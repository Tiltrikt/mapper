package dev.tiltrikt.mapper.simple.model;

import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

  String name;

  String username;
}
