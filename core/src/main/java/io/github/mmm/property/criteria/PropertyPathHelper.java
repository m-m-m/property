/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.property.criteria;

import io.github.mmm.value.PropertyPath;
import io.github.mmm.value.ReadablePath;
import io.github.mmm.value.WritablePath;

/**
 * Little helper class for simple reusable operations on {@link PropertyPath} and related types. Designed for framework
 * internal reuse and not intended for external users.
 *
 * @since 1.0.0
 */
public final class PropertyPathHelper {

  private PropertyPathHelper() {

  }

  /**
   * @param path1 the first {@link ReadablePath} to compare.
   * @param path2 the second {@link ReadablePath} to compare.
   * @return {@code true} if both {@link ReadablePath paths} are logically equal, {@code false} otherwise.
   */
  public static boolean isEqualPath(ReadablePath path1, ReadablePath path2) {

    return isEqualPath(path1, path2, false);
  }

  /**
   * @param path1 the first {@link ReadablePath} to compare.
   * @param path2 the second {@link ReadablePath} to compare.
   * @param ignorePath1Alias - {@code true} to ignore the top-level {@link ReadablePath#pathSegment() segment} of
   *        {@code path1} as it is considered to be an alias, {@code false} to require all segments to be equal.
   * @return {@code true} if both {@link ReadablePath paths} are logically equal, {@code false} otherwise.
   */
  public static boolean isEqualPath(ReadablePath path1, ReadablePath path2, boolean ignorePath1Alias) {

    if ((path2 == null) || (path1 == null)) {
      if (ignorePath1Alias && (path1 instanceof SimplePath) && (path1.parentPath() == null)) {
        return true;
      }
      return (path2 == path1);
    } else if (!path1.pathSegment().equals(path2.pathSegment())) {
      return false;
    }
    return isEqualPath(getParent(path1), getParent(path2), ignorePath1Alias);
  }

  /**
   * @param path the {@link ReadablePath} to get the parent of.
   * @return the real {@link ReadablePath#parentPath() parent} property.
   */
  public static ReadablePath getParent(ReadablePath path) {

    if (path == null) {
      return null;
    }
    ReadablePath parent = path.parentPath();
    if (parent instanceof WritablePath) {
      // magic detection to skip beans
      return parent.parentPath();
    }
    return parent;
  }

}
