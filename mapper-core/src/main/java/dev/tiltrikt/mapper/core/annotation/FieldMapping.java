package dev.tiltrikt.mapper.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldMapping {

  @NotNull String EMPTY_TARGET = "";

  @NotNull String targetName() default EMPTY_TARGET;

  boolean ignore() default false;
}
