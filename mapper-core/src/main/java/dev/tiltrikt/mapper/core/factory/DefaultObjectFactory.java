package dev.tiltrikt.mapper.core.factory;

import dev.tiltrikt.mapper.core.exception.MissingConstructorException;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

public class DefaultObjectFactory implements ObjectFactory {

  @Override
  @SneakyThrows
  public <T> @NotNull T createInstance(@NotNull Class<T> targetClass) {
    try {
      Constructor<T> constructor = targetClass.getDeclaredConstructor();
      return constructor.newInstance();
    } catch (NoSuchMethodException ignored) {
      throw new MissingConstructorException(
          "Can't create an instance of %s. Missing no arguments constructor.",
          targetClass.getSimpleName()
      );
    }
  }
}
