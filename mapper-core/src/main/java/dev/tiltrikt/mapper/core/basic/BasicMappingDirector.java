package dev.tiltrikt.mapper.core.basic;

import org.jetbrains.annotations.NotNull;

/**
 * An interface representing a basic mapping director for mapping objects from a source type to a target type.
 */
public interface BasicMappingDirector {

  /**
   * Directs mapping properties from a source object to a target object.
   *
   * @param source The source object to map from.
   * @param target The target object to map to.
   * @return The target object after mapping.
   */
  <S, T> @NotNull T map(@NotNull S source, @NotNull T target);
}
