package dev.tiltrikt.mapper.core;

import dev.tiltrikt.mapper.core.schema.MappingSchema;
import org.jetbrains.annotations.NotNull;

public interface Mapper {

  @NotNull <S, T> T map(@NotNull S source, Class<T> targetClass);

  @NotNull <S, T> T map(@NotNull S source, @NotNull T target);

  @NotNull <S, T> T map(@NotNull S source, @NotNull Class<T> target, @NotNull MappingSchema schema);

  @NotNull <S, T> T map(@NotNull S source, @NotNull T target, @NotNull MappingSchema schema);
}
