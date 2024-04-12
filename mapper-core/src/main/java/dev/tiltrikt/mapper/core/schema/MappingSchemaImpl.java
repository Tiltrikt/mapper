package dev.tiltrikt.mapper.core.schema;

import dev.tiltrikt.mapper.core.exception.MappingSchemaException;
import java.util.Map;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MappingSchemaImpl<S, T> implements MappingSchema<S, T> {

  @NotNull Class<S> sourceClass;

  @NotNull Class<T> targetClass;

  @NotNull Map<String, String> fieldMappingSchema;

  @Override
  public boolean shouldBeMapped(@NotNull String sourceField) {
    return fieldMappingSchema.containsKey(sourceField);
  }

  @Override
  public @NotNull String getTargetField(@NotNull String sourceField) throws MappingSchemaException {
    return Optional.ofNullable(fieldMappingSchema.get(sourceField))
        .orElseThrow(
            () -> new MappingSchemaException("No targets found for field %w", sourceField)
        );
  }

}
