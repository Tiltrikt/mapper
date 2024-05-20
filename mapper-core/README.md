# Mapper-starter-spring

## Dependencies
```groovy
dependencies {
  implementation "dev.tiltrikt:mapper-core:1.0.0"
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
    mapper.map(source, target, mappingSchema);
    mapper.map(source, Target.class, mappingSchema);
  }
}
```

In case of need you can also configurate Mapper by yourself:
```java
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Service {

  @NotNull
  Mapper mapper;
  
  public Service() {
    MapperImpl mapperImpl= new MapperImpl();
    mapperImpl.setObjectFactory(yourObjectFactory);
    mapperImpl.setMappingSchemaResolver(yourMappingSchemaResolver);
    mapperImpl.setMappingSchemaProcessor(yourMappingSchemaProcessor);
    mapper = mapperImpl;
  }
}
```