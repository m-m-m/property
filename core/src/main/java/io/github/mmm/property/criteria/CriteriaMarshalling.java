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
  public static final String NAME_PROPERTY_PATH = "path";

  /** The property name for a {@link PropertyPath}. */
  public static final String NAME_SORT_ORDER = "order";

  private static final CriteriaMarshalling INSTANCE = new CriteriaMarshalling();

  /**
   * The constructor.
   */
  protected CriteriaMarshalling() {

    super();
  }

  @Override
  public void writeObject(StructuredWriter writer, CriteriaExpression<?> expression) {

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
   * @param writer the {@link StructuredWriter}.
   * @param arg the {@link CriteriaExpression#getArgs() argument}.
   */
  public void writeArg(StructuredWriter writer, Supplier<?> arg) {

    if (arg instanceof CriteriaExpression) {
      writeObject(writer, (CriteriaExpression<?>) arg);
    } else if (arg instanceof PropertyPath) {
      writePropertyPath(writer, (PropertyPath<?>) arg);
    } else {
      writer.writeValue(arg.get());
    }
  }

  /**
   * @param writer the {@link StructuredWriter}.
   * @param propertyPath the {@link PropertyPath}.
   */
  public void writePropertyPath(StructuredWriter writer, PropertyPath<?> propertyPath) {

    writer.writeStartObject();
    writer.writeName(NAME_PROPERTY_PATH);
    writer.writeValueAsString(propertyPath.path());
    writer.writeEnd();
  }

  /**
   * @param writer the {@link StructuredWriter}.
   * @param ordering the {@link CriteriaOrdering}.
   */
  public void writeOrdering(StructuredWriter writer, CriteriaOrdering ordering) {

    writer.writeStartObject();
    writer.writeName(NAME_PROPERTY_PATH);
    writer.writeValueAsString(ordering.getPath().path());
    writer.writeName(NAME_SORT_ORDER);
    writer.writeValueAsString(ordering.getOrder().name());
    writer.writeEnd();
  }

  @Override
  public CriteriaExpression<?> readObject(StructuredReader reader) {

    reader.require(State.START_OBJECT);
    return readExpression(reader);
  }

  /**
   * @param reader the {@link StructuredReader}.
   * @return the parsed {@link CriteriaExpression}.
   */
  public CriteriaExpression<?> readExpression(StructuredReader reader) {

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
        reader.require(State.START_ARRAY);
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
   * @param reader the {@link StructuredReader}.
   * @return the parsed {@link CriteriaExpression#getArgs() argument}.
   */
  public Supplier<?> readArg(StructuredReader reader) {

    if (reader.getState() == State.START_OBJECT) {
      reader.next();
      if (NAME_PROPERTY_PATH.equals(reader.getName())) {
        reader.readName();
        return readPropertyPath(reader);
      } else {
        return readExpression(reader);
      }
    } else {
      return readLiteral(reader);
    }
  }

  /**
   * @param reader the {@link StructuredReader}.
   * @return the parsed {@link Literal}.
   */
  public Literal<?> readLiteral(StructuredReader reader) {

    return Literal.of(reader.readValue());
  }

  /**
   * @param reader the {@link StructuredReader}.
   * @return the parsed {@link PropertyPath}.
   */
  public PropertyPath<?> readPropertyPath(StructuredReader reader) {

    String path = reader.readValueAsString();
    reader.require(State.END_OBJECT);
    return new SimplePath(path);
  }

  /**
   * @param reader the {@link StructuredReader}.
   * @return the parsed {@link CriteriaOrdering}.
   */
  public CriteriaOrdering readOrdering(StructuredReader reader) {

    reader.require(State.START_OBJECT);
    PropertyPath<?> path = null;
    SortOrder order = null;
    while (!reader.readEnd()) {
      String name = reader.readName();
      if (NAME_PROPERTY_PATH.equals(name)) {
        String p = reader.readValueAsString();
        path = new SimplePath(p);
      } else if (NAME_SORT_ORDER.equals(name)) {
        String o = reader.readValueAsString();
        order = SortOrder.valueOf(o);
      } else {
        reader.skipValue();
      }
    }
    if (path == null) {
      throw new ObjectNotFoundException(NAME_PROPERTY_PATH);
    }
    if (order == null) {
      throw new ObjectNotFoundException(NAME_SORT_ORDER);
    }
    return new CriteriaOrdering(path, order);
  }

  /**
   * @return the singleton instance of this {@link CriteriaMarshalling}.
   */
  public static CriteriaMarshalling get() {

    return INSTANCE;
  }

}
