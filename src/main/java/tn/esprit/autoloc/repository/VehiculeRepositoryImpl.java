package tn.esprit.autoloc.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Vehicule;

@Repository
public class VehiculeRepositoryImpl implements IVehiculeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Vehicule save(Vehicule entite) {
        if (entite.getIdVehicule() == null) {
            entityManager.persist(entite);
            return entite;
        }
        return entityManager.merge(entite);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vehicule> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Vehicule.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> findAll() {
        return entityManager
                .createQuery("select e from Vehicule e", Vehicule.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return entityManager.find(Vehicule.class, id) != null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Vehicule entite = entityManager.find(Vehicule.class, id);
        if (entite != null) {
            entityManager.remove(entite);
        }
    }
}
