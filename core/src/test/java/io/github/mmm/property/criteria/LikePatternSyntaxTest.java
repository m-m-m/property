/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test of {@link LikePatternSyntax}.
 */
class LikePatternSyntaxTest extends Assertions {

  /** Basic test of {@link LikePatternSyntax#GLOB}. */
  @Test
  void testGlob() {

    LikePatternSyntax syntax = LikePatternSyntax.GLOB;
    assertThat(syntax.getAny()).isEqualTo('*');
    assertThat(syntax.getSingle()).isEqualTo('?');
  }

  /** Basic test of {@link LikePatternSyntax#SQL}. */
  @Test
  void testSql() {

    LikePatternSyntax syntax = LikePatternSyntax.SQL;
    assertThat(syntax.getAny()).isEqualTo('%');
    assertThat(syntax.getSingle()).isEqualTo('_');
  }

  /**
   * Test of {@link LikePatternSyntax#convert(String, LikePatternSyntax, boolean)} from {@link LikePatternSyntax#GLOB}
   * to {@link LikePatternSyntax#SQL}.
   */
  @Test
  void testGlob2Sql() {

    LikePatternSyntax source = LikePatternSyntax.GLOB;
    LikePatternSyntax target = LikePatternSyntax.SQL;
    assertThat(target.convert("", source)).isEqualTo("");
    assertThat(target.convert("*", source)).isEqualTo("%");
    assertThat(target.convert("?", source)).isEqualTo("_");
    assertThat(target.convert("a*b?c", source)).isEqualTo("a%b_c");
    assertThat(target.convert("*10% key_loss*", source)).isEqualTo("%10\\% key\\_loss%");
    assertThat(target.convert("*10%\\\\esc\\*pe\\d*\\", source)).isEqualTo("%10\\%\\\\esc*pe\\\\d%\\\\");

    assertThat(target.convert("a", source, true)).isEqualTo("%a%");
    assertThat(target.convert("*", source, true)).isEqualTo("%");
    assertThat(target.convert("a*b?c", source, true)).isEqualTo("%a%b_c%");
  }

  /**
   * Test of {@link LikePatternSyntax#convert(String, LikePatternSyntax, boolean)} from {@link LikePatternSyntax#SQL} to
   * {@link LikePatternSyntax#GLOB}.
   */
  @Test
  void testSql2Glob() {

    LikePatternSyntax source = LikePatternSyntax.SQL;
    LikePatternSyntax target = LikePatternSyntax.GLOB;
    assertThat(target.convert("", source)).isEqualTo("");
    assertThat(target.convert("%", source)).isEqualTo("*");
    assertThat(target.convert("_", source)).isEqualTo("?");
    assertThat(target.convert("a%b_c", source)).isEqualTo("a*b?c");
    assertThat(target.convert("%10* key?loss%", source)).isEqualTo("*10\\* key\\?loss*");
    assertThat(target.convert("%10*\\\\esc\\%pe\\d%\\", source)).isEqualTo("*10\\*\\\\esc%pe\\\\d*\\\\");

    assertThat(target.convert("a", source, true)).isEqualTo("*a*");
    assertThat(target.convert("%", source, true)).isEqualTo("*");
    assertThat(target.convert("a%b_c", source, true)).isEqualTo("*a*b?c*");
  }

  /** Test of {@link LikePatternSyntax#autoDetect(String)}. */
  @Test
  void testAutoDetect() {

    assertThat(LikePatternSyntax.autoDetect(null)).isEqualTo(null);
    assertThat(LikePatternSyntax.autoDetect("")).isEqualTo(null);
    assertThat(LikePatternSyntax.autoDetect("a")).isEqualTo(null);
    assertThat(LikePatternSyntax.autoDetect("aBc")).isEqualTo(null);
    assertThat(LikePatternSyntax.autoDetect("a*b")).isEqualTo(LikePatternSyntax.GLOB);
    assertThat(LikePatternSyntax.autoDetect("a?b")).isEqualTo(LikePatternSyntax.GLOB);
    assertThat(LikePatternSyntax.autoDetect("a%b")).isEqualTo(LikePatternSyntax.SQL);
    assertThat(LikePatternSyntax.autoDetect("a_b")).isEqualTo(LikePatternSyntax.SQL);
  }

}