package dev.tiltrikt.mapper.core.resolver;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Optional;

public abstract class AbstractFieldResolver {

  public @NotNull Optional<Field> findField(@NotNull String sourceName, @NotNull Class<?> targetClass) {

      Field targetField = null;
      try {
        targetField = targetClass.getDeclaredField(sourceName);
      } catch (NoSuchFieldException ignored) {
      }
      return Optional.ofNullable(targetField);
  }
}
