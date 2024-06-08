package dev.tiltrikt.mapper.core.model;

import dev.tiltrikt.mapper.core.basic.annotation.FieldMapping;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnnotatedFieldModel {

  @FieldMapping
  String field;
}
