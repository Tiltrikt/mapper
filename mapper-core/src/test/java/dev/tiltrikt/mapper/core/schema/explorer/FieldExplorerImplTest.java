package dev.tiltrikt.mapper.core.schema.explorer;

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

import static org.junit.jupiter.api.Assertions.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class FieldExplorerImplTest {

  static Field simpleField;

  static Field annotatedField;

  static Field annotatedIgnoreField;

  static Field annotatedTargetNameField;

  FieldExplorer fieldExplorer = new FieldExplorerImpl();

  @SneakyThrows
  @BeforeAll
  static void setUp() {
    simpleField = SimpleFieldModel.class.getDeclaredField("field");
    annotatedField = AnnotatedFieldModel.class.getDeclaredField("field");
    annotatedIgnoreField = AnnotatedIgnoreFieldModel.class.getDeclaredField("field");
    annotatedTargetNameField = AnnotatedTargetNameFieldModel.class.getDeclaredField("field");
  }

  @Test
  void givenFieldWithoutAnnotation_whenShouldBeMapped_thenReturnedTrue() {
    assertTrue(fieldExplorer.shouldBeMapped(simpleField));
  }

  @Test
  void givenFieldWithAnnotation_whenShouldBeMapped_thenReturnedTrue() {
    assertTrue(fieldExplorer.shouldBeMapped(annotatedField));
  }

  @Test
  void givenFieldWithAnnotationIgnore_whenShouldBeMapped_thenReturnedFalse() {
    assertFalse(fieldExplorer.shouldBeMapped(annotatedIgnoreField));
  }

  @Test
  void givenSimpleField_whenGetMappingName_thenReturnedFieldName() {
    assertEquals("field", fieldExplorer.getMappingName(simpleField));
  }

  @Test
  void givenFieldWithAnnotation_whenGetMappingName_thenReturnedFieldName() {
    assertEquals("field", fieldExplorer.getMappingName(annotatedField));
  }

  @Test
  void givenFieldWithAnnotationTargetName_whenGetMappingName_thenReturnedNameFromAnnotation() {
    assertNotEquals("field", fieldExplorer.getMappingName(annotatedTargetNameField));
    assertEquals(AnnotatedTargetNameFieldModel.ANNOTATION_TARGET_NAME, fieldExplorer.getMappingName(annotatedTargetNameField));
  }
}