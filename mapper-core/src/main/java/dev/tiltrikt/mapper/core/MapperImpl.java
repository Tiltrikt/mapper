package dev.tiltrikt.mapper.core;

import dev.tiltrikt.mapper.core.basic.factory.DefaultObjectFactory;
import dev.tiltrikt.mapper.core.basic.factory.ObjectFactory;
import dev.tiltrikt.mapper.core.exception.MissingConstructorException;
import dev.tiltrikt.mapper.core.manager.DefaultMappingManager;
import dev.tiltrikt.mapper.core.manager.MappingManager;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * Reduces basic mapping methods to one. Uses as adapter for further using of {@link MappingManager}.
 *
 * @see MappingManager
 * @see ObjectFactory
 */
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class MapperImpl implements Mapper {

  @NotNull
  MappingManager mappingManager = new DefaultMappingManager();

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
    return mappingManager.map(source, target);
  }
}
