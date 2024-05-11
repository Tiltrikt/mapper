package dev.tiltrikt.mapper.core.schema.resolver;

import dev.tiltrikt.mapper.core.schema.MappingSchema;
import org.jetbrains.annotations.NotNull;

public interface MappingSchemaResolver {

  <S, T> MappingSchema resolve(@NotNull S source, @NotNull T target);
}
