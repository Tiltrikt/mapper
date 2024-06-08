package dev.tiltrikt.mapper.core.exception;

import org.jetbrains.annotations.NotNull;

public class ProviderNotFoundException extends MapperException {

  public ProviderNotFoundException(@NotNull String message, Object @NotNull ... params) {
    super(message, params);
  }
}
