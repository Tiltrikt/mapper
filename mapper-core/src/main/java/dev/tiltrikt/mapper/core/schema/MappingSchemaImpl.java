package dev.tiltrikt.mapper.core.schema;

import dev.tiltrikt.mapper.core.exception.MappingSchemaException;

import java.lang.reflect.Field;
import java.util.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MappingSchemaImpl implements MappingSchema {

  @NotNull Map<Field, Field> fieldMappingSchema;

  @Override
  public @NotNull Field getTargetField(@NotNull Field sourceField) throws MappingSchemaException {
    return Optional.ofNullable(fieldMappingSchema.get(sourceField))
        .orElseThrow(
            () -> new MappingSchemaException("No targets found for field %w", sourceField.getName())
        );
  }

  public @NotNull List<Field> getFieldsToMap() {
    return new ArrayList<>(fieldMappingSchema.keySet());
  }
}