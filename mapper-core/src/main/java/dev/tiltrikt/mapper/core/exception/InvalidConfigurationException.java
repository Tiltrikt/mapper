package dev.tiltrikt.mapper.core.exception;

import org.jetbrains.annotations.NotNull;

public class InvalidConfigurationException extends MapperException {

  public InvalidConfigurationException(@NotNull String message,
                                       Object @NotNull ... params) {
    super(message, params);
  }
}
