/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.pattern;

import java.util.regex.Pattern;

import io.github.mmm.property.ReadableProperty;
import io.github.mmm.property.object.ReadableSimpleProperty;
import io.github.mmm.value.observable.pattern.PatternExpression;

/**
 * {@link ReadableProperty} with {@link Pattern} {@link #get() value}.
 *
 * @since 1.0.0
 */
public interface ReadablePatternProperty extends ReadableSimpleProperty<Pattern>, PatternExpression {

}
