package dev.tiltrikt.mapper.core.resolver.impl;

import dev.tiltrikt.mapper.core.annotation.FieldMapping;
import dev.tiltrikt.mapper.core.exception.MapperException;
import dev.tiltrikt.mapper.core.resolver.AbstractFieldResolver;
import dev.tiltrikt.mapper.core.resolver.FieldResolver;
import java.lang.reflect.Field;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public final class AnnotationFieldResolver extends AbstractFieldResolver implements FieldResolver {

  @Override
  public @NotNull Optional<Field> resolve(@NotNull Field sourceField, @NotNull Object target) {

    String sourceName;
    try {
       sourceName = sourceField.getAnnotation(FieldMapping.class).targetName();
    } catch (NullPointerException e) {
      throw new MapperException("No field with FieldMapping annotation");
    }

    Optional<Field> resolvedField = findField(sourceName, target);

    if(resolvedField.isEmpty()) {
      throw new MapperException("No field with name %s in target class", sourceField);
    }
    return resolvedField;
  }
}
