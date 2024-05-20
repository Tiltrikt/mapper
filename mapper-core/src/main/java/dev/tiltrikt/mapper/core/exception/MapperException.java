package dev.tiltrikt.mapper.core.exception;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A custom runtime exception used to indicate errors in mapping operations.
 * This class extends {@link RuntimeException} and provides multiple constructors
 * to create an instance of {@code MapperException} with various levels of details
 * regarding the error.
 */
public class MapperException extends RuntimeException {

  /**
   * Constructs a new {@code MapperException} with {@code null} as its detail message.
   * The cause is not specified.
   */
  public MapperException() {
  }

  /**
   * Constructs a new {@code MapperException} with a formatted detail message.
   * The message is formatted using {@link String#format(String, Object...)}.
   *
   * @param message the detail message, which can include format specifiers
   *                as described in {@link java.util.Formatter}.
   * @param params  the parameters referenced by the format specifiers in the message.
   */
  public MapperException(@NotNull String message, Object @NotNull ... params) {
    super(String.format(message, params));
  }

  /**
   * Constructs a new {@code MapperException} with the specified detail message.
   *
   * @param message the detail message, which is saved for later retrieval by
   *                the {@link Throwable#getMessage()} method.
   */
  public MapperException(@NotNull String message) {
    super(message);
  }

  /**
   * Constructs a new {@code MapperException} with the specified detail message
   * and cause.
   * <p>Note that the detail message associated with
   * {@code cause} is <i>not</i> automatically incorporated in
   * this runtime exception's detail message.
   *
   * @param  message the detail message (which is saved for later retrieval
   *         by the {@link #getMessage()} method).
   * @param  cause the cause (which is saved for later retrieval by the
   *         {@link #getCause()} method).  (A {@code null} value is
   *         permitted, and indicates that the cause is nonexistent or
   *         unknown.)
   */
  public MapperException(@NotNull String message, @Nullable Throwable cause) {
    super(message, cause);
  }
}
