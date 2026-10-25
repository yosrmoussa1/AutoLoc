
package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
@RequiredArgsConstructor
@Tag(name = "Équipements", description = "Gestion des équipements AutoLoc (créer, consulter, modifier et supprimer des équipements)")
public class EquipementRestController {

    private final IEquipementService equipementService;

    @PostMapping
    @Operation(summary = "Créer un équipement")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Équipement créé"),
            @ApiResponse(responseCode = "400", description = "Libellé ou quantité invalide")
    })
    public ResponseEntity<Equipement> ajouter(
            @RequestBody Equipement equipement) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(equipementService.create(equipement));
    }

    @GetMapping
    @Operation(summary = "Lister les équipements")
    @ApiResponse(responseCode = "200", description = "Liste des équipements")
    public ResponseEntity<List<Equipement>> afficherTous() {
        return ResponseEntity.ok(equipementService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un équipement")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Équipement trouvé"),
            @ApiResponse(responseCode = "404", description = "Équipement introuvable")
    })
    public ResponseEntity<Equipement> afficher(
            @Parameter(description = "Identifiant de l'équipement")
            @PathVariable Long id) {
        return ResponseEntity.ok(equipementService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un équipement")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Équipement modifié"),
            @ApiResponse(responseCode = "400", description = "Libellé ou quantité invalide"),
            @ApiResponse(responseCode = "404", description = "Équipement introuvable")
    })
    public ResponseEntity<Equipement> modifier(
            @PathVariable Long id,
            @RequestBody Equipement equipement) {
        return ResponseEntity.ok(equipementService.update(id, equipement));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un équipement")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Équipement supprimé"),
            @ApiResponse(responseCode = "404", description = "Équipement introuvable")
    })
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        equipementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
