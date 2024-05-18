package dev.tiltrikt.mapper.core.schema.explorer;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.List;

public interface ClassExplorer {

  @NotNull List<Field> getMappableFields(@NotNull Class<?> targetClass);
}
