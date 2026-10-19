package be.iccbxl.pid.reservationsspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

/**
 * Configuration de Thymeleaf : active le système de layouts.
 *
 * @author Julio Ramos do Carmo
 */
@Configuration
public class ThymeleafConfiguration {

    // Rend le Layout Dialect disponible pour tous les templates
    @Bean
    public LayoutDialect thymeleafDialect() {
        return new LayoutDialect();
    }
}