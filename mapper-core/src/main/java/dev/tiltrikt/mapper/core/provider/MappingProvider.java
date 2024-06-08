package dev.tiltrikt.mapper.core.provider;

import org.jetbrains.annotations.NotNull;

/**
 * Interface provides methods to map a source object to a target object.
 */
@FunctionalInterface
public interface MappingProvider<S, T> {

  /**
   * Maps the given source object to the specified target object.
   *
   * @param source The source object to map from.
   * @param target The target object to map to.
   * @return The target object after mapping.
   */
  @NotNull T map(@NotNull S source, @NotNull T target);

}
