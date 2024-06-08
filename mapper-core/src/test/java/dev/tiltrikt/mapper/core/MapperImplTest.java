package dev.tiltrikt.mapper.core;

import dev.tiltrikt.mapper.core.basic.factory.ObjectFactory;
import dev.tiltrikt.mapper.core.manager.MappingManager;
import dev.tiltrikt.mapper.core.model.SimpleFieldModel;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class MapperImplTest {

  @Mock
  @NotNull
  ObjectFactory objectFactory;

  @Mock
  @NotNull
  MappingManager mappingManager;

  @Spy
  @InjectMocks
  @NotNull
  MapperImpl mapper;

  @Test
  void givenSourceObjectTargetClass_whenMap_thenTargetObjectCreatedAndMapSourceObjectTargetObjectCalled() {
    SimpleFieldModel source = new SimpleFieldModel("field");
    SimpleFieldModel target = new SimpleFieldModel("field");
    doReturn(target)
        .when(objectFactory)
        .createInstance(SimpleFieldModel.class);
    doReturn(target)
        .when(mapper)
        .map(any(SimpleFieldModel.class), any(SimpleFieldModel.class));

    mapper.map(source, SimpleFieldModel.class);

    verify(
        objectFactory,
        times(1)
    ).createInstance(SimpleFieldModel.class);
    verify(
        mapper,
        times(1)
    ).map(any(SimpleFieldModel.class), any(SimpleFieldModel.class));
  }

  @Test
  void givenSourceObjectTargetObject_whenMap_thenMappingManagerCalled() {
    SimpleFieldModel source = new SimpleFieldModel("source");
    SimpleFieldModel target = new SimpleFieldModel("target");

    SimpleFieldModel objectAfterMapping = new SimpleFieldModel("source");
    doReturn(objectAfterMapping)
        .when(mappingManager)
        .map(source, target);

    mapper.map(source, target);

    verify(
        mappingManager,
        times(1)
    ).map(source, target);
  }
}