package dev.tiltrikt.mapper.core.schema.processor;

import dev.tiltrikt.mapper.core.exception.MappingSchemaException;
import dev.tiltrikt.mapper.core.schema.MappingSchema;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

/**
 * Responsible for processing mapping between source and target objects. Based on java reflection.
 */
public class MappingSchemaDefaultProcessor implements MappingSchemaProcessor {

  /**
   * Maps fields from a source object to a target object based on the provided {@link MappingSchema}.
   * It also recursively maps inner objects using {@code innerMappingSchemas} from {@link MappingSchema}.
   *
   * @param source The source object from which map fields.
   * @param target The target object to which map fields.
   * @param schema The mapping schema that defines the associations between fields.
   * @return The target object after mapping.
   * @throws MappingSchemaException If {@code MappingSchema} is incorrect.
   */
  @SneakyThrows
  @Override
  public <S, T> @NotNull T map(@NotNull S source,
                               @NotNull T target,
                               @NotNull MappingSchema schema) throws MappingSchemaException {

    for (Field sourceField : schema.getFieldsToMap()) {
      sourceField.setAccessible(true);
      Object object = sourceField.get(source);
      Field targetField = schema.getTargetField(sourceField);
      targetField.setAccessible(true);
      try {
        targetField.set(target, object);
      } catch (IllegalArgumentException e) {
        map(sourceField.get(source), targetField.get(target), schema.getInnerMappingSchema(sourceField));
      }
    }
    return target;
  }
}
