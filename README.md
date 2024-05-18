# Mapper

## Description
Simple mapper based on java reflections. The advantage of this implementation is that project is small and easy configurable.

## Dependencies
```groovy
dependencies {
  implementation "dev.tiltrikt:mapper-starter-spring:1.0.0"
}

repositories {
  mavenCentral()
  maven {
    name = "GitHubPackages"
    url = uri("https://maven.pkg.github.com/tiltrikt/mapper")
  }
}
```

## Get started

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
    mapper.map(source, target, mappingSchema);
    mapper.map(source, Target.class, mappingSchema);
  }
}
```

In case of need you can also configurate Mapper by yourself:
```java
@Configuration
public class MapperConfiguration() {
  
  @Bean
  public Mapper mapper() {
    MapperImpl mapper = new MapperImpl();
    mapper.setObjectFactory(yourObjectFactory);
    mapper.setMappingSchemaResolver(yourMappingSchemaResolver);
    mapper.setMappingSchemaProcessor(yourMappingSchemaProcessor);
    return mapper;
  }
}
```

## Collaborator
temez: https://github.com/temez