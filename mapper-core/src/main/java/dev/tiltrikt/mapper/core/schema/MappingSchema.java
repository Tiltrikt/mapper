package dev.tiltrikt.mapper.core.schema;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Interface defining a mapping schema, which specifies how source and target fields are associated.
 */
public interface MappingSchema {

  /**
   * Retrieves the target field associated with the provided source field.
   *
   * @param sourceField The source field for which the target field is to be retrieved.
   * @return The target field associated with the provided source field.
   */
  @NotNull Field getTargetField(@NotNull Field sourceField);

  /**
   * Retrieves a list of all source fields present in the mapping schema.
   *
   * @return A list containing all source fields present in the mapping schema.
   */
  @NotNull List<Field> getFieldsToMap();
}
