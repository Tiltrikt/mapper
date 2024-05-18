package dev.tiltrikt.mapper.core.schema.explorer;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClassExplorerImpl implements ClassExplorer {

  FieldExplorer fieldExplorer = new FieldExplorerImpl();

  @Override
  public @NotNull List<Field> getMappableFields(@NotNull Class<?> targetClass) {

    List<Field> fieldMap = new ArrayList<>();
    while (!targetClass.equals(Object.class)) {
      fieldMap.addAll(List.of(targetClass.getDeclaredFields()));
      targetClass = targetClass.getSuperclass();
    }
    return fieldMap
        .stream()
        .filter(fieldExplorer::shouldBeMapped)
        .toList();
  }
}
