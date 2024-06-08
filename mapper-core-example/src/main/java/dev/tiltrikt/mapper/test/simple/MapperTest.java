package dev.tiltrikt.mapper.test.simple;

import dev.tiltrikt.mapper.core.Mapper;
import dev.tiltrikt.mapper.core.MapperImpl;
import dev.tiltrikt.mapper.test.simple.model.User;
import dev.tiltrikt.mapper.test.simple.model.UserDto;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MapperTest {

  Mapper mapper = new MapperImpl();

  public void map() {
    User user = new User(1, "John", "Doe");
    System.out.println(user);

    UserDto userDto = mapper.map(user, UserDto.class);
    System.out.println(userDto);
  }
}
