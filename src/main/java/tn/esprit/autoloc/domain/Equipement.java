package tn.esprit.autoloc.domain;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table( name = "equipement")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Equipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;
    @Column (nullable = false, length = 20)
    private String libelle;
}
