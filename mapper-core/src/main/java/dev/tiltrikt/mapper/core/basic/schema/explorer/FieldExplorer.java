package dev.tiltrikt.mapper.core.basic.schema.explorer;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

/**
 * Provides methods for getting information about field.
 */
public interface FieldExplorer {

  /**
   * Determines whether a field should be mapped.
   *
   * @param field The field to be checked for mapping.
   * @return true if the field should be mapped, false otherwise.
   */
  boolean shouldBeMapped(@NotNull Field field);

  @NotNull
  String getMappingName(@NotNull Field field);
}
