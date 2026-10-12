package tn.esprit.autoloc.repository;

import java.util.List;
import java.util.Optional;
import tn.esprit.autoloc.domain.Paiement;

public interface IPaiementRepository {

    Paiement save(Paiement entite);

    Optional<Paiement> findById(Long id);

    List<Paiement> findAll();

    boolean existsById(Long id);

    void deleteById(Long id);
}
