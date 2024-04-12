package dev.tiltrikt.mapper.core;

import dev.tiltrikt.mapper.core.exception.MissingConstructorException;
import dev.tiltrikt.mapper.core.schema.MappingSchema;
import java.lang.reflect.Constructor;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class MapperImpl implements Mapper {

  @NotNull ObjectFactory objectFactory = new ObjetFactoryImpl();

  @Override
  public <S, T> @NotNull T map(@NotNull S source, Class<T> targetClass) {
    return null;
  }

  @Override
  public <S, T> @NotNull T map(@NotNull S source, @NotNull T target) {
    return null;
  }

  @Override
  public <S, T> @NotNull T map(@NotNull S source, @NotNull Class<T> target,
                               @NotNull MappingSchema<S, T> schema) {
    return null;
  }

  @Override
  public <S, T> @NotNull T map(@NotNull S source, @NotNull T target,
                               @NotNull MappingSchema<S, T> schema) {
    return null;
  }


  private static final class ObjetFactoryImpl implements ObjectFactory {

    @Override
    @SneakyThrows
    public <T> @NotNull T createInstance(@NotNull Class<T> targetClass) {
      try {
        Constructor<T> constructor = targetClass.getDeclaredConstructor();
        return constructor.newInstance();
      } catch (NoSuchMethodException ignored) {
        throw new MissingConstructorException(
            "Can't create an instance of %s. Missing no arguments constructor.",
            targetClass.getSimpleName()
        );
      }
    }
  }
}
