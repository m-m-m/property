package io.github.mmm.property.criteria;

/**
 * Interface for a factory providing instances of {@link CriteriaFormatter}.
 */
@FunctionalInterface
public interface CriteriaFormatterFactory {

  /**
   * @return a new {@link CriteriaFormatter} instance.
   */
  CriteriaFormatter create();

}
