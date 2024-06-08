package dev.tiltrikt.mapper.core.manager;

import dev.tiltrikt.mapper.core.basic.BasicMappingDirector;
import dev.tiltrikt.mapper.core.basic.BasicMappingDirectorImpl;
import dev.tiltrikt.mapper.core.basic.schema.processor.MappingSchemaDefaultProcessor;
import dev.tiltrikt.mapper.core.basic.schema.resolver.MappingSchemaResolver;
import dev.tiltrikt.mapper.core.provider.MappingProvider;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.ResolvableType;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Iterates {@code source} and {@code target} objects through a list of {@link MappingProvider}s.
 * <p>
 * Manages {@code MappingProvider}s in order to provide suitable one for mapping given objects.
 * <p>
 * The {@link MappingProvider}s are iterated over in the order they are present in the list. When a provider is found
 * that supports mapping between the source and target object's types, the mapping is performed using that provider.
 * <p>
 * If no suitable provider is found than basic mapper would be used.
 *
 * @see MappingProvider
 * @see MappingSchemaResolver
 * @see MappingSchemaDefaultProcessor
 */
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DefaultMappingManager implements MappingManager {

  @NotNull
  BasicMappingDirector basicMappingDirector = new BasicMappingDirectorImpl();

  /**
   * List of registered {@link MappingProvider}s that could be used for mapping.
   */
  @NotNull
  List<MappingProvider<?, ?>> providers = Collections.emptyList();

  /**
   * Attempts to map fields from source to target object.
   * <p>
   * The list of {@link MappingProvider}s will be successively tried until found
   * one that is capable of mapping given objects.
   * Mapping will then be attempted with that <code>MappingProvider</code>.
   * <p>
   * If more than one <code>MappingProvider</code> supports the passed
   * objects, the first one will be used.
   * <p>
   * If no suitable provider is found than mapping operations would be delegated to {@link BasicMappingDirector}
   *
   * @param source The source object to map from.
   * @param target The target object to map to.
   * @return The target object after mapping.
   */
  @Override
  @SuppressWarnings("unchecked")
  public <S, T> @NotNull T map(@NotNull S source, @NotNull T target) {

    for (MappingProvider<?, ?> provider : providers) {
      ResolvableType resolvableType = ResolvableType.forClass(MappingProvider.class, provider.getClass());
      if (Objects.equals(resolvableType.resolveGeneric(0), source.getClass()) &&
          Objects.equals(resolvableType.resolveGeneric(1), target.getClass())) {
        return ((MappingProvider<S, T>) provider).map(source, target);
      }
    }

    return basicMappingDirector.map(source, target);
  }
}
