package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@Tag(
        name = "Gestion des agences",
        description = "Gestion des agences AutoLoc (créer, consulter, modifier et supprimer des agences)."
)
public class AgenceRestController {

    private final IAgenceService agenceService;

    public AgenceRestController(IAgenceService agenceService) {
        this.agenceService = agenceService;
    }

    // Ajouter une agence
    @PostMapping("/ajouter")
    @Operation(
            summary = "Ajouter une agence",
            description = "Crée une nouvelle agence à partir des données envoyées en JSON."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agence créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public ResponseEntity<Agence> ajouterAgence(
            @RequestBody Agence agence) {

        Agence nouvelleAgence = agenceService.create(agence);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nouvelleAgence);
    }

    // Afficher toutes les agences
    @GetMapping("/afficher")
    @Operation(
            summary = "Afficher toutes les agences",
            description = "Retourne la liste complète des agences enregistrées."
    )
    @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès")
    public ResponseEntity<List<Agence>> afficherAgences() {
        return ResponseEntity.ok(agenceService.findAll());
    }

    // Afficher une agence par ID
    @GetMapping("/afficher/{id}")
    @Operation(
            summary = "Afficher une agence",
            description = "Recherche et retourne une agence à partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence trouvée"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    public ResponseEntity<Agence> afficherAgence(
            @PathVariable Long id) {

        return ResponseEntity.ok(agenceService.findById(id));
    }

    // Modifier une agence
    @PutMapping("/modifier/{id}")
    @Operation(
            summary = "Modifier une agence",
            description = "Met à jour les informations de l'agence correspondant à l'identifiant fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence modifiée avec succès"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    public ResponseEntity<Agence> modifierAgence(
            @PathVariable Long id,
            @RequestBody Agence agence) {

        Agence agenceModifiee =
                agenceService.update(id, agence);

        return ResponseEntity.ok(agenceModifiee);
    }

    // Supprimer une agence
    @DeleteMapping("/supprimer/{id}")
    @Operation(
            summary = "Supprimer une agence",
            description = "Supprime l'agence correspondant à l'identifiant fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Agence supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    public ResponseEntity<Void> supprimerAgence(
            @PathVariable Long id) {

        agenceService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}