package tn.esprit.autoloc.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;

@Repository
public class ContratRepositoryImpl implements IContratRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Contrat save(Contrat entite) {
        if (entite.getIdContrat() == null) {
            entityManager.persist(entite);
            return entite;
        }
        return entityManager.merge(entite);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Contrat> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Contrat.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contrat> findAll() {
        return entityManager
                .createQuery("select e from Contrat e", Contrat.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return entityManager.find(Contrat.class, id) != null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Contrat entite = entityManager.find(Contrat.class, id);
        if (entite != null) {
            entityManager.remove(entite);
        }
    }
}
