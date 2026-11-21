package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.autoloc.domain.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IVehiculeRepository
        extends JpaRepository<Vehicule, Long> {

    Vehicule findByReservationsContratIdContrat(Long idContrat);

    @Query("SELECT m.vehicule FROM Maintenance m WHERE m.idMaintenance = :idMaintenance")
    Vehicule findVehiculesByIdMaintenance(@Param("idMaintenance") Long idMaintenance);
}