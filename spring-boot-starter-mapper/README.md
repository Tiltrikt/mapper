# Spring-boot-starter-mapper

## Dependencies
```groovy
dependencies {
  implementation "dev.tiltrikt:mapper-starter-spring:<current version>"
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
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Service {

  @NotNull
  Mapper mapper;

  public void example() {
    mapper.map(source, target);
    mapper.map(source, Target.class);
  }
}
```
<br>

You could implement you custom **MappingProvider** for non-traditional mapping<br>
It would be automatically registered in **MappingManger** after starting of the application
```java
@Component
public class UserToUserDtoMappingProvider implements MappingProvider<User, UserDto> {

  @Override
  public @NotNull UserDto map(@NotNull User source, @NotNull UserDto target) {
    target.setName(source.getName() + "Dto");
    target.setSurname(source.getSurname() + "Dto");
    return target;
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