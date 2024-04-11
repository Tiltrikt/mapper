package dev.tiltrikt.mapper.core;

import org.jetbrains.annotations.NotNull;

public class MapperImpl implements Mapper {

  @Override
  public <T> @NotNull T map(@NotNull Object source, Class<T> targetClass) {
    return null;
  }

  @Override
  public <T> @NotNull T map(@NotNull Object source, @NotNull T target) {
    return null;
  }
}
