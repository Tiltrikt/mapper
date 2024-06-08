package dev.tiltrikt.mapper.starter;

import dev.tiltrikt.mapper.core.Mapper;
import dev.tiltrikt.mapper.core.MapperImpl;
import dev.tiltrikt.mapper.core.manager.MappingManager;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StarterMapperAutoconfiguration {

  @Bean
  @ConditionalOnMissingBean(Mapper.class)
  public Mapper mapper(@NotNull MappingManager mappingManager) {

    MapperImpl mapper = new MapperImpl();
    mapper.setMappingManager(mappingManager);
    return mapper;
  }

}
