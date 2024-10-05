/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.time.month;

import java.time.Month;

import io.github.mmm.marshall.StructuredReader;
import io.github.mmm.marshall.StructuredWriter;
import io.github.mmm.property.PropertyMetadata;
import io.github.mmm.property.object.SimpleProperty;

/**
 * This is the implementation of {@link WritableMonthProperty}.
 *
 * @since 1.0.0
 */
public class MonthProperty extends SimpleProperty<Month> implements WritableMonthProperty {

  private Month value;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public MonthProperty(String name) {

    this(name, null, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public MonthProperty(String name, PropertyMetadata<Month> metadata) {

    this(name, null, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   */
  public MonthProperty(String name, Month value) {

    this(name, value, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public MonthProperty(String name, Month value, PropertyMetadata<Month> metadata) {

    super(name, metadata);
    this.value = value;
  }

  @Override
  protected Month doGet() {

    return this.value;
  }

  @Override
  protected void doSet(Month newValue) {

    this.value = newValue;
  }

  @Override
  public Month getFallbackSafeValue() {

    return null;
  }

  @Override
  public void writeValue(StructuredWriter writer, Month month) {

    if (month == null) {
      writer.writeValueAsNull();
    } else {
      writer.writeValueAsInteger(month.getValue());
    }
  }

  @Override
  protected Month readValue(StructuredReader reader, boolean apply) {

    Month month = null;
    Integer monthValue = reader.readValueAsInteger();
    if (monthValue != null) {
      int m = monthValue.intValue();
      if (m != 0) {
        month = Month.of(m);
      }
    }
    if (apply) {
      set(month);
    }
    return month;
  }

}
