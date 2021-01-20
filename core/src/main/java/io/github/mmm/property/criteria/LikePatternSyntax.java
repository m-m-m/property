/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import java.util.HashMap;
import java.util.Map;

import io.github.mmm.base.exception.DuplicateObjectException;

/**
 * Enum defining available syntaxes for a match pattern in a LIKE-clause. While databases typically require {@link #SQL}
 * syntax, human user expect {@link #GLOB} syntax in search forms. Therefore this enum also supports
 * {@link #convert(String, LikePatternSyntax, boolean) conversion} from one syntax to another.
 */
public class LikePatternSyntax {

  private static final Map<String, LikePatternSyntax> NAME2SYNTAX_MAP = new HashMap<>();

  /**
   * Glob syntax that is typically expected by end-users and supported by typical search forms. It uses asterisk ('*')
   * for {@link #getAny() any wildcard} and question-mark ('?') for {@link #getSingle() single wildcard}.
   */
  public static final LikePatternSyntax GLOB = new LikePatternSyntax("Glob", '*', '?');

  /**
   * SQL syntax that is typically required by databases. It uses percent ('%') for {@link #getAny() any wildcard} and
   * underscore ('_') for {@link #getSingle() single wildcard}.
   */
  public static final LikePatternSyntax SQL = new LikePatternSyntax("SQL", '%', '_');

  /** The escape character. */
  public static final char ESCAPE = '\\';

  private final String name;

  private final char any;

  private final char single;

  /**
   * The constructor.
   *
   * @param name the name and {@link #toString() string representation}.
   * @param any the {@link #getAny() any wildcard}.
   * @param single {@link #getSingle() single wildcard}.
   */
  protected LikePatternSyntax(String name, char any, char single) {

    super();
    this.name = name;
    this.any = any;
    this.single = single;
    LikePatternSyntax old = NAME2SYNTAX_MAP.putIfAbsent(name, this);
    if (old != null) {
      throw new DuplicateObjectException(this, name, old);
    }
  }

  /**
   * @return the name of this syntax.
   */
  public String getName() {

    return this.name;
  }

  /**
   * @return the wildcard character that matches any string including the {@link String#isEmpty() empty} string.
   */
  public char getAny() {

    return this.any;
  }

  /**
   * @return the wildcard character that matches exactly one single character.
   */
  public char getSingle() {

    return this.single;
  }

  /**
   * @param pattern the LIKE pattern in the given {@link LikePatternSyntax}.
   * @param syntax the {@link LikePatternSyntax} of the given {@code pattern}.
   * @return the given {@code pattern} converted to this {@link LikePatternSyntax}.
   */
  public String convert(String pattern, LikePatternSyntax syntax) {

    return convert(pattern, syntax, false);
  }

  /**
   * @param pattern the LIKE pattern in the given {@link LikePatternSyntax}.
   * @param syntax the {@link LikePatternSyntax} of the given {@code pattern}.
   * @param matchSubstring - {@code true} if the given {@code pattern} shall also match substrings, {@code false}
   *        otherwise.
   * @return the given {@code pattern} converted to this {@link LikePatternSyntax}.
   */
  public String convert(String pattern, LikePatternSyntax syntax, boolean matchSubstring) {

    if ((pattern == null) || pattern.isEmpty()) {
      if (matchSubstring) {
        return Character.toString(this.any);
      } else {
        return pattern;
      }
    }
    if (syntax == null) {
      syntax = autoDetect(pattern);
    }
    if (this == syntax) {
      String result = pattern;
      if (matchSubstring) {
        if (pattern.charAt(0) != this.any) {
          result = this.any + result;
        }
        int lastIndex = pattern.length() - 1;
        if ((pattern.charAt(lastIndex) != this.any) || ((lastIndex > 0) && (pattern.charAt(lastIndex - 1) == ESCAPE))) {
          result = result + this.any;
        }
      }
      return result;
    }
    int length = pattern.length();
    StringBuilder sb = new StringBuilder(length + 8);
    boolean lastWildcardAny = false;
    for (int i = 0; i < length; i++) {
      lastWildcardAny = false;
      char c = pattern.charAt(i);
      if (c == syntax.any) {
        c = this.any;
        lastWildcardAny = true;
      } else if (c == syntax.single) {
        c = this.single;
      } else if ((c == this.any) || (c == this.single)) {
        if ((i == 0) && matchSubstring) {
          sb.append(this.any);
        }
        sb.append(ESCAPE);
      } else if (c == ESCAPE) {
        // lookahead
        int next = i + 1;
        if (next < length) {
          c = pattern.charAt(next);
          i = next;
        }
        if ((c != syntax.any) && (c != syntax.single)) {
          sb.append(ESCAPE);
          if (c != ESCAPE) {
            sb.append(ESCAPE);
          }
        }
      }
      if (matchSubstring && (i == 0) && !lastWildcardAny) {
        sb.append(this.any);
      }
      sb.append(c);
    }
    if (matchSubstring && !lastWildcardAny) {
      sb.append(this.any);
    }
    return sb.toString();
  }

  /**
   * @param pattern the string value that may be a pattern.
   * @return the {@link LikePatternSyntax} for the given {@code pattern} or {@code null} if the given {@code pattern}
   *         does not contain any wildcards.
   */
  public static LikePatternSyntax autoDetect(String pattern) {

    if ((pattern == null) || pattern.isEmpty()) {
      return null;
    }
    for (LikePatternSyntax syntax : NAME2SYNTAX_MAP.values()) {
      if (pattern.indexOf(syntax.any) > 0) {
        return syntax;
      } else if (pattern.indexOf(syntax.single) > 0) {
        return syntax;
      }
    }
    return null;
  }

  @Override
  public String toString() {

    return this.name + "(" + this.any + "," + this.single + ")";
  }

}
