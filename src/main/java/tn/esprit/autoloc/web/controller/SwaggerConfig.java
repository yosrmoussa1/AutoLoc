 package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

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
                .packagesToScan("tn.esprit.autoloc.web.controller")
                .pathsToMatch("/api/agences/**")
                .build();
    }

    // Documentation des employés
    @Bean
    public GroupedOpenApi employeApi() {
        return GroupedOpenApi.builder()
                .group("Gestion des Employés")
                .packagesToScan("tn.esprit.autoloc.web.controller")
                .pathsToMatch("/api/employes/**")
                .build();
    }

    // Documentation des véhicules
    @Bean
    public GroupedOpenApi vehiculeApi() {
        return GroupedOpenApi.builder()
                .group("Gestion des Véhicules")
                .packagesToScan("tn.esprit.autoloc.web.controller")
                .pathsToMatch("/api/vehicules/**")
                .build();
    }

    // Documentation des équipements
    @Bean
    public GroupedOpenApi equipementApi() {
        return GroupedOpenApi.builder()
                .group("Gestion des Équipements")
                .packagesToScan("tn.esprit.autoloc.web.controller")
                .pathsToMatch("/api/equipements/**")
                .build();
    }

    // Documentation des clients
    @Bean
    public GroupedOpenApi clientApi() {
        return GroupedOpenApi.builder()
                .group("Gestion des Clients")
                .packagesToScan("tn.esprit.autoloc.web.controller")
                .pathsToMatch("/api/clients/**")
                .build();
    }

    // Documentation des réservations
    @Bean
    public GroupedOpenApi reservationApi() {
        return GroupedOpenApi.builder()
                .group("Gestion des Réservations")
                .packagesToScan("tn.esprit.autoloc.web.controller")
                .pathsToMatch("/api/reservations/**")
                .build();
    }

    // Documentation des contrats
    @Bean
    public GroupedOpenApi contratApi() {
        return GroupedOpenApi.builder()
                .group("Gestion des Contrats")
                .packagesToScan("tn.esprit.autoloc.web.controller")
                .pathsToMatch("/api/contrats/**")
                .build();
    }

    // Documentation des paiements
    @Bean
    public GroupedOpenApi paiementApi() {
        return GroupedOpenApi.builder()
                .group("Gestion des Paiements")
                .packagesToScan("tn.esprit.autoloc.web.controller")
                .pathsToMatch("/api/paiements/**")
                .build();
    }

    // Documentation des maintenances
    @Bean
    public GroupedOpenApi maintenanceApi() {
        return GroupedOpenApi.builder()
                .group("Gestion des Maintenances")
                .packagesToScan("tn.esprit.autoloc.web.controller")
                .pathsToMatch("/api/maintenances/**")
                .build();
    }
}
