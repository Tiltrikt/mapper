package dev.tiltrikt.mapper.starter;

import dev.tiltrikt.mapper.core.manager.DefaultMappingManager;
import dev.tiltrikt.mapper.core.manager.MappingManager;
import dev.tiltrikt.mapper.core.provider.MappingProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MappingManagerAutoconfiguration {

  @Bean
  @ConditionalOnMissingBean(MappingManager.class)
  public MappingManager mappingManager(@NotNull List<MappingProvider<?, ?>> mappingProviders) {
    DefaultMappingManager mappingManager = new DefaultMappingManager();
    mappingManager.setProviders(mappingProviders);
    return mappingManager;
  }
}
