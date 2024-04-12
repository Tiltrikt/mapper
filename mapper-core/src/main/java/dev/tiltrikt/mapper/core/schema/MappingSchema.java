package dev.tiltrikt.mapper.core.schema;

import dev.tiltrikt.mapper.core.exception.MappingSchemaException;
import org.jetbrains.annotations.NotNull;

public interface MappingSchema<S, T> {

  boolean shouldBeMapped(@NotNull String sourceField);

  @NotNull String getTargetField(@NotNull String sourceField) throws MappingSchemaException;

  @NotNull Class<S> getSourceClass();

  @NotNull Class<T> getTargetClass();
}
