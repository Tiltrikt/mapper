package dev.tiltrikt.mapper.core.schema.resolver;

import dev.tiltrikt.mapper.core.exception.MappingSchemaException;
import dev.tiltrikt.mapper.core.schema.MappingSchema;
import dev.tiltrikt.mapper.core.schema.MappingSchemaImpl;
import dev.tiltrikt.mapper.core.schema.explorer.ClassExplorer;
import dev.tiltrikt.mapper.core.schema.explorer.ClassExplorerImpl;
import dev.tiltrikt.mapper.core.schema.explorer.FieldExplorer;
import dev.tiltrikt.mapper.core.schema.explorer.FieldExplorerImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Responsible for creating {@link MappingSchema} between source and target objects.<br>
 *
 * @see FieldExplorer
 * @see ClassExplorer
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MappingSchemaDefaultResolver implements MappingSchemaResolver {

  @NotNull
  FieldExplorer fieldExplorer = new FieldExplorerImpl();

  @NotNull
  ClassExplorer classExplorer = new ClassExplorerImpl();


  /**
   * Resolves a mapping schema between source and target objects.
   * If a target field cannot be found no exception is thrown, but a warning message is logged.
   * It goes recursively through all inner field and creates additional mapping schemas in case of need.
   *
   * @param source The object from which fields will be mapped.
   * @param target The object to which fields will be mapped.
   * @return A {@link MappingSchema} representing relation between source and target fields.
   */
  @Override
  public <S, T> MappingSchema resolve(@NotNull S source, @NotNull T target) {

    return resolve(source.getClass(), target.getClass());
  }

  /**
   * Resolves a mapping schema between source and target classes.
   * If a target field cannot be found no exception is thrown, but a warning message is logged.<br>
   * It goes recursively through all inner field and creates additional mapping schemas in case of need.
   *
   * @param sourceClass The class from which fields will be mapped.
   * @param targetClass The class to which fields will be mapped.
   * @return A {@link MappingSchema} representing relation between source and target fields.
   */
  @Override
  public <S, T> MappingSchema resolve(@NotNull Class<S> sourceClass, @NotNull Class<T> targetClass) {

    Map<Field, Field> fieldMappingSchema = new HashMap<>();
    Map<Field, MappingSchema> innerMappingSchemas = new HashMap<>();

    List<Field> sourceFieldsList = classExplorer.getMappableFields(sourceClass);
    List<Field> targetFieldsList = classExplorer.getMappableFields(targetClass);

    for (Field sourceField : sourceFieldsList) {
      try {
        Field targetField = getTargetField(sourceField, targetFieldsList);
        fieldMappingSchema.put(sourceField, targetField);

        Map<Field, MappingSchema> innerFieldMappingSchemaForField = resolveInnerFieldMappingSchema(sourceField, targetField);
        if (innerFieldMappingSchemaForField != null) {
          innerMappingSchemas.putAll(innerFieldMappingSchemaForField);
        }
      } catch (MappingSchemaException e) {
        log.warn(e.getMessage());
      }
    }

    return new MappingSchemaImpl(fieldMappingSchema, innerMappingSchemas);
  }

  /**
   * Retrieves the target field corresponding to the provided source field.
   *
   * @param sourceField     The source field for which the target field is to be retrieved.
   * @param targetFieldsList The list of available for mapping fields present in {@code Target.class}.
   * @return The target field corresponding to the provided source field.
   * @throws MappingSchemaException If no target field is found for the provided source field.
   */
  private @NotNull Field getTargetField(@NotNull Field sourceField,
                                        @NotNull List<Field> targetFieldsList) {

    String sourceFieldName = fieldExplorer.getMappingName(sourceField);
    return targetFieldsList
        .stream()
        .filter(field -> fieldExplorer.getMappingName(field).equals(sourceFieldName))
        .findFirst()
        .orElseThrow(() -> new MappingSchemaException("Cannot find target field for: %s", sourceFieldName));
  }

  /**
   * Resolves the inner field mapping schema between the source and target fields.
   * If the types of the source and target fields are the same, returns {@code null}.
   * It means that no additional actions are required to map these fields.
   * Otherwise, it resolves a {@link MappingSchema} between the types of the source and target fields.
   *
   * @param sourceField the source {@link Field} to be mapped, must not be {@code null}
   * @param targetField the target {@link Field} to be mapped, must not be {@code null}
   * @return a map containing the source field and its corresponding {@link MappingSchema},
   *         or {@code null} if the source and target fields have the same type
   */
  private @Nullable Map<Field, MappingSchema> resolveInnerFieldMappingSchema(@NotNull Field sourceField, @NotNull Field targetField) {
    if (sourceField.getType().equals(targetField.getType())) {
      return null;
    }

    MappingSchema mappingSchema = resolve(sourceField.getType(), targetField.getType());
    return Map.of(sourceField, mappingSchema);
  }
}
