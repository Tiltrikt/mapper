package dev.tiltrikt.mapper.core.model;

import dev.tiltrikt.mapper.core.basic.annotation.FieldMapping;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnnotatedIgnoreFieldModel {

  @FieldMapping(ignore = true)
  String field;
}
