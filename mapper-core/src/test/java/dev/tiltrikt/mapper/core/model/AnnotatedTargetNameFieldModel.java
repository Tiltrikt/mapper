package dev.tiltrikt.mapper.core.model;

import dev.tiltrikt.mapper.core.basic.annotation.FieldMapping;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnnotatedTargetNameFieldModel {

  @FieldMapping(targetName = ANNOTATION_TARGET_NAME)
  String field;

  @FieldMapping(ignore = true)
  public final static String ANNOTATION_TARGET_NAME = "target";
}
