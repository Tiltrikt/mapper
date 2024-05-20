## mapper-starter-spring

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