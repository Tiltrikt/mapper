package dev.tiltrikt.mapper.core.schema.processor;

import dev.tiltrikt.mapper.core.schema.MappingSchema;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public class MappingSchemaProcessorImpl implements MappingSchemaProcessor {

  @SneakyThrows
  @Override
  public <S, T> @NotNull T map(@NotNull S source, @NotNull T target, @NotNull MappingSchema schema) {

    for (Field sourceField : schema.getFieldsToMap()) {
      Object object = sourceField.get(source);
      Field targetField = schema.getTargetField(sourceField);
      targetField.setAccessible(true);
      targetField.set(target, object);
    }
    return target;
  }
}
