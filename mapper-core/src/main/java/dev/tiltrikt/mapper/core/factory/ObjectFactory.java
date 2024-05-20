package dev.tiltrikt.mapper.core.factory;

import org.jetbrains.annotations.NotNull;

/**
 * Interface for creating object of specified class.
 */
public interface ObjectFactory {

  /**
   * Creates an object of the specified class.
   *
   * @param targetClass the class of the object to be created.
   * @return a new instance of the specified class.
   */
  @NotNull
  <T> T createInstance(@NotNull Class<T> targetClass);
}
