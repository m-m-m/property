/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
/**
 * Provides advanced properties with support for change-listeners, bindings, validation, and marshalling.
 */
@SuppressWarnings("all") //
module io.github.mmm.property.builder {

  requires transitive io.github.mmm.property;

  requires transitive io.github.mmm.validation.main;

  exports io.github.mmm.property.builder;

  exports io.github.mmm.property.builder.container;

  exports io.github.mmm.property.builder.lang;

  exports io.github.mmm.property.builder.number;

  exports io.github.mmm.property.builder.time;

}
