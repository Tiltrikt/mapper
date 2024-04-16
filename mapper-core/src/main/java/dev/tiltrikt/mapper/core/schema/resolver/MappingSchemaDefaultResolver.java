package dev.tiltrikt.mapper.core.schema.resolver;

import dev.tiltrikt.mapper.core.annotation.FieldMapping;
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

    List<Field> sourceFieldsList = getMappableFields(source.getClass());
    List<Field> targetFieldsList = getMappableFields(target.getClass());

    for (Field sourceField : sourceFieldsList) {

      String sourceFieldName = fieldExplorer.getMappingName(sourceField);
      Field targetField = targetFieldsList
          .stream()
          .filter(field -> fieldExplorer.getMappingName(sourceField).equals(sourceFieldName))
          .findFirst()
          .orElseThrow(() -> new MappingSchemaException("Cannot find target field for %s", sourceFieldName));

      fieldMappingSchema.put(sourceField, targetField);
    }

    return new MappingSchemaImpl(fieldMappingSchema);
  }


  private @NotNull List<Field> getMappableFields(@NotNull Class<?> targetClass) {

    List<Field> fieldMap = new ArrayList<>();
    while (!targetClass.equals(Object.class)) {
      fieldMap.addAll(List.of(targetClass.getDeclaredFields()));
      targetClass = targetClass.getSuperclass();
    }
    return fieldMap
        .stream()
        .filter(field -> !field.getAnnotation(FieldMapping.class).ignore())
        .toList();
  }
}
