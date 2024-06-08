package dev.tiltrikt.mapper.test.simple.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

  int id;

  String name;

  String surname;
}
