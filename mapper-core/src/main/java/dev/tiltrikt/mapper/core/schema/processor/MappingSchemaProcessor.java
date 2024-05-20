package dev.tiltrikt.mapper.core.schema.processor;

import dev.tiltrikt.mapper.core.exception.MappingSchemaException;
import dev.tiltrikt.mapper.core.schema.MappingSchema;
import org.jetbrains.annotations.NotNull;

/**
 * Responsible only for processing mapping between source and target objects. Shouldn't
 * resolve how fields must be mapped by itself.
 */
public interface MappingSchemaProcessor {

  /**
   * Maps fields from a source object to a target object based on the provided {@link MappingSchema}.
   *
   * @param source The source object from which map fields.
   * @param target The target object to which map fields.
   * @param schema The mapping schema that defines the associations between fields.
   * @return The target object after mapping.
   * @throws MappingSchemaException If {@code MappingSchema} is incorrect.
   */
  @NotNull <S, T> T map(@NotNull S source,
                        @NotNull T target,
                        @NotNull MappingSchema schema) throws MappingSchemaException;
}
