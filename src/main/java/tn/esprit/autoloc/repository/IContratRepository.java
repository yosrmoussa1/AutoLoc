package tn.esprit.autoloc.repository;

import tn.esprit.autoloc.domain.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IContratRepository
        extends JpaRepository<Contrat, Long> {
    List<Contrat> findByReservationVehiculeIdVehicule(Long idVehicule);
}
