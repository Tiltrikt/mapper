package dev.tiltrikt.mapper.core.schema.processor;

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
   *
   * @param source The source object from which map fields.
   * @param target The target object to which map fields.
   * @param schema The mapping schema that defines the associations between fields.
   * @return The target object after mapping.
   */
  @SneakyThrows
  @Override
  public <S, T> @NotNull T map(@NotNull S source, @NotNull T target, @NotNull MappingSchema schema) {

    for (Field sourceField : schema.getFieldsToMap()) {
      sourceField.setAccessible(true);
      Object object = sourceField.get(source);
      Field targetField = schema.getTargetField(sourceField);
      targetField.setAccessible(true);
      targetField.set(target, object);
    }
    return target;
  }
}
