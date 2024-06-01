package dev.tiltrikt.mapper.core.schema.processor;

import dev.tiltrikt.mapper.core.model.SimpleFieldModel;
import dev.tiltrikt.mapper.core.model.inner.InnerSource;
import dev.tiltrikt.mapper.core.model.inner.InnerTarget;
import dev.tiltrikt.mapper.core.model.inner.SourceInnerFieldModel;
import dev.tiltrikt.mapper.core.model.inner.TargetInnerFieldModel;
import dev.tiltrikt.mapper.core.schema.MappingSchema;
import dev.tiltrikt.mapper.core.schema.MappingSchemaImpl;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class MappingSchemaDefaultProcessorTest {

  MappingSchemaProcessor mappingSchemaProcessor = new MappingSchemaDefaultProcessor();

  @SneakyThrows
  @Test
  void givenMappingSchemaWithoutInnerSchemas_whenMap_thenReturnedTargetObjectWithMappedFields() {
    SimpleFieldModel sourceObject = new SimpleFieldModel("sourceObject");
    SimpleFieldModel targetObject = new SimpleFieldModel("targetObject");
    Field field = SimpleFieldModel.class.getDeclaredField("field");
    MappingSchema mappingSchema = new MappingSchemaImpl(Map.of(field, field));
    SimpleFieldModel afterMappingObject = mappingSchemaProcessor.map(sourceObject, targetObject, mappingSchema);

    assertEquals("sourceObject", afterMappingObject.getField());
  }

  @SneakyThrows
  @Test
  void givenMappingSchemaWithInnerSchemas_whenMap_thenReturnedTargetObjectWithMappedFields() {
    SourceInnerFieldModel sourceObject = new SourceInnerFieldModel(new InnerSource("sourceObject"));
    TargetInnerFieldModel targetObject = new TargetInnerFieldModel(new InnerTarget("targetObject"));
    Field sourceField = SourceInnerFieldModel.class.getDeclaredField("field");
    Field targerField = TargetInnerFieldModel.class.getDeclaredField("field");
    Field innerSourceField = InnerSource.class.getDeclaredField("field");
    Field innerTargetField = InnerTarget.class.getDeclaredField("field");

    MappingSchema innerMappingSchema = new MappingSchemaImpl(Map.of(innerSourceField, innerTargetField));
    MappingSchema mappingSchema = new MappingSchemaImpl(
        Map.of(sourceField, targerField),
        Map.of(sourceField, innerMappingSchema)
    );
    TargetInnerFieldModel afterMappingObject = mappingSchemaProcessor.map(sourceObject, targetObject, mappingSchema);

    assertEquals("sourceObject", afterMappingObject.getField().getField());
  }
}