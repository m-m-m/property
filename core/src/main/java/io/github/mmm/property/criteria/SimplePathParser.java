package io.github.mmm.property.criteria;

import io.github.mmm.scanner.CharStreamScanner;
import io.github.mmm.value.PropertyPath;
import io.github.mmm.value.SimplePath;

/**
 * Implementation of {@link PropertyPathParser} for {@link SimplePath}.
 *
 * @since 1.0.0
 */
public class SimplePathParser implements PropertyPathParser {

  /** The singleton instance. */
  public static final SimplePathParser INSTANCE = new SimplePathParser();

  private SimplePathParser() {

    super();
  }

  @Override
  public PropertyPath<?> parse(CharStreamScanner scanner, String segment) {

    SimplePath path = null;
    if (segment != null) {
      path = new SimplePath(null, segment);
    }
    return parse(scanner, path);
  }

  /**
   * @param scanner the {@link CharStreamScanner} to read from.
   * @param parent the {@link PropertyPath#parentPath() parent path} or {@code null}.
   * @return the parsed {@link SimplePath}.
   */
  public SimplePath parse(CharStreamScanner scanner, SimplePath parent) {

    SimplePath path = parent;
    do {
      String segment = PropertyPathParser.readSegment(scanner, path);
      path = new SimplePath(path, segment);
    } while (scanner.expectOne('.'));
    return path;
  }
}
