package dev.tiltrikt.mapper.core.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Thrown to indicate that error is related to mapping schema.
 * @see MapperException
 */
public class MappingSchemaException extends MapperException {

  /**
   * Constructs a new {@code MappingSchemaException} with a formatted detail message.
   * The message is formatted using {@link String#format(String, Object...)}.
   *
   * @param message the detail message, which can include format specifiers
   *                as described in {@link java.util.Formatter}.
   * @param params  the parameters referenced by the format specifiers in the message.
   */
  public MappingSchemaException(@NotNull String message, Object @NotNull ... params) {
    super(message, params);
  }
}
