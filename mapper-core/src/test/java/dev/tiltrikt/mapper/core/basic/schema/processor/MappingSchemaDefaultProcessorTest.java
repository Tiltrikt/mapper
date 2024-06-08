package dev.tiltrikt.mapper.core.basic.schema.processor;

import dev.tiltrikt.mapper.core.basic.schema.MappingSchema;
import dev.tiltrikt.mapper.core.basic.schema.MappingSchemaImpl;
import dev.tiltrikt.mapper.core.model.SimpleFieldModel;
import dev.tiltrikt.mapper.core.model.inner.InnerSource;
import dev.tiltrikt.mapper.core.model.inner.InnerTarget;
import dev.tiltrikt.mapper.core.model.inner.SourceInnerFieldModel;
import dev.tiltrikt.mapper.core.model.inner.TargetInnerFieldModel;
import dev.tiltrikt.mapper.core.model.primitive.PrimitiveFieldModel;
import dev.tiltrikt.mapper.core.model.primitive.PrimitiveWrapperFieldModel;
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

  @SneakyThrows
  @Test
  void givenMappingSchemaWithPrimitives_whenMap_thenReturnedTargetObjectWithMappedFields() {
    PrimitiveFieldModel sourceObject = new PrimitiveFieldModel(5);
    PrimitiveFieldModel targetObject = new PrimitiveFieldModel(1);
    Field field = PrimitiveFieldModel.class.getDeclaredField("field");
    MappingSchema mappingSchema = new MappingSchemaImpl(Map.of(field, field));

    PrimitiveFieldModel afterMappingObject = mappingSchemaProcessor.map(sourceObject, targetObject, mappingSchema);

    assertEquals(5, afterMappingObject.getField());
  }

  @SneakyThrows
  @Test
  void givenMappingSchemaWithPrimitiveAndPrimitiveWrapper_whenMap_thenReturnedTargetObjectWithMappedFields() {
    PrimitiveFieldModel sourceObject = new PrimitiveFieldModel(5);
    PrimitiveWrapperFieldModel targetObject = new PrimitiveWrapperFieldModel(1);
    Field sourceField = PrimitiveFieldModel.class.getDeclaredField("field");
    Field targetField = PrimitiveWrapperFieldModel.class.getDeclaredField("field");
    MappingSchema mappingSchema = new MappingSchemaImpl(Map.of(sourceField, targetField));

    PrimitiveWrapperFieldModel afterMappingObject = mappingSchemaProcessor.map(sourceObject, targetObject, mappingSchema);

    assertEquals(5, afterMappingObject.getField());
  }

  @SneakyThrows
  @Test
  void givenMappingSchemaWithPrimitiveWrapperAndPrimitive_whenMap_thenReturnedTargetObjectWithMappedFields() {
    PrimitiveWrapperFieldModel sourceObject = new PrimitiveWrapperFieldModel(5);
    PrimitiveFieldModel targetObject = new PrimitiveFieldModel(1);
    Field sourceField = PrimitiveWrapperFieldModel.class.getDeclaredField("field");
    Field targetField = PrimitiveFieldModel.class.getDeclaredField("field");
    MappingSchema mappingSchema = new MappingSchemaImpl(Map.of(sourceField, targetField));

    PrimitiveFieldModel afterMappingObject = mappingSchemaProcessor.map(sourceObject, targetObject, mappingSchema);

    assertEquals(5, afterMappingObject.getField());
  }
}