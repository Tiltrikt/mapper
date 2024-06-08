package dev.tiltrikt.mapper.core.basic.schema;

import dev.tiltrikt.mapper.core.exception.MappingSchemaException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Represents schema as associations between source and target fields. Association could be represented
 * as wrapper of {@code Map<Field, Field>}. Could also contain inner mapping schemas for inner mapping.
 * Used for mapping.
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MappingSchemaImpl implements MappingSchema {

  /** <p>The mapping schema storing associations between source and target fields.</p>
   * Use case:
   * <pre>{@code
   *
   *    Source class:
   *
   *    public class User {
   *      Address address;
   *    }
   *
   *    Target class:
   *
   *    public class UserDto {
   *      Address address;
   *    }
   *
   * }</pre>
   */
  @NotNull Map<Field, Field> fieldMappingSchema;

  /** <p>Inner mapping schemas that provide instructions how inner fields with different classes should be mapped.</p>
   *
   * Use case:
   * <pre>{@code
   *
   *    Source class:
   *
   *    public class User {
   *      Address address;
   *    }
   *
   *    Target class:
   *
   *    public class UserDto {
   *      AddressDto address;
   *    }
   *
   * }</pre>
   */
  @NotNull Map<Field, MappingSchema> innerMappingSchemas;

  /**
   * Constructs a new instance of {@code MappingSchemaImpl} with the specified field mapping schema.
   * Allows to use {@code MappingSchemaImpl} without specifying {@code innerMappingSchemas}.
   *
   * @param fieldMappingSchema The mapping schema storing associations between source and target fields.
   */
  public MappingSchemaImpl(@NotNull Map<Field, Field> fieldMappingSchema) {
    this.fieldMappingSchema = fieldMappingSchema;
    this.innerMappingSchemas = new HashMap<>();
  }

  /**
   * Retrieves the target field associated with the provided source field.
   *
   * @param sourceField The source field for which the target field is to be retrieved.
   * @return The target field associated with the provided source field.
   * @throws MappingSchemaException If no target field is found for the provided source field.
   */
  @Override
  public @NotNull Field getTargetField(@NotNull Field sourceField) throws MappingSchemaException {
    return Optional.ofNullable(fieldMappingSchema.get(sourceField))
        .orElseThrow(
            () -> new MappingSchemaException("No targets found for field %s", sourceField.getName())
        );
  }

  /**
   * Retrieves the target mapping schema associated with the provided source field.
   *
   * @param sourceField The source field for which the target mapping schema is to be retrieved.
   * @return The inner mapping schema associated with the provided source field.
   * @throws MappingSchemaException If no mapping schema is found for the provided source field.
   */
  @Override
  public @NotNull MappingSchema getInnerMappingSchema(@NotNull Field sourceField) throws MappingSchemaException {
    return Optional.ofNullable(innerMappingSchemas.get(sourceField))
        .orElseThrow(
            () -> new MappingSchemaException("No target mapping schema found for field %s", sourceField.getName())
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
