package dev.tiltrikt.mapper.core.resolver;

import java.lang.reflect.Field;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public interface FieldResolver {

  @NotNull Optional<Field> resolve(@NotNull Field sourceField, @NotNull Object target);
}
