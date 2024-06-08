package dev.tiltrikt.mapper.core.basic.schema.explorer;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Provides methods for getting information about class.
 */
public interface ClassExplorer {

  /**
   * Retrieves a list of mappable fields within the given target class and its superclasses.
   * @param targetClass The class to retrieve the mappable fields.
   * @return A list of mappable fields within the target class and its superclasses.
   */
  @NotNull List<Field> getMappableFields(@NotNull Class<?> targetClass);
}
