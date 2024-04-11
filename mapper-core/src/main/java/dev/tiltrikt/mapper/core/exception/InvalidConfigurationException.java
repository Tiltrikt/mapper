package dev.tiltrikt.mapper.core.exception;

import org.jetbrains.annotations.NotNull;

public class InvalidConfigurationException extends MapperException {

  public InvalidConfigurationException(@NotNull String message,
                                       Object @NotNull ... params) {
    super(message, params);
  }

  public InvalidConfigurationException(@NotNull String message) {
    super(message);
  }

  public InvalidConfigurationException(@NotNull String message,
                                       @NotNull Throwable cause) {
    super(message, cause);
  }

  public InvalidConfigurationException(@NotNull Throwable cause) {
    super(cause);
  }

  public InvalidConfigurationException(@NotNull String message,
                                       @NotNull Throwable cause,
                                       boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
