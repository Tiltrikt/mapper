package dev.tiltrikt.mapper.core.basic.schema.explorer;

import dev.tiltrikt.mapper.core.model.AnnotatedFieldModel;
import dev.tiltrikt.mapper.core.model.AnnotatedIgnoreFieldModel;
import dev.tiltrikt.mapper.core.model.AnnotatedTargetNameFieldModel;
import dev.tiltrikt.mapper.core.model.SimpleFieldModel;
import dev.tiltrikt.mapper.core.model.inheritance.ChildModel;
import dev.tiltrikt.mapper.core.model.inheritance.ParentModel;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ClassExplorerImplTest {

  static Field simpleField;

  static Field annotatedField;

  static Field annotatedTargetNameField;

  static Field parentField;

  ClassExplorer classExplorer = new ClassExplorerImpl();

  @SneakyThrows
  @BeforeAll
  static void setUp() {
    simpleField = SimpleFieldModel.class.getDeclaredField("field");
    annotatedField = AnnotatedFieldModel.class.getDeclaredField("field");
    annotatedTargetNameField = AnnotatedTargetNameFieldModel.class.getDeclaredField("field");
    parentField = ParentModel.class.getDeclaredField("field");
  }

  @SneakyThrows
  @Test
  void givenSimpleFieldModel_whenGetMappableFields_thenReturnedField() {
    List<Field> fieldList = classExplorer.getMappableFields(SimpleFieldModel.class);
    assertEquals(1, fieldList.size());
    assertEquals(simpleField, fieldList.get(0));
  }

  @Test
  void givenAnnotatedFieldModel_whenGetMappableFields_thenReturnedField() {
    List<Field> fieldList = classExplorer.getMappableFields(AnnotatedFieldModel.class);
    assertEquals(1, fieldList.size());
    assertEquals(annotatedField, fieldList.get(0));
  }

  @Test
  void givenAnnotatedIgnoreFieldModel_whenGetMappableFields_thenReturnedField() {
    List<Field> fieldList = classExplorer.getMappableFields(AnnotatedIgnoreFieldModel.class);
    assertEquals(0, fieldList.size());
  }

  @Test
  void givenAnnotatedTargetNameFieldModel_whenGetMappableFields_thenReturnedField() {
    List<Field> fieldList = classExplorer.getMappableFields(AnnotatedTargetNameFieldModel.class);
    assertEquals(1, fieldList.size());
    assertEquals(annotatedTargetNameField, fieldList.get(0));
  }

  @Test
  void givenParentFieldModel_whenGetMappableFields_thenReturnedField() {
    List<Field> fieldList = classExplorer.getMappableFields(ChildModel.class);
    assertEquals(1, fieldList.size());
    assertEquals(parentField, fieldList.get(0));
  }

}