package dev.tiltrikt.mapper.core.resolver.factory;

import dev.tiltrikt.mapper.core.resolver.FieldResolver;
import java.lang.reflect.Field;
import org.jetbrains.annotations.NotNull;

public interface FieldResolverFactory {

  @NotNull FieldResolver create(@NotNull Field sourceField);
}
