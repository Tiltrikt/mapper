package dev.tiltrikt.mapper.provider;

import dev.tiltrikt.mapper.core.provider.MappingProvider;
import dev.tiltrikt.mapper.provider.model.User;
import dev.tiltrikt.mapper.provider.model.UserDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoMappingProvider implements MappingProvider<User, UserDto> {

  @Override
  public @NotNull UserDto map(@NotNull User source, @NotNull UserDto target) {
    System.out.println("Invoking custom mapping provider...");
    target.setName(source.getName() + "Dto");
    target.setSurname(source.getSurname() + "Dto");
    return target;
  }
}
