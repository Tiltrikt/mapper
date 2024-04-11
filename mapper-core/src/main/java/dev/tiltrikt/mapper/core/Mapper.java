package dev.tiltrikt.mapper.core;

import org.jetbrains.annotations.NotNull;

public interface Mapper {

  @NotNull <T> T map(@NotNull Object source, Class<T> targetClass);

  @NotNull <T> T map(@NotNull Object source, @NotNull T target);
}
