/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.property.api.links.impl;

import net.sf.mmm.marshall.StructuredReader;
import net.sf.mmm.marshall.StructuredWriter;
import net.sf.mmm.util.data.api.id.Id;
import net.sf.mmm.util.data.api.link.Link;

/**
 * Mapper to read and write {@link Link} and {@link Id} values.
 */
public final class LinkMapper {

  private static final String ID = "id";

  private static final String VERSION = "version";

  private LinkMapper() {

  }

  public static void writeLink(StructuredWriter writer, Link<?> link) {

    Id<?> id = null;
    if (link != null) {
      id = link.getId();
    }
    writeId(writer, id);
  }

  public static void writeId(StructuredWriter writer, Id<?> id) {

    Object idValue = null;
    Comparable<?> version = null;
    if (id != null) {
      idValue = id.getId();
      version = id.getVersion();
    }
    if (idValue == null) {
      writer.writeValueAsNull();
      return;
    }
    if (version == null) {
      writer.writeValue(idValue);
    } else {
      writer.writeStartObject();
      writer.writeName(ID);
      writer.writeValue(idValue);
      writer.writeName(VERSION);
      writer.writeValue(version);
      writer.writeEnd();
    }
  }

  public static <I extends Id<?>> I readId(StructuredReader reader, Class<I> idClass) {

    Class<?> idValueClass = null;
    if (reader.readStartObject()) {
      
    } else {
      reader.readValue(type)
    }
  }

}
