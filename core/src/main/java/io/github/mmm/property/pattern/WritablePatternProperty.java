/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.pattern;

import java.util.regex.Pattern;

import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.object.WritableSimpleProperty;
import io.github.mmm.value.observable.pattern.WritablePatternValue;

/**
 * {@link WritableProperty} with {@link Pattern} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface WritablePatternProperty
    extends ReadablePatternProperty, WritableSimpleProperty<Pattern>, WritablePatternValue {

}
