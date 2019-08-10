/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api;

import net.sf.mmm.marshall.MarshallableObject;
import net.sf.mmm.value.PropertyPath;
import net.sf.mmm.value.ReadableTypedValue;
import net.sf.mmm.value.observable.ObservableValue;

/**
 * This is the interface for a generic property.
 *
 * @param <V> is the generic type of the {@link #getValue() value}.
 *
 * @author hohwille
 * @since 1.0.0
 */
public interface ReadableProperty<V>
    extends ObservableValue<V>, ReadableTypedValue<V>, PropertyPath<V>, MarshallableObject {

  /**
   * @return the name of the property. By convention it should start with a {@link Character#isUpperCase(char) capital}
   *         letter followed by alpha-numeric characters. The name of a single property must especially not contain the
   *         dot character (.) that is used to separate segments in a {@link PropertyPath path}.
   */
  @Override
  String getName();

  /**
   * @return the {@code net.sf.mmm.util.bean.api.Bean} owning the property.
   * @deprecated planned to be removed, causing cyclic dependencies and door for hackish solutions.
   */
  @Deprecated
  Object getBean();

}
