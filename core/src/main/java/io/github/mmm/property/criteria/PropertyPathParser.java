/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import io.github.mmm.base.filter.CharFilter;
import io.github.mmm.scanner.CharScannerParser;
import io.github.mmm.scanner.CharStreamScanner;
import io.github.mmm.value.PropertyPath;
import io.github.mmm.value.ReadablePath;

/**
 * {@link CharScannerParser} for {@link PropertyPath}.
 *
 * @see SimplePathParser
 * @since 1.0.0
 */
public interface PropertyPathParser extends CharScannerParser<PropertyPath<?>> {

  @Override
  default PropertyPath<?> parse(CharStreamScanner scanner) {

    return parse(scanner, null);
  }

  /**
   * @param scanner the {@link CharStreamScanner} to read from.
   * @param segment the already parsed root segment (including the first '.') for lookahead or {@code null} to parse the
   *        entire path from the scanner.
   * @return the parsed {@link PropertyPath}.
   */
  PropertyPath<?> parse(CharStreamScanner scanner, String segment);

  /**
   * Parses a single {@link PropertyPath#pathSegment() segment} of a {@link PropertyPath}.
   *
   * @param scanner the {@link CharStreamScanner} to read the segment from.
   * @return the parsed segment.
   */
  static String parseSegment(CharStreamScanner scanner) {

    return parseSegment(scanner, null);
  }

  /**
   * Parses a single {@link PropertyPath#pathSegment() segment} of a {@link PropertyPath}.
   *
   * @param scanner the {@link CharStreamScanner} to read the segment from.
   * @param path the optional {@link PropertyPath} that has been parsed so far.
   * @return the parsed segment.
   */
  static String parseSegment(CharStreamScanner scanner, PropertyPath<?> path) {

    String segment = scanner.readWhile(CharFilter.SEGMENT, 1, 256);
    if ((segment == null) || (segment.isEmpty())) {
      if (path != null) {
        throw new IllegalArgumentException("Expected path segment not found at " + path + ".");
      } else {
        throw new IllegalArgumentException("Expected path segment not found.");
      }
    }
    return segment;
  }

  /**
   * @param scanner the {@link CharStreamScanner} to read from. Should be pointing to a
   *        {@link PropertyPath#pathSegment() segment}.
   * @param path the optional {@link ReadablePath} that has already been parsed. May be {@code null}.
   * @return the parsed {@link PropertyPath#pathSegment() segment}.
   */
  static String readSegment(CharStreamScanner scanner, ReadablePath path) {

    String segment = scanner.readWhile(CharFilter.SEGMENT);
    if ((segment == null) || (segment.isEmpty())) {
      if (path != null) {
        throw new IllegalArgumentException("Expected path segment not found at " + path + ".");
      } else {
        throw new IllegalArgumentException("Expected path segment not found.");
      }
    }
    return segment;
  }

}
