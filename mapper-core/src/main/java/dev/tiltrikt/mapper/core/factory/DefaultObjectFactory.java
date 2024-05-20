package dev.tiltrikt.mapper.core.factory;

import dev.tiltrikt.mapper.core.exception.MissingConstructorException;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

/**
 * A default implementation of the {@code ObjectFactory} interface which creates objects
 * of classes using their default (no-argument) constructor. Based on java reflection.
 */
public class DefaultObjectFactory implements ObjectFactory {

  /**
   * Creates an instance of the specified class using its default constructor.
   *
   * @param targetClass the class of the object to be created.
   * @return a new instance of the specified class.
   * @throws MissingConstructorException If the specified class does not have a default (no-argument) constructor.
   */
  @Override
  @SneakyThrows
  public <T> @NotNull T createInstance(@NotNull Class<T> targetClass) throws MissingConstructorException {
    try {
      Constructor<T> constructor = targetClass.getDeclaredConstructor();
      return constructor.newInstance();
    } catch (NoSuchMethodException exception) {
      throw new MissingConstructorException(
          "Can't create an instance of %s. Missing no arguments constructor.",
          targetClass.getSimpleName()
      );
    }
  }
}
