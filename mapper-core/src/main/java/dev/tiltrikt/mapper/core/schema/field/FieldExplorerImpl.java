package dev.tiltrikt.mapper.core.schema.field;

import dev.tiltrikt.mapper.core.annotation.FieldMapping;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public class FieldExplorerImpl implements FieldExplorer {

  @Override
  public boolean shouldBeMapped(@NotNull Field field) {
    return !field.getAnnotation(FieldMapping.class).ignore();
  }

  @Override
  public @NotNull String getMappingName(@NotNull Field field) {
    String annotationName = field.getAnnotation(FieldMapping.class).targetName();
    return annotationName.equals(FieldMapping.EMPTY_TARGET) ? field.getName() : annotationName;
  }
}
