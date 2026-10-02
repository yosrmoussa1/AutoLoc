package tn.esprit.autoloc.domain;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table( name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    @Column( nullable =false, length = 30)
    private String nom;
    @Column( nullable =false, length = 30)
    private String prenom;
    @Column( nullable =false, length = 30)
    private String email;
    @Column( nullable =false, length = 20)
    private String telephone;
    @Column( nullable =false, length = 20)
    private String numPermis;
    @Column ( nullable =false)
    private LocalDate dateInscription;
}
