package tn.esprit.autoloc.repository;

import java.util.List;
import java.util.Optional;
import tn.esprit.autoloc.domain.Agence;

public interface IAgenceRepository {

    Agence save(Agence entite);

    Optional<Agence> findById(Long id);

    List<Agence> findAll();

    boolean existsById(Long id);

    void deleteById(Long id);
}