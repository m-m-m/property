package io.github.mmm.property.string;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import io.github.mmm.property.PropertyMetadata;

/**
 * {@link StringProperty} for a {@link Set} of tags.
 */
public final class TagsProperty extends StringProperty implements WritableTagsProperty {

  private final Set<String> tags;

  private final Set<String> tagsMutable;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   */
  public TagsProperty(String name) {

    this(name, null, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public TagsProperty(String name, PropertyMetadata<String> metadata) {

    this(name, null, metadata);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param value the (initial) {@link #get() value}.
   */
  public TagsProperty(String name, String value) {

    this(name, value, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() property name}.
   * @param value the (initial) {@link #get() value}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public TagsProperty(String name, String value, PropertyMetadata<String> metadata) {

    super(name, metadata);
    this.tagsMutable = new HashSet<>();
    this.tags = Collections.unmodifiableSet(this.tagsMutable);
    setValue(value);
  }

  @Override
  protected void doSet(String newValue) {

    this.tagsMutable.clear();
    if (newValue == null) {
      super.doSet("");
      return;
    }
    StringBuilder sb = new StringBuilder(newValue.length());
    for (String tag : newValue.split(",")) {
      tag = tag.trim();
      if (!tag.isEmpty()) {
        boolean added = this.tagsMutable.add(tag);
        if (added) {
          if (sb.length() > 0) {
            sb.append(',');
          }
          sb.append(tag);
        }
      }
    }
    super.doSet(sb.toString());
  }

  /**
   * @return the {@link Set} of individual tags.
   */
  @Override
  public Set<String> getTags() {

    return this.tags;
  }

  /**
   * @param tag the tag to add.
   * @return {@code true} if the given {@code tag} has been added, {@code false} otherwise (if it has already been
   *         present before).
   */
  public boolean addTag(String tag) {

    boolean added = doAddTag(tag);
    if (added) {
      fireChange(doGet());
    }
    return added;
  }

  private boolean doAddTag(String tag) {

    if (tag == null) {
      return false;
    }
    tag = tag.trim();
    if (tag.isEmpty()) {
      return false;
    }
    boolean added = this.tagsMutable.add(tag);
    if (added) {
      String value = doGet();
      if (value.isEmpty()) {
        super.doSet(tag);
      } else {
        super.doSet(value + "," + tag);
      }
    }
    return added;
  }

  /**
   * @param tags2add the {@link Collection} of tags to {@link #addTag(String) add}.
   * @return {@code true} if the given {@code tag} has been added, {@code false} otherwise (if it has already been
   *         present before).
   */
  public boolean addTags(Collection<String> tags2add) {

    boolean added = false;
    for (String tag : tags2add) {
      boolean tagAdded = doAddTag(tag);
      if (tagAdded) {
        added = true;
      }
    }
    if (added) {
      fireChange(doGet());
    }
    return added;
  }

  /**
   * @param tag the tag to remove.
   * @return {@code true} if the given {@code tag} has been removed, {@code false} otherwise (if it has not been present
   *         already).
   */
  public boolean removeTag(String tag) {

    boolean removed = this.tagsMutable.remove(tag);
    if (removed) {
      String value = doGet();
      int tagStart = value.indexOf(tag);
      int start = 0;
      int end = 0;
      int valueLength = value.length();
      int tagLength = tag.length();
      if (valueLength == tagLength) {
        assert (tagStart == 0);
        this.tagsMutable.clear();
        super.doSet("");
      }
      boolean tagStarts = false;
      boolean tagEnds = false;
      // e.g. search for tag "hit" in value "pop,superhits,hit" needs to continue indexOf search so we loop here
      while (!tagStarts || !tagEnds) {
        if (end > 0) {
          tagStart = value.indexOf(tag, tagStart);
        }
        if (tagStart < 0) {
          assert (tagStart >= 0); // internal consistency error
          return removed;
        }
        start = tagStart;
        end = tagStart + tagLength;
        if (tagStart == 0) {
          tagStarts = true;
        } else {
          char before = value.charAt(tagStart - 1);
          tagStarts = (before == ',');
          if (tagStarts) {
            start--;
          }
        }
        if (end < valueLength) {
          char after = value.charAt(end + 1);
          tagEnds = (after == ',');
          if (tagEnds && (tagStart == 0)) {
            end++;
          }
        } else {
          tagEnds = true;
        }
      }
      value = value.substring(0, start) + value.substring(end);
      super.doSet(value);
      fireChange(value);
    }
    return removed;
  }

}
