package tn.esprit.autoloc.repository;

import tn.esprit.autoloc.domain.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMaintenanceRepository
        extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByVehiculeIdVehicule(Long idVehicule);
}