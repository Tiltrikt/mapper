package dev.tiltrikt.mapper.core.resolver;

import java.lang.reflect.Field;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractFieldResolver {

  public @NotNull Optional<Field> findField(@NotNull String sourceName, @NotNull Object target) {

    Class<?> targetClass = target.getClass();
    Optional<Field> resolvedField;
    do {
      resolvedField = findFieldInClass(sourceName, targetClass);
      targetClass = targetClass.getSuperclass();
    } while (!targetClass.equals(Object.class) && resolvedField.isEmpty());

    return resolvedField;
  }

  private @NotNull Optional<Field> findFieldInClass(@NotNull String sourceName, @NotNull Class<?> clazz) {
    try {
      return Optional.of(clazz.getDeclaredField(sourceName));
    } catch (NoSuchFieldException e) {
      return Optional.empty();
    }
  }
}
