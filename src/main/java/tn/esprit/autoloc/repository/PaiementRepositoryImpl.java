package tn.esprit.autoloc.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Paiement;

@Repository
public class PaiementRepositoryImpl implements IPaiementRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Paiement save(Paiement entite) {
        if (entite.getIdPaiement() == null) {
            entityManager.persist(entite);
            return entite;
        }
        return entityManager.merge(entite);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Paiement> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Paiement.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paiement> findAll() {
        return entityManager
                .createQuery("select e from Paiement e", Paiement.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return entityManager.find(Paiement.class, id) != null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Paiement entite = entityManager.find(Paiement.class, id);
        if (entite != null) {
            entityManager.remove(entite);
        }
    }
}
