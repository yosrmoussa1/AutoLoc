package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceDTO {
    private Long idMaintenance;
    @NotNull(message = "La date de début est obligatoire")
    private java.time.LocalDate dateDebut;
    @NotNull(message = "La date de fin est obligatoire")
    private java.time.LocalDate dateFin;
    @AssertTrue(message = "La date de fin ne peut pas être antérieure à la date de début")
    @NotNull(message = "La description est obligatoire")
    private String description;
    private Long idVehicule;
    @AssertTrue(message = "La date de fin ne peut pas être antérieure à la date de début")
    public boolean isDatesValid() {
        return dateDebut == null || dateFin == null
                || !dateFin.isBefore(dateDebut);
    }
}
