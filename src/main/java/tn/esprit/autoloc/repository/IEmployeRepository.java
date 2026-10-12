package tn.esprit.autoloc.repository;

import java.util.List;
import java.util.Optional;
import tn.esprit.autoloc.domain.Employe;

public interface IEmployeRepository {

    Employe save(Employe entite);

    Optional<Employe> findById(Long id);

    List<Employe> findAll();

    boolean existsById(Long id);

    void deleteById(Long id);
}
