package dev.tiltrikt.mapper.test.provider.model;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

  String name;

  String surname;
}
