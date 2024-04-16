package dev.tiltrikt.mapper.core.schema.resolver;

import dev.tiltrikt.mapper.core.exception.MappingSchemaException;
import dev.tiltrikt.mapper.core.schema.MappingSchema;
import dev.tiltrikt.mapper.core.schema.MappingSchemaImpl;
import dev.tiltrikt.mapper.core.schema.field.FieldExplorer;
import dev.tiltrikt.mapper.core.schema.field.FieldExplorerImpl;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MappingSchemaDefaultResolver implements MappingSchemaResolver {

  @NotNull FieldExplorer fieldExplorer = new FieldExplorerImpl();

  @Override
  public <S, T> MappingSchema resolve(@NotNull S source, @NotNull T target) {

    Map<Field, Field> fieldMappingSchema = new HashMap<>();

    List<Field> sourceFieldsList = getAllFields(source.getClass());
    List<Field> targetFieldsList = getAllFields(target.getClass());

    for (Field sourceField : sourceFieldsList) {

      if (!fieldExplorer.shouldBeMapped(sourceField)) {
        continue;
      }

      String sourceFieldName = fieldExplorer.getMappingName(sourceField);
      Field targetField = targetFieldsList
          .stream()
          .filter(field -> fieldExplorer.getMappingName(sourceField).equals(sourceFieldName))
          .findFirst()
          .orElseThrow(() -> new MappingSchemaException("Cannot find target field for %s", sourceFieldName));

      if (!fieldExplorer.shouldBeMapped(targetField)) {
        continue;
      }

      fieldMappingSchema.put(sourceField, targetField);
    }

    return new MappingSchemaImpl(fieldMappingSchema);
  }

  private @NotNull List<Field> getAllFields(@NotNull Class<?> targetClass) {

    List<Field> fieldMap = new ArrayList<>();
    while (!targetClass.equals(Object.class)) {
      fieldMap.addAll(List.of(targetClass.getDeclaredFields()));
      targetClass = targetClass.getSuperclass();
    }
    return fieldMap;
  }
}
