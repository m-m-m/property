package io.github.mmm.property.range;

import io.github.mmm.base.range.Range;
import io.github.mmm.value.converter.CompositeTypeMapper;

/**
 * Implementation of {@link CompositeTypeMapper} for {@link Range}. It will decompose the {@link Range} into its two
 * bounds: {@link Range#getMin() min} and {@link Range#getMax() max}.
 *
 * @param <V> type of the {@link Range} bounds.
 * @since 1.0.0
 */
public abstract class RangeTypeMapper<V extends Comparable<?>> extends CompositeTypeMapper<Range<V>, V> {

  private final Class<? extends V> targetType;

  /**
   * The constructor.
   *
   * @param targetType the {@link #getTargetType() target type}.
   * @param next the {@link #next()} mapper.
   * @param suffix the {@link #getSuffix() suffix}.
   */
  public RangeTypeMapper(Class<? extends V> targetType, RangeTypeMapper<V> next, String suffix) {

    super(suffix, next);
    this.targetType = targetType;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public Class<? extends Range<V>> getSourceType() {

    return (Class) Range.class;
  }

  @Override
  public Class<? extends V> getTargetType() {

    return this.targetType;
  }

  private static class MaxMapper<V extends Comparable<?>> extends RangeTypeMapper<V> {

    private MaxMapper(Class<? extends V> targetType) {

      super(targetType, null, "Max");
    }

    @Override
    public V toTarget(Range<V> source) {

      if (source == null) {
        return null;
      }
      return source.getMax();
    }
  }

  private static class MinMapper<V extends Comparable<?>> extends RangeTypeMapper<V> {

    private MinMapper(MaxMapper<V> max) {

      super(max.getTargetType(), max, "Min");
    }

    @Override
    public V toTarget(Range<V> source) {

      if (source == null) {
        return null;
      }
      return source.getMin();
    }
  }

  /**
   * @param <V> type of the {@link Range} bounds.
   * @param valueType the {@link Class} reflecting the {@link Range} bounds.
   * @return the {@link RangeTypeMapper} instance.
   */
  public static <V extends Comparable<?>> RangeTypeMapper<V> of(Class<V> valueType) {

    return new MinMapper<>(new MaxMapper<>(valueType));
  }

}
