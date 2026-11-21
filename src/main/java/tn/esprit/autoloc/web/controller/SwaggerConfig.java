 package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    private static final String CONTROLLER_PACKAGE = "tn.esprit.autoloc.web.controller";
    // Configuration générale de Swagger
    @Bean
    public OpenAPI autolocOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("AutoLoc API")
                        .description("API REST de gestion de location de véhicules")
                        .version("1.0.0"));
    }

    // Documentation des agences
    @Bean
    public GroupedOpenApi agenceApi() {
        return GroupedOpenApi.builder()
                .group("Gestion d'Agences")
                .packagesToScan(CONTROLLER_PACKAGE)
                .pathsToMatch("/api/agences/**")
                .build();
    }

    // Documentation des employés
    @Bean
    public GroupedOpenApi employeApi() {
        return GroupedOpenApi.builder()
                .group("Gestion des Employés")
                .packagesToScan(CONTROLLER_PACKAGE)
                .pathsToMatch("/api/employes/**")
                .build();
    }

    // Documentation des véhicules
    @Bean
    public GroupedOpenApi vehiculeApi() {
        return GroupedOpenApi.builder()
                .group("Gestion des Véhicules")
                .packagesToScan(CONTROLLER_PACKAGE)
                .pathsToMatch("/api/vehicules/**")
                .build();
    }

    // Documentation des équipements
    @Bean
    public GroupedOpenApi equipementApi() {
        return GroupedOpenApi.builder()
                .group("Gestion des Équipements")
                .packagesToScan(CONTROLLER_PACKAGE)
                .pathsToMatch("/api/equipements/**")
                .build();
    }

    // Documentation des clients
    @Bean
    public GroupedOpenApi clientApi() {
        return GroupedOpenApi.builder()
                .group("Gestion des Clients")
                .packagesToScan(CONTROLLER_PACKAGE)
                .pathsToMatch("/api/clients/**")
                .build();
    }

    // Documentation des réservations
    @Bean
    public GroupedOpenApi reservationApi() {
        return GroupedOpenApi.builder()
                .group("Gestion des Réservations")
                .packagesToScan(CONTROLLER_PACKAGE)
                .pathsToMatch("/api/reservations/**")
                .build();
    }

    // Documentation des contrats
    @Bean
    public GroupedOpenApi contratApi() {
        return GroupedOpenApi.builder()
                .group("Gestion des Contrats")
                .packagesToScan(CONTROLLER_PACKAGE)
                .pathsToMatch("/api/contrats/**")
                .build();
    }

    // Documentation des paiements
    @Bean
    public GroupedOpenApi paiementApi() {
        return GroupedOpenApi.builder()
                .group("Gestion des Paiements")
                .packagesToScan(CONTROLLER_PACKAGE)
                .pathsToMatch("/api/paiements/**")
                .build();
    }

    // Documentation des maintenances
    @Bean
    public GroupedOpenApi maintenanceApi() {
        return GroupedOpenApi.builder()
                .group("Gestion des Maintenances")
                .packagesToScan(CONTROLLER_PACKAGE)
                .pathsToMatch("/api/maintenances/**")
                .build();
    }
}
