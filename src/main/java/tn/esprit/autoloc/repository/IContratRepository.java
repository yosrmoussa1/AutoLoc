package tn.esprit.autoloc.repository;
import java.util.List;
import java.util.Optional;
import tn.esprit.autoloc.domain.Contrat;

public interface IContratRepository {

    Contrat save(Contrat entite);

    Optional<Contrat> findById(Long id);

    List<Contrat> findAll();

    boolean existsById(Long id);

    void deleteById(Long id);
}