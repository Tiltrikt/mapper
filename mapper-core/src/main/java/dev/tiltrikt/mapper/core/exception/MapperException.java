package dev.tiltrikt.mapper.core.exception;

import org.jetbrains.annotations.NotNull;

public class MapperException extends RuntimeException {

  public MapperException() {
  }

  public MapperException(@NotNull String message, Object @NotNull ... params) {
    super(String.format(message, params));
  }

  public MapperException(@NotNull String message) {
    super(message);
  }

  public MapperException(@NotNull String message, @NotNull Throwable cause) {
    super(message, cause);
  }

  public MapperException(@NotNull Throwable cause) {
    super(cause);
  }

  public MapperException(@NotNull String message,
                         @NotNull Throwable cause,
                         boolean enableSuppression,
                         boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
