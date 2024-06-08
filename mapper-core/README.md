# Mapper-core

## Dependencies
```groovy
dependencies {
  implementation "dev.tiltrikt:mapper-core:<current version>"
}

repositories {
  mavenCentral()
  maven {
    name = "GitHubPackages"
    url = uri("https://maven.pkg.github.com/tiltrikt/mapper")
  }
}
```

## Example
```java
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Service {

  @NotNull
  Mapper mapper = new MapperImpl();

  public void example() {
    mapper.map(source, target);
    mapper.map(source, Target.class);
  }
}
```
<br>

You could implement you custom **MappingProvider** for non-traditional mapping
```java
public class UserToUserDtoMappingProvider implements MappingProvider<User, UserDto> {

  @Override
  public @NotNull UserDto map(@NotNull User source, @NotNull UserDto target) {
    target.setName(source.getName() + "Dto");
    target.setSurname(source.getSurname() + "Dto");
    return target;
  }
}
```

And then use it
```java
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
}
```
<br>

You can also specify how fields should be mapped:
```java
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Dto {
  
  @FieldMapping(ignore = true)
  User user;

  @FieldMapping(targetName = "userMessage")
  Message message;
}
```