package com.example.restapp.config;

// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.*;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl") // nome do bean da classe com @Component
public class JpaConfig
{
    // @Bean
    // public AuditorAwareImpl auditorAware()
    // {
    //     return new AuditorAwareImpl();
    // }
}
