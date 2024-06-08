package dev.tiltrikt.mapper.core.basic.schema.resolver;

import dev.tiltrikt.mapper.core.basic.schema.MappingSchema;
import dev.tiltrikt.mapper.core.model.AnnotatedTargetNameFieldModel;
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

import static org.junit.jupiter.api.Assertions.assertEquals;


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

  @SneakyThrows
  @Test
  void givenFieldsWithDifferentClasses_whenResolve_thenReturnedSchemaContainsInnerMappingSchema() {
    MappingSchema mappingSchema = mappingSchemaResolver.resolve(
        new SourceInnerFieldModel(new InnerSource("")), new TargetInnerFieldModel(new InnerTarget(""))
    ).getInnerMappingSchema(SourceInnerFieldModel.class.getDeclaredField("field"));

    assertEquals(1, mappingSchema.getFieldsToMap().size());
  }

  @Test
  void givenFieldsAsPrimitives_whenResolve_thenReturnedSchemaContainsField() {
    assertEquals(
        1,
        mappingSchemaResolver.resolve(new PrimitiveFieldModel(5), new PrimitiveFieldModel(1)).getFieldsToMap().size()
    );
  }

  @Test
  void givenFieldsAsPrimitiveAndPrimitiveWrapper_whenResolve_thenReturnedSchemaContainsField() {
    assertEquals(
        1,
        mappingSchemaResolver.resolve(new PrimitiveFieldModel(5), new PrimitiveWrapperFieldModel(1)).getFieldsToMap().size()
    );
  }

  @Test
  void givenFieldsAsPrimitiveWrapperAndPrimitive_whenResolve_thenReturnedSchemaContainsField() {
    assertEquals(
        1,
        mappingSchemaResolver.resolve(new PrimitiveWrapperFieldModel(5), new PrimitiveFieldModel(1)).getFieldsToMap().size()
    );
  }
}