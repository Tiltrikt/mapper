package dev.tiltrikt.mapper.core.schema.explorer;

import dev.tiltrikt.mapper.core.annotation.FieldMapping;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

/**
 * Provides methods for getting information about field. Based on java reflection.
 */
public class FieldExplorerImpl implements FieldExplorer {

  /**
   * Determines whether a field should be mapped based on the presence of the {@code @FieldMapping} annotation
   * and its <i>ignore</i> attribute.<br>
   * If field doesn't have {@code @FieldMapping} annotation it is considered as eligible for mapping.
   *
   * @param field The field to be checked for mapping.
   * @return false if field have {@code @FieldMapping} annotation with {@code ignore=true} attribute, true otherwise.
   */
  @Override
  public boolean shouldBeMapped(@NotNull Field field) {
    if (!field.isAnnotationPresent(FieldMapping.class)) {
      return true;
    }
    return !field.getAnnotation(FieldMapping.class).ignore();
  }

  /**
   * Retrieves the mapping name for a given field. If the {@code @FieldMapping} annotation is present on the field,
   * the <i>targetName</i> attribute value is returned; otherwise, the name of the field itself is returned.
   *
   * @param field The field for which the mapping name is requested.
   * @return The mapping name for the field.
   */
  @Override
  public @NotNull String getMappingName(@NotNull Field field) {
    if (!field.isAnnotationPresent(FieldMapping.class)) {
      return field.getName();
    }
    String annotationName = field.getAnnotation(FieldMapping.class).targetName();
    return annotationName.equals(FieldMapping.EMPTY_TARGET) ? field.getName() : annotationName;
  }
}
