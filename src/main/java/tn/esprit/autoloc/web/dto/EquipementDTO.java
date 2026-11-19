package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipementDTO {
    private Long idEquipement;
    @NotBlank(message = "Le libelle est obligatoire")
    @Size(min = 2, max = 50, message = "Le libelle doit contenir entre 2 et 50 caractères")
    private String libelle;
}
