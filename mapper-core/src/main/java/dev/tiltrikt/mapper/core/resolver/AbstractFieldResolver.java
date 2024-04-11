package dev.tiltrikt.mapper.core.resolver;

import java.lang.reflect.Field;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractFieldResolver {

  public @NotNull Optional<Field> findField(@NotNull String sourceName, @NotNull Object target) {

    Class<?> targetClass = target.getClass();
    Optional<Field> resolvedField;
    do {
      Field targetField = null;
      try {
        targetField = targetClass.getDeclaredField(sourceName);
      } catch (NoSuchFieldException ignored) {
      }
      resolvedField = Optional.ofNullable(targetField);
      targetClass = targetClass.getSuperclass();
    } while (resolvedField.isEmpty() && targetClass != null);
    return resolvedField;
  }
}
