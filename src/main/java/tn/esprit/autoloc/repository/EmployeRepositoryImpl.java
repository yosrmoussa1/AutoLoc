package tn.esprit.autoloc.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Employe;

@Repository
public class EmployeRepositoryImpl implements IEmployeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Employe save(Employe entite) {
        if (entite.getIdEmploye() == null) {
            entityManager.persist(entite);
            return entite;
        }
        return entityManager.merge(entite);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employe> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Employe.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employe> findAll() {
        return entityManager
                .createQuery("select e from Employe e", Employe.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return entityManager.find(Employe.class, id) != null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Employe entite = entityManager.find(Employe.class, id);
        if (entite != null) {
            entityManager.remove(entite);
        }
    }
}
