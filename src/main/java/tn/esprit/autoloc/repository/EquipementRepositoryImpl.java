package tn.esprit.autoloc.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Equipement;

@Repository
public class EquipementRepositoryImpl implements IEquipementRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Equipement save(Equipement entite) {
        if (entite.getIdEquipement() == null) {
            entityManager.persist(entite);
            return entite;
        }
        return entityManager.merge(entite);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Equipement> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Equipement.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Equipement> findAll() {
        return entityManager
                .createQuery("select e from Equipement e", Equipement.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return entityManager.find(Equipement.class, id) != null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Equipement entite = entityManager.find(Equipement.class, id);
        if (entite != null) {
            entityManager.remove(entite);
        }
    }
}
