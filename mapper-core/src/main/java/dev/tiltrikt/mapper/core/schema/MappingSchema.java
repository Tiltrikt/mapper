package dev.tiltrikt.mapper.core.schema;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.List;

public interface MappingSchema {

  @NotNull Field getTargetField(@NotNull Field sourceField);

  @NotNull List<Field> getFieldsToMap();
}
