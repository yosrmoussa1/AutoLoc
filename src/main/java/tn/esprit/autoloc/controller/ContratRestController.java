
package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
@Tag(
        name = "Contrats",
        description = "Gestion des contrats de location (créer, consulter, modifier et supprimer des contrats)"
)
public class ContratRestController {

    private final IContratService contratService;

    // Créer un contrat
    @PostMapping
    @Operation(
            summary = "Créer un contrat",
            description = "Crée un nouveau contrat de location."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Contrat créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Montant total invalide")
    })
    public ResponseEntity<Contrat> ajouter(
            @RequestBody Contrat contrat) {

        Contrat nouveauContrat = contratService.create(contrat);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(nouveauContrat);
    }

    // Afficher tous les contrats
    @GetMapping
    @Operation(
            summary = "Lister les contrats",
            description = "Retourne tous les contrats enregistrés."
    )
    @ApiResponse(responseCode = "200", description = "Liste des contrats récupérée")
    public ResponseEntity<List<Contrat>> afficherTous() {
        return ResponseEntity.ok(contratService.findAll());
    }

    // Afficher un contrat par ID
    @GetMapping("/{id}")
    @Operation(
            summary = "Consulter un contrat",
            description = "Recherche un contrat par son identifiant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrat trouvé"),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    })
    public ResponseEntity<Contrat> afficher(
            @Parameter(description = "Identifiant du contrat")
            @PathVariable Long id) {

        return ResponseEntity.ok(contratService.findById(id));
    }

    // Modifier un contrat
    @PutMapping("/{id}")
    @Operation(
            summary = "Modifier un contrat",
            description = "Met à jour un contrat existant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrat modifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Montant total invalide"),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    })
    public ResponseEntity<Contrat> modifier(
            @PathVariable Long id,
            @RequestBody Contrat contrat) {

        Contrat contratModifie = contratService.update(id, contrat);

        return ResponseEntity.ok(contratModifie);
    }

    // Supprimer un contrat
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Supprimer un contrat",
            description = "Supprime un contrat. Les paiements associés sont supprimés par cascade si la relation est configurée ainsi."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Contrat supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    })
    public ResponseEntity<Void> supprimer(
            @PathVariable Long id) {

        contratService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
