package dev.tiltrikt.mapper.core.basic.schema.explorer;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods for getting information about class. Based on java reflection.<br>
 * @see FieldExplorer
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClassExplorerImpl implements ClassExplorer {

  @NotNull FieldExplorer fieldExplorer = new FieldExplorerImpl();

  /**
   * Retrieves a list of mappable fields within the given target class and its superclasses.
   * Note that {@code Object.class} is skipped.
   *
   * @param targetClass The class to retrieve the mappable fields.
   * @return A list of mappable fields within the target class and its superclasses.
   */
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
