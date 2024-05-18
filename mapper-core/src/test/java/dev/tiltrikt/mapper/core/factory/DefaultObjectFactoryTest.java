package dev.tiltrikt.mapper.core.factory;

import dev.tiltrikt.mapper.core.exception.MissingConstructorException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class DefaultObjectFactoryTest {

  ObjectFactory objectFactory = new DefaultObjectFactory();

  @Test
  void givenTargetClass_whenCreateInstance_thenReturnedInstanceOfTargetClass() {
    assertInstanceOf(String.class, objectFactory.createInstance(String.class));
  }

  @Test
  void givenTargetClassWithoutNoArgsConstructor_whenCreateInstance_thenThrownException() {
    assertThrows(MissingConstructorException.class, () -> objectFactory.createInstance(Optional.class));
  }
}