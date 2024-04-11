package dev.tiltrikt.mapper.core.resolver;

import java.lang.reflect.Field;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

public interface FieldSetResolver {

  @NotNull @Unmodifiable Set<Field> resolve(@NotNull Object object);
}
