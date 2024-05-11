package dev.tiltrikt.mapper.core.schema;

import dev.tiltrikt.mapper.core.exception.MappingSchemaException;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.List;

public interface MappingSchema {

  @NotNull Field getTargetField(@NotNull Field sourceField) throws MappingSchemaException;

  @NotNull List<Field> getFieldsToMap();
}
