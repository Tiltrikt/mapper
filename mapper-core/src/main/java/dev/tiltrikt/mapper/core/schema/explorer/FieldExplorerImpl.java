package dev.tiltrikt.mapper.core.schema.explorer;

import dev.tiltrikt.mapper.core.annotation.FieldMapping;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public class FieldExplorerImpl implements FieldExplorer {

  @Override
  public boolean shouldBeMapped(@NotNull Field field) {
    if (!field.isAnnotationPresent(FieldMapping.class)) {
      return true;
    }
    return !field.getAnnotation(FieldMapping.class).ignore();
  }

  @Override
  public @NotNull String getMappingName(@NotNull Field field) {
    if (!field.isAnnotationPresent(FieldMapping.class)) {
      return field.getName();
    }
    String annotationName = field.getAnnotation(FieldMapping.class).targetName();
    return annotationName.equals(FieldMapping.EMPTY_TARGET) ? field.getName() : annotationName;
  }
}