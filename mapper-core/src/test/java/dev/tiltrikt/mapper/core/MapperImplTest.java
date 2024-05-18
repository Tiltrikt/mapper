package dev.tiltrikt.mapper.core;

import dev.tiltrikt.mapper.core.factory.ObjectFactory;
import dev.tiltrikt.mapper.core.model.SimpleFieldModel;
import dev.tiltrikt.mapper.core.schema.MappingSchema;
import dev.tiltrikt.mapper.core.schema.MappingSchemaImpl;
import dev.tiltrikt.mapper.core.schema.processor.MappingSchemaProcessor;
import dev.tiltrikt.mapper.core.schema.resolver.MappingSchemaResolver;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class MapperImplTest {

  @Mock
  @NotNull
  ObjectFactory objectFactory;

  @Mock
  @NotNull
  MappingSchemaProcessor mappingSchemaProcessor;

  @Mock
  @NotNull
  MappingSchemaResolver mappingSchemaResolver;

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
  void givenSourceObjectTargetObject_whenMap_thenSchemaResolvedAndMapSourceObjectTargetObjectMappingSchemaCalled() {
    SimpleFieldModel source = new SimpleFieldModel("field");
    SimpleFieldModel target = new SimpleFieldModel("field");
    doReturn(new MappingSchemaImpl(Map.of()))
        .when(mappingSchemaResolver)
        .resolve(source, target);
    doReturn(target)
        .when(mapper)
        .map(any(SimpleFieldModel.class), any(SimpleFieldModel.class), any(MappingSchemaImpl.class));

    mapper.map(source, target);

    verify(
        mappingSchemaResolver,
        times(1)
    ).resolve(source, target);
    verify(
        mapper,
        times(1)
    ).map(any(SimpleFieldModel.class), any(SimpleFieldModel.class), any(MappingSchema.class));
  }

  @Test
  void givenSourceObjectTargetClassMappingSchema_whenMap_thenTargetObjectCreatedAndMapSourceObjectTargetObjectMappingSchemaCalled() {
    SimpleFieldModel source = new SimpleFieldModel("field");
    SimpleFieldModel target = new SimpleFieldModel("field");
    doReturn(target)
        .when(objectFactory)
        .createInstance(SimpleFieldModel.class);
    doReturn(target)
        .when(mapper)
        .map(any(SimpleFieldModel.class), any(SimpleFieldModel.class), any(MappingSchemaImpl.class));

    mapper.map(source, SimpleFieldModel.class, new MappingSchemaImpl(Map.of()));

    verify(
        objectFactory,
        times(1)
    ).createInstance(SimpleFieldModel.class);
    verify(
        mapper,
        times(1)
    ).map(any(SimpleFieldModel.class), any(SimpleFieldModel.class), any(MappingSchemaImpl.class));
  }

  @Test
  void givenSourceObjectTargetObjectMappingSchema_whenMap_thenMappingSchemaProcessorCalled() {
    SimpleFieldModel source = new SimpleFieldModel("field");
    SimpleFieldModel target = new SimpleFieldModel("field");
    doReturn(target)
        .when(mappingSchemaProcessor)
        .map(any(SimpleFieldModel.class), any(SimpleFieldModel.class), any(MappingSchemaImpl.class));

    mapper.map(source, target, new MappingSchemaImpl(Map.of()));

    verify(
        mappingSchemaProcessor,
        times(1)
    ).map(any(SimpleFieldModel.class), any(SimpleFieldModel.class), any(MappingSchemaImpl.class));
  }

}