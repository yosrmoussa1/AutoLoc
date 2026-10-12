package tn.esprit.autoloc.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Maintenance;

@Repository
public class MaintenanceRepositoryImpl implements IMaintenanceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Maintenance save(Maintenance entite) {
        if (entite.getIdMaintenance() == null) {
            entityManager.persist(entite);
            return entite;
        }
        return entityManager.merge(entite);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Maintenance> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Maintenance.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Maintenance> findAll() {
        return entityManager
                .createQuery("select e from Maintenance e", Maintenance.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return entityManager.find(Maintenance.class, id) != null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Maintenance entite = entityManager.find(Maintenance.class, id);
        if (entite != null) {
            entityManager.remove(entite);
        }
    }
}
