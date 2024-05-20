package dev.tiltrikt.mapper.core.schema;

import dev.tiltrikt.mapper.core.exception.MappingSchemaException;

import java.lang.reflect.Field;
import java.util.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * Class as wrapper of {@code Map<Field, Field>}. Represents schema as associations between
 * source and target fields. Used for mapping.
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MappingSchemaImpl implements MappingSchema {

  /** The mapping schema storing associations between source and target fields. */
  @NotNull Map<Field, Field> fieldMappingSchema;

  /**
   * Retrieves the target field associated with the provided source field.
   *
   * @param sourceField The source field for which the target field is to be retrieved.
   * @return The target field associated with the provided source field.
   * @throws MappingSchemaException If no target field is found for the provided source field.
   */
  @Override
  public @NotNull Field getTargetField(@NotNull Field sourceField) {
    return Optional.ofNullable(fieldMappingSchema.get(sourceField))
        .orElseThrow(
            () -> new MappingSchemaException("No targets found for field %s", sourceField.getName())
        );
  }

  /**
   * Retrieves a list of all source fields present in the mapping schema.
   *
   * @return A list containing all source fields present in the mapping schema.
   */
  @Override
  public @NotNull List<Field> getFieldsToMap() {
    return new ArrayList<>(fieldMappingSchema.keySet());
  }
}
