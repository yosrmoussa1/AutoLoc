package tn.esprit.autoloc.repository;

import java.util.List;
import java.util.Optional;
import tn.esprit.autoloc.domain.Vehicule;

public interface IVehiculeRepository {

    Vehicule save(Vehicule entite);

    Optional<Vehicule> findById(Long id);

    List<Vehicule> findAll();

    boolean existsById(Long id);

    void deleteById(Long id);
}
