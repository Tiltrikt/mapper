package dev.tiltrikt.mapper.core;

import dev.tiltrikt.mapper.core.exception.MappingSchemaException;
import dev.tiltrikt.mapper.core.exception.MissingConstructorException;
import dev.tiltrikt.mapper.core.factory.DefaultObjectFactory;
import dev.tiltrikt.mapper.core.factory.ObjectFactory;
import dev.tiltrikt.mapper.core.schema.MappingSchema;
import dev.tiltrikt.mapper.core.schema.processor.MappingSchemaDefaultProcessor;
import dev.tiltrikt.mapper.core.schema.processor.MappingSchemaProcessor;
import dev.tiltrikt.mapper.core.schema.resolver.MappingSchemaDefaultResolver;
import dev.tiltrikt.mapper.core.schema.resolver.MappingSchemaResolver;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * Provides implementation of basic methods for mapping operations.
 *
 * @see MappingSchemaResolver
 * @see MappingSchemaProcessor
 * @see ObjectFactory
 */
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class MapperImpl implements Mapper {

  @NotNull
  MappingSchemaResolver mappingSchemaResolver = new MappingSchemaDefaultResolver();

  @NotNull
  MappingSchemaProcessor mappingSchemaProcessor = new MappingSchemaDefaultProcessor();

  @NotNull
  ObjectFactory objectFactory = new DefaultObjectFactory();

  /**
   * Maps the given source object to a new instance of the specified target class.
   * Could lead to {@link MissingConstructorException}
   * if target class doesn't have default constructor. To avoid it create object by
   * yourself and use another method or implement custom {@link ObjectFactory}.
   *
   * @param source      The source object to map from.
   * @param targetClass The class of the target object.
   * @return A new instance of the target class mapped from the source object.
   * @throws MissingConstructorException If object of target class couldn't be created.
   */
  @Override
  public <S, T> @NotNull T map(@NotNull S source, Class<T> targetClass) throws MissingConstructorException {
    T target = objectFactory.createInstance(targetClass);
    return map(source, target);
  }

  /**
   * Maps the given source object to the specified target object.
   *
   * @param source The source object to map from.
   * @param target The target object to map to.
   * @return The target object after mapping.
   */
  @Override
  public <S, T> @NotNull T map(@NotNull S source, @NotNull T target) {
    MappingSchema schema = mappingSchemaResolver.resolve(source, target);
    return map(source, target, schema);
  }

  /**
   * Maps the given source object to a new instance of the specified target class using
   * the provided {@link MappingSchema}. It gives more control on mapping process.
   * Could be useful if you need to map not trivial object, but you don't want to implement
   * own {@link MappingSchemaResolver}.<br>
   * <p>
   * Could lead to {@link MissingConstructorException}
   * if target class doesn't have default constructor. To avoid it create object by
   * yourself and use another method or implement custom {@link ObjectFactory}.
   *
   * @param source      The source object to map from.
   * @param targetClass The class of the target object.
   * @param schema      The mapping schema to use.
   * @return A new instance of the target class mapped from the source object.
   * @throws MissingConstructorException If object of target class couldn't be created.
   * @throws MappingSchemaException      If {@code MappingSchema} was created incorrectly.
   */
  @Override
  public <S, T> @NotNull T map(@NotNull S source,
                               @NotNull Class<T> targetClass,
                               @NotNull MappingSchema schema) throws MissingConstructorException, MappingSchemaException {

    T target = objectFactory.createInstance(targetClass);
    return map(source, target, schema);
  }

  /**
   * Maps the given source object to the specified target object using
   * the provided {@link MappingSchema}. It gives more control on mapping process.
   * Could be useful if you need to map not trivial object, but you don't want to implement
   * own {@link MappingSchemaResolver}.
   *
   * @param source The source object to map from.
   * @param target The target object to map to.
   * @param schema The mapping schema to use.
   * @return The target object after mapping.
   * @throws MappingSchemaException If {@code MappingSchema} was created incorrectly.
   */
  @Override
  public <S, T> @NotNull T map(@NotNull S source,
                               @NotNull T target,
                               @NotNull MappingSchema schema) throws MappingSchemaException {

    return mappingSchemaProcessor.map(source, target, schema);
  }
}
