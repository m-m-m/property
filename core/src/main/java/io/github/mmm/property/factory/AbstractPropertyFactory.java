/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.factory;

import io.github.mmm.property.WritableProperty;

/**
 * The abstract base implementation of {@link PropertyFactory}.
 *
 * @param <V> the generic type of the {@link WritableProperty#get() property value}.
 * @param <P> the generic type of the {@link WritableProperty property}.
 *
 * @since 1.0.0
 */
public abstract class AbstractPropertyFactory<V, P extends WritableProperty<V>> implements PropertyFactory<V, P> {

}
