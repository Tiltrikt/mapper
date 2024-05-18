package dev.tiltrikt.mapper.core.factory;

import org.jetbrains.annotations.NotNull;


public interface ObjectFactory {

  @NotNull
  <T> T createInstance(@NotNull Class<T> targetClass);
}
