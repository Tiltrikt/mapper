package dev.tiltrikt.mapper.core.resolver.impl;

import dev.tiltrikt.mapper.core.annotation.FieldMapping;
import dev.tiltrikt.mapper.core.exception.MapperException;
import dev.tiltrikt.mapper.core.resolver.FieldResolver;
import java.lang.reflect.Field;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public final class AnnotationFieldResolver implements FieldResolver {

  @Override
  public @NotNull Optional<Field> resolve(@NotNull Field sourceField, @NotNull Object target) {

    String sourceName = sourceField.getAnnotation(FieldMapping.class).targetName();
    Field targetField;
    try {
      targetField = target.getClass().getDeclaredField(sourceName);
    } catch (NoSuchFieldException e) {
      throw new MapperException("No field with name %s in target class", sourceField);
    }
    return Optional.of(targetField);
  }
}
