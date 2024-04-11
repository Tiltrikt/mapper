package dev.tiltrikt.mapper.core.exception;

import org.jetbrains.annotations.NotNull;

public class MissingConstructorException extends MapperException{

  public MissingConstructorException(@NotNull String message, Object @NotNull ... params) {
    super(message, params);
  }
}
