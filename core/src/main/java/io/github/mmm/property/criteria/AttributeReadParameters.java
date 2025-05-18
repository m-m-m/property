package io.github.mmm.property.criteria;

/**
 * Interface to {@link #getParameters() read} the {@link #getParameters() parameters}.
 */
public interface AttributeReadParameters {

  /**
   * @return the {@link CriteriaParameters}.
   */
  CriteriaParameters<?> getParameters();
}
