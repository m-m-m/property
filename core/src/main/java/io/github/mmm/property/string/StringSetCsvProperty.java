/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.string;

import java.util.Set;

import io.github.mmm.property.PropertyMetadata;

/**
 * {@link StringSetProperty} using ";" as separator and no {@link #isEncloseWithSeparator() enclosing}. Especially
 * useful to represent tags, synonyms/aliases, etc.<br>
 * There are rumors about very expensive databases that can not distinguish the {@link String#isEmpty() empty}
 * {@link String} from {@code null}. In such contexts you should not use CSV format and this property or you can not
 * distinguish the empty {@link Set} from a {@link Set} {@link Set#contains(Object) containing} the
 * {@link String#isEmpty() empty} {@link String} after reading the {@link #get() value} from the database.
 *
 * @since 1.0.0
 */
public class StringSetCsvProperty extends StringSetProperty {

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public StringSetCsvProperty(String name) {

    this(name, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public StringSetCsvProperty(String name, PropertyMetadata<String> metadata) {

    super(name, metadata);
  }

  @Override
  protected String getSeparator() {

    return ";";
  }

  @Override
  protected boolean isEncloseWithSeparator() {

    return false;
  }

}
