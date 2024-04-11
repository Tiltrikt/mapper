package dev.tiltrikt.mapper.core.resolver.impl;

import dev.tiltrikt.mapper.core.resolver.FieldResolver;
import java.lang.reflect.Field;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public final class DefaultFieldResolver implements FieldResolver {

  @Override
  public @NotNull Optional<Field> resolve(@NotNull Field sourceField, @NotNull Object target) {

    String sourceName = sourceField.getName();
    Field targetField = null;
    try {
      targetField = target.getClass().getDeclaredField(sourceName);
    } catch (NoSuchFieldException ignored) {
    }
    return Optional.ofNullable(targetField);
  }
}
