package dev.tiltrikt.mapper.core.resolver.factory;

import dev.tiltrikt.mapper.core.annotation.FieldMapping;
import dev.tiltrikt.mapper.core.exception.InvalidConfigurationException;
import dev.tiltrikt.mapper.core.resolver.FieldResolver;
import dev.tiltrikt.mapper.core.resolver.impl.AnnotationFieldResolver;
import dev.tiltrikt.mapper.core.resolver.impl.DefaultFieldResolver;
import java.lang.reflect.Field;
import org.jetbrains.annotations.NotNull;

public final class FieldResolverFactoryImpl implements FieldResolverFactory {

  @Override
  public @NotNull FieldResolver create(@NotNull Field sourceField)
      throws InvalidConfigurationException {
    FieldMapping fieldMapping = sourceField.getAnnotation(FieldMapping.class);
    if (fieldMapping != null) {
      if (fieldMapping.ignore() || fieldMapping.targetName().equals(FieldMapping.EMPTY_TARGET)) {
        throw new InvalidConfigurationException(
            "Invalid configuration for field %s."
                + " Field mapping is disabled or target field name is not configured.",
            sourceField.getName()
        );
      }
      return new AnnotationFieldResolver();
    }
    return new DefaultFieldResolver();
  }
}
