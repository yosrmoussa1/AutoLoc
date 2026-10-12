package tn.esprit.autoloc.repository;

import java.util.List;
import java.util.Optional;
import tn.esprit.autoloc.domain.Reservation;

public interface IReservationRepository {

    Reservation save(Reservation entite);

    Optional<Reservation> findById(Long id);

    List<Reservation> findAll();

    boolean existsById(Long id);

    void deleteById(Long id);
}
