package dev.tiltrikt.mapper.starter.configuration;

import dev.tiltrikt.mapper.core.Mapper;
import dev.tiltrikt.mapper.core.MapperImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StarterMapperAutoconfiguration {

  @Bean
  @ConditionalOnMissingBean(Mapper.class)
  public Mapper mapperMap() {
    return new MapperImpl();
  }

}
