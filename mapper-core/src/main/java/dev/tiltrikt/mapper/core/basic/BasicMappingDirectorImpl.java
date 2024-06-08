package dev.tiltrikt.mapper.core.basic;

import dev.tiltrikt.mapper.core.basic.schema.MappingSchema;
import dev.tiltrikt.mapper.core.basic.schema.processor.MappingSchemaDefaultProcessor;
import dev.tiltrikt.mapper.core.basic.schema.processor.MappingSchemaProcessor;
import dev.tiltrikt.mapper.core.basic.schema.resolver.MappingSchemaDefaultResolver;
import dev.tiltrikt.mapper.core.basic.schema.resolver.MappingSchemaResolver;
import org.jetbrains.annotations.NotNull;

/**
 * Directs process of basic mapping.
 *
 * @see MappingSchemaProcessor
 * @see MappingSchemaResolver
 */
public class BasicMappingDirectorImpl implements BasicMappingDirector {

  @NotNull
  MappingSchemaResolver mappingSchemaResolver = new MappingSchemaDefaultResolver();

  @NotNull
  MappingSchemaProcessor mappingSchemaProcessor = new MappingSchemaDefaultProcessor();

  /**
   * Directs mapping properties from a source object to a target object based on the resolved mapping schema.
   *
   * @param source The source object to map from.
   * @param target The target object to map to.
   * @return The target object after mapping.
   */
  @Override
  public <S, T> @NotNull T map(@NotNull S source, @NotNull T target) {
    MappingSchema mappingSchema = mappingSchemaResolver.resolve(source, target);
    return mappingSchemaProcessor.map(source, target, mappingSchema);
  }
}
