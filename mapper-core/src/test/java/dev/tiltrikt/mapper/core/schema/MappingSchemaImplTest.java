package dev.tiltrikt.mapper.core.schema;

import dev.tiltrikt.mapper.core.exception.MappingSchemaException;
import dev.tiltrikt.mapper.core.model.AnnotatedFieldModel;
import dev.tiltrikt.mapper.core.model.AnnotatedIgnoreFieldModel;
import dev.tiltrikt.mapper.core.model.AnnotatedTargetNameFieldModel;
import dev.tiltrikt.mapper.core.model.SimpleFieldModel;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class MappingSchemaImplTest {

  static Field simpleField;

  static Field annotatedField;

  static Field annotatedIgnoreField;

  static Field annotatedTargetNameField;

  static final Map<Field, Field> fieldMap = new HashMap<>();

  static MappingSchema mappingSchema;


  @SneakyThrows
  @BeforeAll
  static void setUp() {
    simpleField = SimpleFieldModel.class.getDeclaredField("field");
    annotatedField = AnnotatedFieldModel.class.getDeclaredField("field");
    annotatedIgnoreField = AnnotatedIgnoreFieldModel.class.getDeclaredField("field");
    annotatedTargetNameField = AnnotatedTargetNameFieldModel.class.getDeclaredField("field");

    fieldMap.put(simpleField, simpleField);
    fieldMap.put(annotatedField, annotatedField);
    fieldMap.put(annotatedTargetNameField, annotatedTargetNameField);

    mappingSchema = new MappingSchemaImpl(fieldMap);
  }

  @Test
  void whenGetFieldsToMap_thenReturnedAllFieldsToMap() {
    assertTrue(mappingSchema.getFieldsToMap().contains(simpleField));
    assertTrue(mappingSchema.getFieldsToMap().contains(annotatedField));
    assertTrue(mappingSchema.getFieldsToMap().contains(annotatedTargetNameField));
  }

  @Test
  void givenFieldContainedInMappingSchema_whenGetTargetField_thenReturnedTargetField() {
    assertEquals(simpleField, mappingSchema.getTargetField(simpleField));
    assertEquals(annotatedField, mappingSchema.getTargetField(annotatedField));
    assertEquals(annotatedTargetNameField, mappingSchema.getTargetField(annotatedTargetNameField));
  }

  @Test
  void givenFieldNotContainedInMappingSchema_whenGetTargetField_thenThrownException() {
    assertThrows(MappingSchemaException.class, () -> mappingSchema.getTargetField(annotatedIgnoreField));
  }
}