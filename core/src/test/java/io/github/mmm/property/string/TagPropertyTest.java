package io.github.mmm.property.string;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test of {@link TagsProperty}.
 */
public class TagPropertyTest extends Assertions {

  /**
   * Test {@link TagsProperty#removeTag(String)} of last tag with collision.
   */
  @Test
  public void testRemoveLast() {

    // given
    TagsProperty tags = new TagsProperty("tags", "pop,partyhit,superhits,hit");
    // when
    boolean removed = tags.removeTag("hit");
    // then
    assertThat(removed).isTrue();
    assertThat(tags.get()).isEqualTo("pop,partyhit,superhits");
  }

  /**
   * Test {@link TagsProperty#removeTag(String)} of first tag with collision.
   */
  @Test
  public void testRemoveFirst() {

    // given
    TagsProperty tags = new TagsProperty("tags", "hit,pop,partyhit,superhits");
    // when
    boolean removed = tags.removeTag("hit");
    // then
    assertThat(removed).isTrue();
    assertThat(tags.get()).isEqualTo("pop,partyhit,superhits");
  }

}
