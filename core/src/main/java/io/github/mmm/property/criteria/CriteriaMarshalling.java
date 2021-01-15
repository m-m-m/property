/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import io.github.mmm.base.exception.ObjectNotFoundException;
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
  public static final String NAME_OPERATOR = "o";

  /** The property name for the {@link CriteriaExpression#getArgs() arguments}. */
  public static final String NAME_ARGUMENTS = "a";

  /** The property name for a {@link PropertyPath}. */
  public static final String NAME_PROPERTY_PATH = "p";

  /**
   * The constructor.
   */
  public CriteriaMarshalling() {

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

  private void writeArg(StructuredWriter writer, Supplier<?> arg) {

    if (arg instanceof CriteriaExpression) {
      writeObject(writer, (CriteriaExpression<?>) arg);
    } else if (arg instanceof PropertyPath) {
      writer.writeStartObject();
      writer.writeName(NAME_PROPERTY_PATH);
      writer.writeValueAsString(((PropertyPath<?>) arg).path());
      writer.writeEnd();
    } else {
      writer.writeValue(arg.get());
    }
  }

  @Override
  public CriteriaExpression<?> readObject(StructuredReader reader) {

    reader.require(State.START_OBJECT);
    return readExpression(reader);
  }

  private CriteriaExpression<?> readExpression(StructuredReader reader) {

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

  private Supplier<?> readArg(StructuredReader reader) {

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

  private Literal<?> readLiteral(StructuredReader reader) {

    return new Literal<>(reader.readValue());
  }

  private PropertyPath<?> readPropertyPath(StructuredReader reader) {

    String path = reader.readValueAsString();
    return new SimplePath(path);
  }

}
