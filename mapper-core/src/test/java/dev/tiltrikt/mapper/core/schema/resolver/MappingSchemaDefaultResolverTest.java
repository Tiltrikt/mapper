package dev.tiltrikt.mapper.core.schema.resolver;

import dev.tiltrikt.mapper.core.model.AnnotatedTargetNameFieldModel;
import dev.tiltrikt.mapper.core.model.SimpleFieldModel;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class MappingSchemaDefaultResolverTest {

  MappingSchemaResolver mappingSchemaResolver = new MappingSchemaDefaultResolver();

  @Test
  void givenFieldsWithTheSameMappingName_whenResolve_thenReturnedSchemaContainsField() {
    assertEquals(
        1,
        mappingSchemaResolver.resolve(new SimpleFieldModel(""), new SimpleFieldModel("")).getFieldsToMap().size()
    );
  }

  @Test
  void givenFieldsWithDifferentMappingName_whenResolve_thenReturnedSchemaContainsNoField() {
    assertEquals(
        0,
        mappingSchemaResolver.resolve(new SimpleFieldModel(""), new AnnotatedTargetNameFieldModel("")).getFieldsToMap().size()
    );
  }

}