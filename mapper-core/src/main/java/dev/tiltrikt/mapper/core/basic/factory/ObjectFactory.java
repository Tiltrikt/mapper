package dev.tiltrikt.mapper.core.basic.factory;

import dev.tiltrikt.mapper.core.exception.MissingConstructorException;
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
   * @throws MissingConstructorException If the specified class does not have a default (no-argument) constructor.
   */
  @NotNull
  <T> T createInstance(@NotNull Class<T> targetClass) throws MissingConstructorException;
}
