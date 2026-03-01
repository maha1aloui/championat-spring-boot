package tn.esprit.ds.championat.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration globale de l'API OpenAPI / Swagger UI.
 * Accessible via : http://localhost:8080/swagger-ui/index.html
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI championatOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Championat API")
                        .description("API REST de gestion du championnat de course automobile - ESPRIT DS")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Équipe ESPRIT DS")
                                .email("contact@esprit.tn")
                                .url("https://esprit.tn"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8089/championnat")
                                .description("Serveur de développement local")
                ));
    }
}
