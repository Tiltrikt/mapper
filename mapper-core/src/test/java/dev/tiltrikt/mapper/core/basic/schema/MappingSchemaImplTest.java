package dev.tiltrikt.mapper.core.basic.schema;

import dev.tiltrikt.mapper.core.exception.MappingSchemaException;
import dev.tiltrikt.mapper.core.model.AnnotatedFieldModel;
import dev.tiltrikt.mapper.core.model.AnnotatedIgnoreFieldModel;
import dev.tiltrikt.mapper.core.model.AnnotatedTargetNameFieldModel;
import dev.tiltrikt.mapper.core.model.SimpleFieldModel;
import dev.tiltrikt.mapper.core.model.inner.InnerSource;
import dev.tiltrikt.mapper.core.model.inner.InnerTarget;
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

  static Field innerSource;

  static Field innerTarget;

  static final Map<Field, Field> fieldMap = new HashMap<>();

  static MappingSchema innerMappingSchema;

  static final Map<Field, MappingSchema> innerMappingSchemas = new HashMap<>();

  static MappingSchema mappingSchema;


  @SneakyThrows
  @BeforeAll
  static void setUp() {
    simpleField = SimpleFieldModel.class.getDeclaredField("field");
    annotatedField = AnnotatedFieldModel.class.getDeclaredField("field");
    annotatedIgnoreField = AnnotatedIgnoreFieldModel.class.getDeclaredField("field");
    annotatedTargetNameField = AnnotatedTargetNameFieldModel.class.getDeclaredField("field");
    innerSource = InnerSource.class.getDeclaredField("field");
    innerTarget = InnerTarget.class.getDeclaredField("field");

    fieldMap.put(simpleField, simpleField);
    fieldMap.put(annotatedField, annotatedField);
    fieldMap.put(annotatedTargetNameField, annotatedTargetNameField);
    fieldMap.put(innerSource, innerTarget);

    innerMappingSchema = new MappingSchemaImpl(Map.of(innerSource, innerTarget));
    innerMappingSchemas.put(innerSource, innerMappingSchema);

    mappingSchema = new MappingSchemaImpl(fieldMap, innerMappingSchemas);
  }

  @Test
  void whenGetFieldsToMap_thenReturnedAllFieldsToMap() {
    assertTrue(mappingSchema.getFieldsToMap().contains(simpleField));
    assertTrue(mappingSchema.getFieldsToMap().contains(annotatedField));
    assertTrue(mappingSchema.getFieldsToMap().contains(annotatedTargetNameField));
    assertTrue(mappingSchema.getFieldsToMap().contains(innerSource));
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

  @Test
  void givenFieldContainedInInnerMappingSchema_whenGetInnerMappingSchema_thenReturnedTargetMappingSchema() {
    assertEquals(innerMappingSchema, mappingSchema.getInnerMappingSchema(innerSource));
  }

  @Test
  void givenFieldNotContainedInInnerMappingSchema_whenGetInnerMappingSchema_thenThrownException() {
    assertThrows(MappingSchemaException.class, () -> mappingSchema.getInnerMappingSchema(simpleField));
  }
}