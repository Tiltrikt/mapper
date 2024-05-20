package dev.tiltrikt.mapper.core.schema.resolver;

import dev.tiltrikt.mapper.core.schema.MappingSchema;
import org.jetbrains.annotations.NotNull;

/**
 * Responsible for creating {@link MappingSchema} between source and target objects.
 */
public interface MappingSchemaResolver {

  /** Resolves a mapping schema between source and target objects.
   *
   * @param source The object from which fields will be mapped.
   * @param target The object to which fields will be mapped.
   * @return A {@link MappingSchema} representing relation between source and target fields.
   */
  <S, T> MappingSchema resolve(@NotNull S source, @NotNull T target);
}
