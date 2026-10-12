package tn.esprit.autoloc.repository;

import java.util.List;
import java.util.Optional;
import tn.esprit.autoloc.domain.Maintenance;

public interface IMaintenanceRepository {

    Maintenance save(Maintenance entite);

    Optional<Maintenance> findById(Long id);

    List<Maintenance> findAll();

    boolean existsById(Long id);

    void deleteById(Long id);
}
