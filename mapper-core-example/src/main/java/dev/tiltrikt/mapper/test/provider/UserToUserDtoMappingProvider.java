package dev.tiltrikt.mapper.test.provider;

import dev.tiltrikt.mapper.core.provider.MappingProvider;
import dev.tiltrikt.mapper.test.provider.model.User;
import dev.tiltrikt.mapper.test.provider.model.UserDto;
import org.jetbrains.annotations.NotNull;

public class UserToUserDtoMappingProvider implements MappingProvider<User, UserDto> {

  @Override
  public @NotNull UserDto map(@NotNull User source, @NotNull UserDto target) {
    System.out.println("Invoking custom mapping provider...");
    target.setName(source.getName() + "Dto");
    target.setSurname(source.getSurname() + "Dto");
    return target;
  }
}
