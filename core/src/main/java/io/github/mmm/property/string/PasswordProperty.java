package io.github.mmm.property.string;

import io.github.mmm.property.PropertyMetadata;

/**
 * {@link StringProperty} with password {@link #get() value}.
 */
public class PasswordProperty extends StringProperty implements WritablePasswordProperty {

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public PasswordProperty(String name) {

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public PasswordProperty(String name, PropertyMetadata<String> metadata) {

    super(name, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   */
  public PasswordProperty(String name, String value) {

    super(name, value);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public PasswordProperty(String name, String value, PropertyMetadata<String> metadata) {

    super(name, value, metadata);
  }

  @Override
  protected boolean isSensitive() {

    return true;
  }

}
