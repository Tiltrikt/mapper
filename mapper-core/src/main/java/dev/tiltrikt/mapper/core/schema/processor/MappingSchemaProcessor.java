package dev.tiltrikt.mapper.core.schema.processor;

import dev.tiltrikt.mapper.core.schema.MappingSchema;
import org.jetbrains.annotations.NotNull;

public interface MappingSchemaProcessor {

  @NotNull <S, T> T map(@NotNull S source, @NotNull T target, @NotNull MappingSchema schema);
}
