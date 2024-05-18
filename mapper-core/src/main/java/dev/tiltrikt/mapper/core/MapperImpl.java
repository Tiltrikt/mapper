package dev.tiltrikt.mapper.core;

import dev.tiltrikt.mapper.core.factory.DefaultObjectFactory;
import dev.tiltrikt.mapper.core.factory.ObjectFactory;
import dev.tiltrikt.mapper.core.schema.MappingSchema;
import dev.tiltrikt.mapper.core.schema.processor.MappingSchemaProcessor;
import dev.tiltrikt.mapper.core.schema.processor.MappingSchemaProcessorImpl;
import dev.tiltrikt.mapper.core.schema.resolver.MappingSchemaDefaultResolver;
import dev.tiltrikt.mapper.core.schema.resolver.MappingSchemaResolver;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class MapperImpl implements Mapper {

  @NotNull
  MappingSchemaResolver mappingSchemaResolver = new MappingSchemaDefaultResolver();

  @NotNull
  MappingSchemaProcessor mappingSchemaProcessor = new MappingSchemaProcessorImpl();

  @NotNull
  ObjectFactory objectFactory = new DefaultObjectFactory();

  @Override
  public <S, T> @NotNull T map(@NotNull S source, Class<T> targetClass) {
    T target = objectFactory.createInstance(targetClass);
    return map(source, target);
  }

  @Override
  public <S, T> @NotNull T map(@NotNull S source, @NotNull T target) {
    MappingSchema schema = mappingSchemaResolver.resolve(source, target);
    return map(source, target, schema);
  }

  @Override
  public <S, T> @NotNull T map(@NotNull S source, @NotNull Class<T> targetClass, @NotNull MappingSchema schema) {
    T target = objectFactory.createInstance(targetClass);
    return map(source, target, schema);
  }

  @Override
  public <S, T> @NotNull T map(@NotNull S source, @NotNull T target, @NotNull MappingSchema schema) {
    return mappingSchemaProcessor.map(source, target, schema);
  }
}
