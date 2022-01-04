/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import io.github.mmm.base.exception.ObjectNotFoundException;
import io.github.mmm.base.sort.SortOrder;
import io.github.mmm.marshall.Marshalling;
import io.github.mmm.marshall.StructuredReader;
import io.github.mmm.marshall.StructuredReader.State;
import io.github.mmm.marshall.StructuredWriter;
import io.github.mmm.value.PropertyPath;

/**
 * {@link Marshalling} to map {@link CriteriaExpression}s.
 *
 * @since 1.0.0
 */
public class CriteriaMarshalling implements Marshalling<CriteriaExpression<?>> {

  private static final String OPERATOR = "Operator";

  /** The property name for the {@link Operator}. */
  public static final String NAME_OPERATOR = "op";

  /** The property name for the {@link CriteriaExpression#getArgs() arguments}. */
  public static final String NAME_ARGUMENTS = "args";

  /** The property name for a {@link PropertyPath}. */
  public static final String NAME_PROPERTY = "path";

  /** The property name for a {@link PropertyPath}. */
  public static final String NAME_SORT_ORDER = "order";

  /** The property name for a value. */
  public static final String NAME_VALUE = "value";

  /** The property name for a {@link ProjectionProperty#getSelection() selection} as property. */
  public static final String NAME_SELECTION_PROPERTY = "pp";

  /** The property name for a {@link ProjectionProperty#getSelection() selection} as expression (aggregate function). */
  public static final String NAME_SELECTION_EXPRESSION = "pe";

  /** The property name for a {@link ProjectionProperty#getProperty() projection property}. */
  public static final String NAME_PROJECTION_PROPERTY = "as";

  private static final CriteriaMarshalling INSTANCE = new CriteriaMarshalling();

  /**
   * The constructor.
   */
  protected CriteriaMarshalling() {

    super();
  }

  @Override
  public void writeObject(StructuredWriter writer, CriteriaExpression<?> expression) {

    writeExpression(writer, expression);
  }

  /**
   * @param writer the {@link StructuredWriter} to write to.
   * @param expression the {@link CriteriaExpression} to write.
   */
  public void writeExpression(StructuredWriter writer, CriteriaExpression<?> expression) {

    writer.writeStartObject();
    writer.writeName(NAME_OPERATOR);
    Operator op = expression.getOperator();
    writer.writeValueAsString(op.getSyntax());
    writer.writeName(NAME_ARGUMENTS);
    writer.writeStartArray();
    for (Supplier<?> arg : expression.getArgs()) {
      writeArg(writer, arg);
    }
    writer.writeEnd(); // end args array
    writer.writeEnd(); // end object
  }

  /**
   * @param writer the {@link StructuredWriter} to write to.
   * @param arg the {@link CriteriaExpression#getArgs() argument}.
   */
  public void writeArg(StructuredWriter writer, Supplier<?> arg) {

    if (arg instanceof CriteriaExpression) {
      writeExpression(writer, (CriteriaExpression<?>) arg);
    } else if (arg instanceof PropertyPath) {
      writeProperty(writer, (PropertyPath<?>) arg);
    } else if (arg instanceof ProjectionProperty) {
      writeProjectionProperty(writer, (ProjectionProperty<?>) arg);
    } else {
      writer.writeValue(arg.get());
    }
  }

  /**
   * @param writer the {@link StructuredWriter} to write to.
   * @param property the {@link PropertyPath}.
   */
  public void writeProperty(StructuredWriter writer, PropertyPath<?> property) {

    writer.writeStartObject();
    writer.writeName(NAME_PROPERTY);
    writer.writeValueAsString(property.path());
    writer.writeEnd();
  }

  /**
   * @param writer the {@link StructuredWriter} to write to.
   * @param projectionProperty the {@link ProjectionProperty} to write.
   */
  public void writeProjectionProperty(StructuredWriter writer, ProjectionProperty<?> projectionProperty) {

    writer.writeStartObject();
    Supplier<?> selection = projectionProperty.getSelection();
    if (selection instanceof PropertyPath) {
      writer.writeName(NAME_SELECTION_PROPERTY);
      writer.writeValueAsString(((PropertyPath<?>) selection).path());
    } else if (selection instanceof CriteriaExpression) {
      writer.writeName(NAME_SELECTION_EXPRESSION);
      writeExpression(writer, (CriteriaExpression<?>) selection);
    } else {
      throw new IllegalStateException();
    }
    writer.writeName(NAME_PROJECTION_PROPERTY);
    writer.writeValueAsString(projectionProperty.getProperty().path());
    writer.writeEnd();
  }

  /**
   * @param writer the {@link StructuredWriter} to write to.
   * @param ordering the {@link CriteriaOrdering}.
   */
  public void writeOrdering(StructuredWriter writer, CriteriaOrdering ordering) {

    writer.writeStartObject();
    writer.writeName(NAME_PROPERTY);
    writer.writeValueAsString(ordering.getProperty().path());
    writer.writeName(NAME_SORT_ORDER);
    writer.writeValueAsString(ordering.getOrder().name());
    writer.writeEnd();
  }

  /**
   * @param writer the {@link StructuredWriter} to write to.
   * @param assignment the {@link PropertyAssignment} to write.
   */
  public void writeAssignment(StructuredWriter writer, PropertyAssignment<?> assignment) {

    writer.writeStartObject();
    writer.writeName(NAME_PROPERTY);
    writer.writeValueAsString(assignment.getProperty().path());
    writer.writeName(NAME_VALUE);
    writeArg(writer, assignment.getValue());
    writer.writeEnd();
  }

  @Override
  public CriteriaExpression<?> readObject(StructuredReader reader) {

    return readExpression(reader);
  }

  /**
   * @param reader the {@link StructuredReader} to read from.
   * @return the parsed {@link CriteriaExpression}.
   */
  public CriteriaExpression<?> readExpression(StructuredReader reader) {

    reader.require(State.START_OBJECT, true);
    return readExpressionInteral(reader);
  }

  private CriteriaExpression<?> readExpressionInteral(StructuredReader reader) {

    Operator op = null;
    List<Supplier<?>> args = null;
    while (!reader.readEnd()) {
      String name = reader.readName();
      if (NAME_OPERATOR.equals(name)) {
        assert (op == null);
        String syntax = reader.readValueAsString();
        op = Operator.of(syntax);
        if (op == null) {
          throw new ObjectNotFoundException(OPERATOR, syntax);
        }
      } else if (NAME_ARGUMENTS.equals(name)) {
        assert (args == null);
        args = new ArrayList<>();
        reader.require(State.START_ARRAY, true);
        while (!reader.readEnd()) {
          Supplier<?> arg = readArg(reader);
          args.add(arg);
        }
      } else {
        // ignore unknown properties ...
        reader.skipValue();
      }
    }
    if (op == null) {
      throw new ObjectNotFoundException(OPERATOR);
    }
    return op.criteria(args);
  }

  /**
   * @param reader the {@link StructuredReader} to read from.
   * @return the parsed {@link CriteriaExpression}.
   */
  public CriteriaPredicate readPredicate(StructuredReader reader) {

    CriteriaExpression<?> expression = readExpression(reader);
    if (expression instanceof CriteriaPredicate) {
      return (CriteriaPredicate) expression;
    }
    throw new IllegalStateException("Expression is not a predicate: " + expression);
  }

  /**
   * @param reader the {@link StructuredReader} to read from.
   * @return the parsed {@link CriteriaExpression#getArgs() argument}.
   */
  public Supplier<?> readArg(StructuredReader reader) {

    if (reader.getState() == State.START_OBJECT) {
      reader.next();
      String name = reader.getName();
      if (NAME_PROPERTY.equals(name)) {
        reader.readName();
        return readProperty(reader);
      } else if (NAME_SELECTION_PROPERTY.equals(name) || NAME_SELECTION_EXPRESSION.equals(name)
          || NAME_PROJECTION_PROPERTY.equals(name)) {
        return readProjectionProperty(reader);
      } else {
        return readExpressionInteral(reader);
      }
    } else {
      return readLiteral(reader);
    }
  }

  /**
   * @param reader the {@link StructuredReader} to read from.
   * @return the parsed {@link Literal}.
   */
  public Literal<?> readLiteral(StructuredReader reader) {

    return Literal.of(reader.readValue());
  }

  /**
   * @param reader the {@link StructuredReader} to read from.
   * @return the parsed {@link PropertyPath}.
   */
  public PropertyPath<?> readProperty(StructuredReader reader) {

    String path = reader.readValueAsString();
    reader.require(State.END_OBJECT, true);
    return SimplePath.of(path);
  }

  /**
   * @param reader the {@link StructuredReader} to read from.
   * @return the parsed {@link ProjectionProperty}.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  private ProjectionProperty<?> readProjectionProperty(StructuredReader reader) {

    Supplier<?> selection = null;
    PropertyPath<?> property = null;
    while (!reader.readEnd()) {
      String name = reader.readName();
      if (NAME_SELECTION_PROPERTY.equals(name)) {
        assert (selection == null);
        String path = reader.readValueAsString();
        selection = SimplePath.of(path);
      } else if (NAME_SELECTION_EXPRESSION.equals(name)) {
        assert (selection == null);
        selection = readExpression(reader);
      } else if (NAME_PROJECTION_PROPERTY.equals(name)) {
        assert (property == null);
        String path = reader.readValueAsString();
        property = SimplePath.of(path);
      } else {
        reader.skipValue();
      }
    }
    return new ProjectionProperty(selection, property);
  }

  /**
   * @param reader the {@link StructuredReader} to read from.
   * @return the parsed {@link CriteriaOrdering}.
   */
  public CriteriaOrdering readOrdering(StructuredReader reader) {

    reader.require(State.START_OBJECT, true);
    PropertyPath<?> property = null;
    SortOrder order = null;
    while (!reader.readEnd()) {
      String name = reader.readName();
      if (NAME_PROPERTY.equals(name)) {
        String p = reader.readValueAsString();
        property = SimplePath.of(p);
      } else if (NAME_SORT_ORDER.equals(name)) {
        String o = reader.readValueAsString();
        order = SortOrder.valueOf(o);
      } else {
        reader.skipValue();
      }
    }
    if (property == null) {
      throw new ObjectNotFoundException(NAME_PROPERTY);
    }
    if (order == null) {
      throw new ObjectNotFoundException(NAME_SORT_ORDER);
    }
    return new CriteriaOrdering(property, order);
  }

  /**
   * @param reader the {@link StructuredReader} to read from.
   * @return the parsed {@link PropertyAssignment}.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public PropertyAssignment<?> readAssignment(StructuredReader reader) {

    reader.require(State.START_OBJECT, true);
    PropertyPath<?> property = null;
    Supplier<?> value = null;
    boolean hasValue = false;
    while (!reader.readEnd()) {
      String name = reader.readName();
      if (NAME_PROPERTY.equals(name)) {
        String p = reader.readValueAsString();
        property = SimplePath.of(p);
      } else if (NAME_VALUE.equals(name)) {
        value = readArg(reader);
        hasValue = true;
      } else {
        reader.skipValue();
      }
    }
    if (property == null) {
      throw new ObjectNotFoundException(NAME_PROPERTY);
    }
    if (!hasValue) {
      throw new ObjectNotFoundException(NAME_VALUE);
    }
    return new PropertyAssignment(property, value);
  }

  /**
   * @return the singleton instance of this {@link CriteriaMarshalling}.
   */
  public static CriteriaMarshalling get() {

    return INSTANCE;
  }

}
