package dev.tiltrikt.mapper.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;

/**
 * Specifies mapping details for fields. It is used in combination with {@link dev.tiltrikt.mapper.core.Mapper}.
 * If no {@code @FieldMapping} annotation is specified the default values apply:
 *
 * <pre>{@code
 *
 *    Example 1:
 *
 *    @FieldMapping(ignore = true)
 *    private final User;
 *
 *    Example 2:
 *
 *    @FieldMapping(TargetName = "userMessage")
 *    private final Message;
 *
 * }</pre>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldMapping {

  @NotNull String EMPTY_TARGET = "";

  /**
   * (Optional) The name which is used for mapping with {@link dev.tiltrikt.mapper.core.Mapper}.
   * Defaults to the field name.
   */
  @NotNull String targetName() default EMPTY_TARGET;

  /**
   * (Optional) Whether the field is included in mapping operations with {@link dev.tiltrikt.mapper.core.Mapper}.
   */
  boolean ignore() default false;
}
