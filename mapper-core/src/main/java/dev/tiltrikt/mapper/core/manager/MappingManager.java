package dev.tiltrikt.mapper.core.manager;

import dev.tiltrikt.mapper.core.exception.ProviderNotFoundException;
import org.jetbrains.annotations.NotNull;

/**
 * Manages providers in order to provide suitable one for mapping given objects.
 */
public interface MappingManager {

  /**
   * Delegates map operation to suitable provider.
   *
   * @param source The source object to map from.
   * @param target The target object to map to.
   * @return The target object after mapping.
   * @throws ProviderNotFoundException if no suitable provider is found for the source and target types.
   */
  @NotNull <S, T> T map(@NotNull S source, @NotNull T target) throws ProviderNotFoundException;
}
