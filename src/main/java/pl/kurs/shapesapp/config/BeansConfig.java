package pl.kurs.shapesapp.config;


import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Set;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class BeansConfig {

    @Bean
    public ModelMapper modelMapper(Set<Converter> converters) {
        ModelMapper modelMapper = new ModelMapper();
        converters.forEach(modelMapper::addConverter);
        return modelMapper;
    }

    @Bean
    AuditorAware<String> auditorProvider() {
        return new SpringSecurityAuditorAware();
    }
}
