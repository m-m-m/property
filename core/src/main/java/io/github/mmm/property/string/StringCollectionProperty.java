/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.string;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import io.github.mmm.base.collection.AbstractIterator;
import io.github.mmm.marshall.StructuredReader;
import io.github.mmm.marshall.StructuredWriter;
import io.github.mmm.property.PropertyMetadata;

/**
 * This is an extension of {@link StringProperty} that stores a {@link Collection} of {@link String}s as a simple
 * {@link String} in a CSV like format. Since it needs a separator character that shall not occur inside the contained
 * {@link String} elements, it is only suitable for limited use-cases such as representing a set of tags or synonyms.
 * For a generic collection of arbitrary {@link String}s (or other elements) please consider using
 * {@link io.github.mmm.property.container.collection.CollectionProperty}. However, in cases where this implementation
 * is applicable, it can be a pragmatic simplification compared to relational associations.<br>
 * Please note that {@code null} elements will be silently ignored by operations such as {@link #add(String) add} or
 * {@link #remove(String) remove} while the empty {@link String} is not.
 *
 * @since 1.0.0
 */
public abstract class StringCollectionProperty extends StringProperty implements Iterable<String> {

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   */
  public StringCollectionProperty(String name) {

    super(name);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() name}.
   * @param metadata the {@link #getMetadata() metadata}.
   */
  public StringCollectionProperty(String name, PropertyMetadata<String> metadata) {

    super(name, metadata);
  }

  /**
   * @return the potential {@link Collection} to redundantly manage the elements efficiently or {@code null} if all
   *         information is only maintained in the {@link String} {@link #get() value}. Please note that
   *         {@link #bindTwoWay(io.github.mmm.value.observable.WritableObservableValue) binding} may cause undesired
   *         effects if backed by a {@link Collection}.
   */
  protected Collection<String> getCollection() {

    return null;
  }

  /**
   * @return the separator character (e.g. "|", ";", "," - may also be something exotic like "$&%" to avoid collision
   *         with elements).
   */
  protected String getSeparator() {

    return "|";
  }

  /**
   * Determines if the elements shall be enclosed with the separator (e.g. "|one|two|three|") or not (e.g.
   * "one|two|three"). Enclosing can be used as a pragmatic approach to store lists or sets in a database while still
   * being able to search with SQL using {@code contains} (e.g. {@code WHERE x.tags CONTAINS '|one|'}). Also enclosing
   * is increasing the efficiency of {@link #add(String) add} and {@link #remove(String) remove} operations. Therefore,
   * it is highly recommended to stick with the default ({@code true} for activated enclosing). To display the
   * {@link #get() value} to end-users you can use {@link #getCsv(String, boolean)}.
   *
   * @return {@code true} if the {@link String} {@link #get() value} shall be enclosed with the {@link #getSeparator()
   *         separator}, {@code false} otherwise.
   */
  protected boolean isEncloseWithSeparator() {

    return true;
  }

  private void doSet(String oldValue, String value) {

    if (!isValueEqual(value, oldValue)) {
      super.setWithChange(oldValue, value);
    }
  }

  @Override
  protected void setWithChange(String oldValue, String value) {

    Collection<String> collection = getCollection();
    if (collection != null) {
      collection.clear();
      Consumer<String> consumer = s -> collection.add(s);
      if ((value != null) && !value.isEmpty()) {
        String separator = getSeparator();
        boolean enclose = isEncloseWithSeparator();
        // will parse the value CSV and insert elements into collection...
        convertCsv(value, separator, enclose, false, null, false, consumer);
      }
    }
    if (isEncloseWithSeparator() && (value != null) && !value.isEmpty()) {
      String separator = getSeparator();
      if (!value.startsWith(separator)) {
        value = separator + value;
      }
      if (!value.endsWith(separator)) {
        value = value + separator;
      }
    }
    super.setWithChange(oldValue, value);
  }

  /**
   * @see List#contains(Object)
   *
   * @param element the element to check.
   * @return {@code true} if the given {@code element} is contained in this collection of {@link String}s.
   */
  public boolean contains(String element) {

    Collection<String> collection = getCollection();
    if (collection != null) {
      return collection.contains(element);
    }
    return indexOf(get(), element) >= 0;
  }

  private int indexOf(String value, String element) {

    if (value == null) {
      return -1;
    }
    String separator = getSeparator();
    boolean enclose = isEncloseWithSeparator();
    if (enclose && (value.isEmpty() || value.equals(separator))) {
      return -1;
    }
    int valueLength = value.length();
    int elementLength = element.length();
    if (enclose) {
      String wrapped = separator + element + separator;
      return value.indexOf(wrapped);
    }
    int separatorLength = separator.length();
    if (elementLength == 0) {
      if (value.startsWith(separator)) {
        return 0;
      } else if (value.endsWith(separator)) {
        return valueLength - separatorLength;
      } else {
        return value.indexOf(separator + separator);
      }
    } else {
      int start = 0;
      do {
        int index = value.indexOf(element, start);
        if (index < 0) {
          return -1;
        }
        boolean match = true;
        int separatorIndex = index - separatorLength;
        if (index > 0) {
          match = (separatorIndex < 0) || (value.indexOf(separator, separatorIndex) == separatorIndex);
        }
        start = index + elementLength;
        if (start < valueLength) {
          match &= (value.indexOf(separator, start) == start);
        } else {
          start = -1;
        }
        if (match) {
          if (index == 0) {
            return 0;
          }
          return separatorIndex;
        }
      } while (start > 0);
      return -1;
    }
  }

  /**
   * @see List#add(Object)
   *
   * @param element the element to add to this collection of {@link String}s.
   * @return {@code true} if the {@link #get() value} has changed, {@code false} otherwise (if the given {@code element}
   *         was already {@link #contains(String) present} and this property ensures distinct elements).
   */
  public boolean add(String element) {

    requireWritable();
    if (element == null) {
      return false;
    }
    String separator = getSeparator();
    assert (!element.contains(separator));
    Collection<String> collection = getCollection();
    if (collection != null) {
      boolean added = collection.add(element);
      if (!added) {
        return false; // e.g. element already contained in Set so nothing to change
      }
    }
    String oldValue = get();
    String value;
    if (oldValue == null) {
      if (isEncloseWithSeparator()) {
        value = separator + element + separator;
      } else {
        value = element;
      }
    } else if (isEncloseWithSeparator()) {
      value = oldValue + element + separator;
    } else {
      value = oldValue + separator + element;
    }
    doSet(oldValue, value);
    return true;
  }

  /**
   * @see Collection#remove(Object)
   *
   * @param element the element to remove from this {@link String} collection.
   * @return {@code true} if the given element was previously present and is now removed so the value actually has
   *         changed, {@code false} otherwise (if not present and no change).
   */
  public boolean remove(String element) {

    requireWritable();
    if (element == null) {
      return false;
    }
    boolean removed = false;
    Collection<String> collection = getCollection();
    if (collection != null) {
      removed = collection.remove(element);
      if (!removed) {
        return false; // assuming collection is in sync with value
      }
    }
    String oldValue = get();
    int index = indexOf(oldValue, element);
    if (index < 0) {
      return false;
    }
    int valueLength = oldValue.length();
    String value;
    int separatorLength = getSeparator().length();
    int end = index + separatorLength + element.length();
    if (index == 0) {
      if (end >= valueLength) {
        value = null;
      } else {
        if (isEncloseWithSeparator() && (end + 1 == valueLength)) {
          value = null;
        } else {
          value = oldValue.substring(end);
        }
      }
    } else {
      if (end >= valueLength) {
        value = oldValue.substring(0, index);
      } else {
        value = oldValue.substring(0, index) + oldValue.substring(end);
      }
    }
    doSet(oldValue, value);
    return true;
  }

  /**
   * @param collection the {@link Collection} of elements to set as separated {@link #add(String) string value}.
   */
  public void set(Collection<String> collection) {

    requireWritable();
    Collection<String> values = getCollection();
    if (values != null) {
      values.clear();
      if (collection != null) {
        values.addAll(collection);
      }
    }
    String oldValue = get();
    String value;
    if (collection == null) {
      value = null;
    } else {
      boolean enclose = isEncloseWithSeparator();
      if (collection.isEmpty()) {
        if (enclose) {
          value = "";
        } else {
          value = null;
        }
      } else {
        String separator = getSeparator();
        StringBuilder sb = new StringBuilder(collection.size() * (6 + separator.length()));
        String sep = "";
        if (enclose) {
          sep = separator;
        }
        for (String element : collection) {
          sb.append(sep);
          sep = separator;
          sb.append(element);
        }
        if (enclose) {
          sb.append(separator);
        }
        value = sb.toString();
      }
    }
    doSet(oldValue, value);
  }

  /**
   * @param separator the custom {@link #getSeparator() separator} to use (e.g. ";" for CSV).
   * @param enclose the custom {@link #isEncloseWithSeparator() enclose with separator flag}.
   * @return the {@link #get() value} as CSV with the given parameters.
   */
  public String getCsv(String separator, boolean enclose) {

    String value = get();
    String defaultSeparator = getSeparator();
    boolean defaultEnclose = isEncloseWithSeparator();
    String result = convertCsv(value, defaultSeparator, defaultEnclose, false, separator, enclose, null);
    return result;
  }

  /**
   * @param csv the new {@link #get() value} with elements separated by the given {@code separator}.
   * @param separator the custom {@link #getSeparator() separator} to use (e.g. ";" for CSV).
   * @param enclose the custom {@link #isEncloseWithSeparator() enclose with separator flag}.
   */
  public void setCsv(String csv, String separator, boolean enclose) {

    setCsv(csv, separator, enclose, false);
  }

  /**
   * @param csv the new {@link #get() value} with elements separated by the given {@code separator}.
   * @param separator the custom {@link #getSeparator() separator} to use (e.g. ";" for CSV).
   * @param enclose the custom {@link #isEncloseWithSeparator() enclose with separator flag}.
   * @param trim {@code true} if the elements from the given {@code csv} should be {@link String#trim() trimmed},
   *        {@code false} otherwise.
   */
  public void setCsv(String csv, String separator, boolean enclose, boolean trim) {

    requireWritable();
    Collection<String> collection = getCollection();
    Consumer<String> consumer = null;
    if (collection != null) {
      collection.clear();
      consumer = s -> collection.add(s);
    }
    String oldValue = get();
    String defaultSeparator = getSeparator();
    boolean defaultEnclose = isEncloseWithSeparator();
    String value = convertCsv(csv, separator, enclose, trim, defaultSeparator, defaultEnclose, consumer);
    doSet(oldValue, value);
  }

  private String empty(boolean enclose) {

    if (enclose) {
      return "";
    } else {
      return null;
    }
  }

  private String convertCsv(String csv, String csvSeparator, boolean csvEnclose, boolean trim, String targetSeparator,
      boolean targetEnclose, Consumer<String> consumer) {

    if (csv == null) {
      return null;
    }
    int csvLength = csv.length();
    if (csvLength == 0) {
      if (csvEnclose) {
        return empty(targetEnclose);
      }
      if (consumer != null) {
        consumer.accept("");
      }
      if (targetEnclose) {
        return targetSeparator + targetSeparator;
      } else {
        return csv; // ""
      }
    }
    int csvSeparatorLength = csvSeparator.length();
    int start = 0;
    if (csvEnclose) {
      assert (csv.startsWith(csvSeparator));
      start = csvSeparatorLength;
      assert (csv.endsWith(csvSeparator));
    }
    String result = null;
    // optimization if csv and target are compatible...
    if ((targetSeparator != null) && (!trim || csv.indexOf(' ') < 0) && targetSeparator.equals(csvSeparator)) {
      if (targetEnclose == csvEnclose) {
        result = csv;
      } else if (targetEnclose) {
        // convert to enclosed format
        result = targetSeparator + csv + targetSeparator;
      } else {
        // convert from enclosed format
        if (csvLength > csvSeparatorLength) {
          result = csv.substring(csvSeparatorLength, csvLength - csvSeparatorLength);
        } else {
          result = "";
        }
      }
    }
    StringBuilder sb = null;
    if (result == null) {
      if (targetSeparator != null) {
        sb = new StringBuilder(csvLength);
      }
    } else if (consumer == null) {
      if (csvLength <= csvSeparatorLength) {
        assert (result.isEmpty());
        return null;
      }
      return result;
    }
    String sep = "";
    if (targetEnclose) {
      sep = targetSeparator;
    }
    assert ((sb != null) || (consumer != null)); // otherwise this method call was pointless...
    do {
      int index = csv.indexOf(csvSeparator, start);
      String element;
      if (index < 0) {
        element = csv.substring(start);
        start = csvLength;
      } else {
        element = csv.substring(start, index);
        start = index + csvSeparatorLength;
      }
      if (trim) {
        element = element.trim();
      }
      if (consumer != null) {
        consumer.accept(element);
      }
      if (sb != null) {
        sb.append(sep);
        sep = targetSeparator;
        sb.append(element);
      }
    } while (start < csvLength);
    if (sb != null) {
      if (targetEnclose) {
        sb.append(targetSeparator);
      }
      result = sb.toString();
    }
    return result;
  }

  /**
   * @param <C> type of the {@link Collection}.
   * @param collection the {@link Collection} where to {@link #add(String) add} the elements from this property.
   * @return the given {@link Collection}.
   */
  protected <C extends Collection<String>> C collect(C collection) {

    String value = get();
    String separator = getSeparator();
    boolean enclose = isEncloseWithSeparator();
    convertCsv(value, separator, enclose, false, null, false, s -> collection.add(s));
    return collection;
  }

  @Override
  protected String readValue(StructuredReader reader, boolean apply) {

    if (reader.readStartArray()) {
      if (apply) {
        while (!reader.readEndArray()) {
          String element = reader.readValueAsString();
          add(element);
        }
        return get();
      } else {
        StringBuilder sb = new StringBuilder();
        String separator = getSeparator();
        boolean encloseWithSeparator = isEncloseWithSeparator();
        while (!reader.readEndArray()) {
          String element = reader.readValueAsString();
          if (sb.isEmpty()) {
            if (encloseWithSeparator) {
              sb.append(separator);
              sb.append(element);
              sb.append(separator);
            } else {
              sb.append(element);
            }
          } else if (encloseWithSeparator) {
            sb.append(element);
            sb.append(separator);
          } else {
            sb.append(separator);
            sb.append(element);
          }
        }
        return sb.toString();
      }
    } else {
      return super.readValue(reader, apply);
    }
  }

  @Override
  public void writeValue(StructuredWriter writer, String value) {

    writer.writeStartArray();
    String separator = getSeparator();
    boolean enclose = isEncloseWithSeparator();
    convertCsv(value, separator, enclose, false, null, false, s -> writer.writeValueAsString(s));
    writer.writeEnd();
  }

  @Override
  public Iterator<String> iterator() {

    String value = get();
    if (value == null) {
      return Collections.emptyIterator();
    }
    return new ValueIterator(value, getSeparator(), isEncloseWithSeparator());
  }

  private static class ValueIterator extends AbstractIterator<String> {

    private final String string;

    private final String separator;

    private final int end;

    private int index = 0;

    private ValueIterator(String string, String separator, boolean enclose) {

      super();
      this.string = string;
      this.separator = separator;
      this.end = string.length();
      if (enclose && string.startsWith(separator)) {
        this.index = separator.length();
      }
      findFirst();
    }

    @Override
    protected String findNext() {

      if (this.index >= this.end) {
        return null;
      }
      int i = this.string.indexOf(this.separator, this.index);
      if (i < 0) {
        i = this.end;
      }
      String result = this.string.substring(this.index, i);
      this.index = i + this.separator.length();
      if (this.index >= this.end) {
        this.index = this.end;
      }
      return result;
    }

  }

}
