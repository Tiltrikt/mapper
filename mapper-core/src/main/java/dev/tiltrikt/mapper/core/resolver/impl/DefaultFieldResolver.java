package dev.tiltrikt.mapper.core.resolver.impl;

import dev.tiltrikt.mapper.core.resolver.AbstractFieldResolver;
import dev.tiltrikt.mapper.core.resolver.FieldResolver;
import java.lang.reflect.Field;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public final class DefaultFieldResolver extends AbstractFieldResolver implements FieldResolver {

  @Override
  public @NotNull Optional<Field> resolve(@NotNull Field sourceField, @NotNull Object target) {

    Class<?> targetClass = target.getClass();
    Optional<Field> resolvedField;
    String sourceName = sourceField.getName();

    do {
      resolvedField = findField(sourceName, targetClass);
      targetClass = targetClass.getSuperclass();
    } while (resolvedField.isEmpty() && targetClass != null);

    return resolvedField;
  }
}
