package ru.averkiev.testservice.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Настройка конфигурации Spring.
 * @author mrGreenNV
 */
@Configuration
@ComponentScan(basePackages = "ru.averkiev.testservice")
@EnableJpaRepositories(basePackages = "ru.averkiev.testservice.repositories")
public class SpringConfig {

    /**
     * Создание Bean для преобразования DTO к модели и наоборот.
     * @return объект ModelMapper.
     */
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
