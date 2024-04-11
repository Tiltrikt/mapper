package dev.tiltrikt.mapper.core.resolver;

import dev.tiltrikt.mapper.core.annotation.FieldMapping;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

public final class FieldSetResolverImpl implements FieldSetResolver {

  @Override
  public @NotNull @Unmodifiable Set<Field> resolve(@NotNull Object object) {
    Set<Field> fieldSet = new HashSet<>();
    Class<?> objectClass = object.getClass();
    do {
      fieldSet.addAll(Arrays.stream(objectClass.getDeclaredFields()).toList());
      objectClass = objectClass.getSuperclass();
    } while (!objectClass.getSuperclass().equals(Object.class));

    return fieldSet
        .stream()
        .filter(field -> {
          FieldMapping fieldMapping = field.getAnnotation(FieldMapping.class);
          return fieldMapping == null || !fieldMapping.ignore();
        })
        .collect(Collectors.toUnmodifiableSet());
  }
}
