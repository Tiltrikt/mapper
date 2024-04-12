package dev.tiltrikt.mapper.core;

import dev.tiltrikt.mapper.core.exception.MissingConstructorException;
import dev.tiltrikt.mapper.core.resolver.FieldSetResolver;
import dev.tiltrikt.mapper.core.resolver.FieldSetResolverImpl;
import dev.tiltrikt.mapper.core.resolver.factory.FieldResolverFactory;
import dev.tiltrikt.mapper.core.resolver.factory.FieldResolverFactoryImpl;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Set;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class MapperImpl implements Mapper {

  @NotNull ObjectFactory objectFactory = new ObjetFactoryImpl();
  @NotNull FieldSetResolver fieldSetResolver = new FieldSetResolverImpl();
  @NotNull FieldResolverFactory fieldResolverFactory = new FieldResolverFactoryImpl();

  @Override
  public <T> @NotNull T map(@NotNull Object source, Class<T> targetClass) {
    T object = objectFactory.createInstance(targetClass);
    return map(source, object);
  }

  @Override
  public <T> @NotNull T map(@NotNull Object source, @NotNull T target) {
    Set<Field> sourceFieldSet = fieldSetResolver.resolve(source);
    for (Field sourceField : sourceFieldSet) {
      fieldResolverFactory.create(sourceField)
          .resolve(sourceField, target)
          .ifPresent(
              field -> {
                field.setAccessible(true);
                sourceField.setAccessible(true);
                try {
                  field.set(target, sourceField.get(source));
                } catch (IllegalAccessException e) {
                  throw new RuntimeException(e);
                }
              }
          );
    }
    return target;
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
