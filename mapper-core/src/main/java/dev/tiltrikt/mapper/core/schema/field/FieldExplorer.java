package dev.tiltrikt.mapper.core.schema.field;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public interface FieldExplorer {

  boolean shouldBeMapped(@NotNull Field field);

  @NotNull String getMappingName(@NotNull Field field);
}
