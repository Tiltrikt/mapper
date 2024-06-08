package dev.tiltrikt.mapper.core;

import dev.tiltrikt.mapper.core.exception.MissingConstructorException;
import org.jetbrains.annotations.NotNull;

  /**
 * Interface provides a basic set of mapping operations. It facilitates fields mapping between
 * source and target objects, helping to avoid boilerplate code. Mapper is typically used as a shared component.<br>
 * Most popular use case is mapping DTO objects.
 */
public interface Mapper {

    /**
     * Maps the given source object to a new instance of the specified target class.
     *
     * @param source The source object to map from.
     * @param targetClass The class of the target object.
     * @return A new instance of the target class mapped from the source object.
     * @throws MissingConstructorException If object of target class couldn't be created.
     */
  @NotNull <S, T> T map(@NotNull S source, Class<T> targetClass) throws MissingConstructorException;

    /**
     * Maps the given source object to the specified target object.
     *
     * @param source The source object to map from.
     * @param target The target object to map to.
     * @return The target object after mapping.
     */
  @NotNull <S, T> T map(@NotNull S source, @NotNull T target);
}
