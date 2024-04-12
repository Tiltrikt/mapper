package dev.tiltrikt.mapper.core.exception;

import org.jetbrains.annotations.NotNull;

public class MappingSchemaException extends MapperException {

  public MappingSchemaException(@NotNull String message, Object @NotNull ... params) {
    super(message, params);
  }
}
