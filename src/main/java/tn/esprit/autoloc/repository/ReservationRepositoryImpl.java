package tn.esprit.autoloc.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Reservation;

@Repository
public class ReservationRepositoryImpl implements IReservationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Reservation save(Reservation entite) {
        if (entite.getIdReservation() == null) {
            entityManager.persist(entite);
            return entite;
        }
        return entityManager.merge(entite);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reservation> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Reservation.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> findAll() {
        return entityManager
                .createQuery("select e from Reservation e", Reservation.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return entityManager.find(Reservation.class, id) != null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Reservation entite = entityManager.find(Reservation.class, id);
        if (entite != null) {
            entityManager.remove(entite);
        }
    }
}
