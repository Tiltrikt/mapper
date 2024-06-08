package dev.tiltrikt.mapper.provider;

import dev.tiltrikt.mapper.core.Mapper;
import dev.tiltrikt.mapper.provider.model.User;
import dev.tiltrikt.mapper.provider.model.UserDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MapperWithProviderTest implements ApplicationRunner {

  @NotNull
  Mapper mapper;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    User user = new User(1, "John", "Doe");
    System.out.println(user);

    UserDto userDto = mapper.map(user, UserDto.class);
    System.out.println(userDto);
  }
}
