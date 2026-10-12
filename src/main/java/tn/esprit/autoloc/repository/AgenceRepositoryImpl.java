package tn.esprit.autoloc.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;

@Repository
public class AgenceRepositoryImpl implements IAgenceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Agence save(Agence entite) {
        if (entite.getIdAgence() == null) {
            entityManager.persist(entite);
            return entite;
        }
        return entityManager.merge(entite);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Agence> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Agence.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agence> findAll() {
        return entityManager
                .createQuery("select e from Agence e", Agence.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return entityManager.find(Agence.class, id) != null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Agence entite = entityManager.find(Agence.class, id);
        if (entite != null) {
            entityManager.remove(entite);
        }
    }
}
