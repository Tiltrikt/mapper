package dev.tiltrikt.mapper.core.schema.processor;

import dev.tiltrikt.mapper.core.model.SimpleFieldModel;
import dev.tiltrikt.mapper.core.schema.MappingSchema;
import dev.tiltrikt.mapper.core.schema.MappingSchemaImpl;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class MappingSchemaDefaultProcessorTest {

  MappingSchemaProcessor mappingSchemaProcessor = new MappingSchemaDefaultProcessor();

  @SneakyThrows
  @Test
  void whenMap_thenReturnedTargetObjectWithMappedFields() {
    SimpleFieldModel sourceObject = new SimpleFieldModel("sourceObject");
    SimpleFieldModel targetObject = new SimpleFieldModel("targetObject");
    Field field = SimpleFieldModel.class.getDeclaredField("field");
    MappingSchema mappingSchema = new MappingSchemaImpl(Map.of(field, field));
    SimpleFieldModel afterMappingObject = mappingSchemaProcessor.map(sourceObject, targetObject, mappingSchema);

    assertEquals("sourceObject", afterMappingObject.getField());
  }
}