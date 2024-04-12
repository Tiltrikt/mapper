package dev.tiltrikt.mapper.core;

import dev.tiltrikt.mapper.core.exception.MapperException;
import dev.tiltrikt.mapper.core.exception.MissingConstructorException;
import dev.tiltrikt.mapper.core.resolver.FieldResolver;
import dev.tiltrikt.mapper.core.resolver.FieldSetResolver;
import dev.tiltrikt.mapper.core.resolver.FieldSetResolverImpl;
import dev.tiltrikt.mapper.core.resolver.impl.AnnotationFieldResolver;
import dev.tiltrikt.mapper.core.resolver.impl.DefaultFieldResolver;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class MapperImpl implements Mapper {

  @NotNull ObjectFactory objectFactory = new ObjetFactoryImpl();
  @NotNull FieldSetResolver fieldSetResolver = new FieldSetResolverImpl();
  @NotNull FieldResolver defaultFieldResolver = new DefaultFieldResolver();
  @NotNull FieldResolver annotationFieldResolver = new AnnotationFieldResolver();

  @Override
  public <T> @NotNull T map(@NotNull Object source, Class<T> targetClass) {
    T object = objectFactory.createInstance(targetClass);
    return map(source, object);
  }

  @Override
  public <T> @NotNull T map(@NotNull Object source, @NotNull T target) {

    Set<Field> sourceFieldSet = fieldSetResolver.resolve(source);
    for (Field sourceField : sourceFieldSet) {
      Optional<Field> optionalField;
      try {
        optionalField = annotationFieldResolver.resolve(sourceField, target);
      } catch (MapperException e) {
        optionalField = defaultFieldResolver.resolve(sourceField, target);
      }

      optionalField.ifPresent(
          new Consumer<>() {
            @SneakyThrows
            @Override
            public void accept(Field field) {
              field.set(target, sourceField.get(source));
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
