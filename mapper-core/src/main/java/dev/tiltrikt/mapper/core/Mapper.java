package dev.tiltrikt.mapper.core;

import dev.tiltrikt.mapper.core.schema.MappingSchema;
import org.jetbrains.annotations.NotNull;

  /**
 * Interface provides a basic set of mapping operations. It facilitates fields mapping between
 * source and target objects, helping to avoid boilerplate code.Mapper is typically used as a shared component.<br>
 * Most popular use case is mapping DTO objects.
 */
public interface Mapper {

    /**
     * Maps the given source object to a new instance of the specified target class.
     *
     * @param source The source object to map from.
     * @param targetClass The class of the target object.
     * @return A new instance of the target class mapped from the source object.
     */
  @NotNull <S, T> T map(@NotNull S source, Class<T> targetClass);

    /**
     * Maps the given source object to the specified target object.
     *
     * @param source The source object to map from.
     * @param target The target object to map to.
     * @return The target object after mapping.
     */
  @NotNull <S, T> T map(@NotNull S source, @NotNull T target);

    /**
     * Maps the given source object to a new instance of the specified target class using
     * the provided {@link MappingSchema}. It gives more control on mapping process.
     * Could be useful if you need to map not trivial object.
     *
     * @param source The source object to map from.
     * @param targetClass The class of the target object.
     * @param schema The mapping schema to use.
     * @return A new instance of the target class mapped from the source object.
     */
  @NotNull <S, T> T map(@NotNull S source, @NotNull Class<T> targetClass, @NotNull MappingSchema schema);

    /**
     * Maps the given source object to the specified target object using
     * the provided {@link MappingSchema}. It gives more control on mapping process.
     * Could be useful if you need to map not trivial object.
     *
     * @param source The source object to map from.
     * @param target The target object to map to.
     * @param schema The mapping schema to use.
     * @return The target object after mapping.
     */
  @NotNull <S, T> T map(@NotNull S source, @NotNull T target, @NotNull MappingSchema schema);
}
