package tn.esprit.autoloc.repository;

import java.util.List;
import java.util.Optional;
import tn.esprit.autoloc.domain.Equipement;

public interface IEquipementRepository {

    Equipement save(Equipement entite);

    Optional<Equipement> findById(Long id);

    List<Equipement> findAll();

    boolean existsById(Long id);

    void deleteById(Long id);
}
