package dev.tiltrikt.mapper.test.provider;

import dev.tiltrikt.mapper.core.Mapper;
import dev.tiltrikt.mapper.core.MapperImpl;
import dev.tiltrikt.mapper.core.manager.DefaultMappingManager;
import dev.tiltrikt.mapper.test.provider.model.User;
import dev.tiltrikt.mapper.test.provider.model.UserDto;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MapperWithProviderTest {

  Mapper mapper;

  public MapperWithProviderTest() {
    DefaultMappingManager mappingManager = new DefaultMappingManager();
    mappingManager.setProviders(List.of(new UserToUserDtoMappingProvider()));
    MapperImpl mapperImpl = new MapperImpl();
    mapperImpl.setMappingManager(mappingManager);
    mapper = mapperImpl;
  }

  public void map() {
    User user = new User(1, "John", "Doe");
    System.out.println(user);

    UserDto userDto = mapper.map(user, UserDto.class);
    System.out.println(userDto);
  }
}
