package dev.tiltrikt.mapper.core.resolver.factory;

import dev.tiltrikt.mapper.core.annotation.FieldMapping;
import dev.tiltrikt.mapper.core.resolver.FieldResolver;
import dev.tiltrikt.mapper.core.resolver.impl.AnnotationFieldResolver;
import dev.tiltrikt.mapper.core.resolver.impl.DefaultFieldResolver;
import java.lang.reflect.Field;
import org.jetbrains.annotations.NotNull;

public final class FieldResolverFactoryImpl implements FieldResolverFactory {

  @Override
  public @NotNull FieldResolver create(@NotNull Field sourceField) {
    if (sourceField.isAnnotationPresent(FieldMapping.class)) {
      return new AnnotationFieldResolver();
    }
    return new DefaultFieldResolver();
  }
}
