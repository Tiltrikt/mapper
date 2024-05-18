package dev.tiltrikt.mapper.core.model;

import dev.tiltrikt.mapper.core.annotation.FieldMapping;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnnotatedFieldModel {

  @FieldMapping
  String field;
}
