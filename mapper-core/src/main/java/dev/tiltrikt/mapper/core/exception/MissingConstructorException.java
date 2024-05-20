package dev.tiltrikt.mapper.core.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Thrown to indicate that appropriate constructor is not found. This will be default constructor mostly.
 * @see MapperException
 */
public class MissingConstructorException extends MapperException {

  /**
   * Constructs a new {@code MissingConstructorException} with a formatted detail message.
   * The message is formatted using {@link String#format(String, Object...)}.
   *
   * @param message the detail message, which can include format specifiers
   *                as described in {@link java.util.Formatter}.
   * @param params  the parameters referenced by the format specifiers in the message.
   */
  public MissingConstructorException(@NotNull String message, Object @NotNull ... params) {
    super(message, params);
  }
}
