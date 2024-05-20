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
   *
   * @param source The object from which fields will be mapped.
   * @param target The object to which fields will be mapped.
   * @return A {@link MappingSchema} representing relation between source and target fields.
   */
  @Override
  public <S, T> MappingSchema resolve(@NotNull S source,
                                      @NotNull T target) {

    Map<Field, Field> fieldMappingSchema = new HashMap<>();

    List<Field> sourceFieldsList = classExplorer.getMappableFields(source.getClass());
    List<Field> targetFieldsList = classExplorer.getMappableFields(target.getClass());

    for (Field sourceField : sourceFieldsList) {
      try {
        fieldMappingSchema.put(sourceField, getTargetField(sourceField, targetFieldsList));
      } catch (MappingSchemaException e) {
        log.warn(e.getMessage());
      }
    }

    return new MappingSchemaImpl(fieldMappingSchema);
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
}
