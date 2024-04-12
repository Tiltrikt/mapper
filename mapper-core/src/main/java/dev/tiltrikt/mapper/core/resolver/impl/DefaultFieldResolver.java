package dev.tiltrikt.mapper.core.resolver.impl;

import dev.tiltrikt.mapper.core.resolver.AbstractFieldResolver;
import dev.tiltrikt.mapper.core.resolver.FieldResolver;
import java.lang.reflect.Field;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public final class DefaultFieldResolver extends AbstractFieldResolver implements FieldResolver {

  @Override
  public @NotNull Optional<Field> resolve(@NotNull Field sourceField, @NotNull Object target) {
    String sourceName = sourceField.getName();
    return findField(sourceName, target);
  }
}
