package dev.tiltrikt.mapper.core.manager;

import dev.tiltrikt.mapper.core.basic.BasicMappingDirectorImpl;
import dev.tiltrikt.mapper.core.model.AnnotatedFieldModel;
import dev.tiltrikt.mapper.core.model.SimpleFieldModel;
import dev.tiltrikt.mapper.core.provider.MappingProvider;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class DefaultMappingManagerTest {

  @Spy
  MappingProvider<AnnotatedFieldModel, AnnotatedFieldModel> mappingProviderA = new MappingProvider<>() {
    @Override
    public @NotNull AnnotatedFieldModel map(@NotNull AnnotatedFieldModel source, @NotNull AnnotatedFieldModel target) {
      return null;
    }
  };

  @Spy
  MappingProvider<SimpleFieldModel, SimpleFieldModel> mappingProviderB = new MappingProvider<>() {
    @Override
    public @NotNull SimpleFieldModel map(@NotNull SimpleFieldModel source, @NotNull SimpleFieldModel target) {
      return null;
    }
  };

  @Spy
  BasicMappingDirectorImpl basicMappingDirector;

  MappingManager mappingManager;

  @BeforeEach
  void setUp() {
    List<MappingProvider<?, ?>> mappingProviders = List.of(mappingProviderA, mappingProviderB);
    DefaultMappingManager defaultMappingManager = new DefaultMappingManager();
    defaultMappingManager.setProviders(mappingProviders);
    defaultMappingManager.setBasicMappingDirector(basicMappingDirector);
    mappingManager = defaultMappingManager;
  }

  @Test
  void givenObjectsThatOneOfProvidersSupports_whenMap_thenMappingOperationMustBeDelegatedToThatProvider() {
    SimpleFieldModel source = new SimpleFieldModel("source");
    SimpleFieldModel target = new SimpleFieldModel("target");

    SimpleFieldModel objectAfterMapping = new SimpleFieldModel("source");
    doReturn(objectAfterMapping)
        .when(mappingProviderB)
        .map(source, target);

    assertEquals(objectAfterMapping, mappingManager.map(source, target));

    verify(
        mappingProviderA,
        never()
    ).map(any(), any());

    verify(
        basicMappingDirector,
        never()
    ).map(any(), any());

    verify(
        mappingProviderB,
        times(1)
    ).map(source, target);
  }

  @Test
  void givenObjectsThatNoneOfProvidersSupports_whenMap_thenDefaultMappingMustBeInvoked() {
    SimpleFieldModel source = new SimpleFieldModel("source");
    AnnotatedFieldModel target = new AnnotatedFieldModel("target");

    AnnotatedFieldModel objectAfterMapping = new AnnotatedFieldModel("source");
    doReturn(objectAfterMapping)
        .when(basicMappingDirector)
        .map(source, target);

    assertEquals(objectAfterMapping, mappingManager.map(source, target));

    verify(
        mappingProviderA,
        never()
    ).map(any(), any());

    verify(
        mappingProviderB,
        never()
    ).map(any(), any());

    verify(
        basicMappingDirector,
        times(1)
    ).map(any(), any());


  }
}