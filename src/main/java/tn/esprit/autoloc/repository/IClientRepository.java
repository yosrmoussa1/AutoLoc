package tn.esprit.autoloc.repository;


import java.util.List;
import java.util.Optional;
import tn.esprit.autoloc.domain.Client;

public interface IClientRepository {

    Client save(Client entite);

    Optional<Client> findById(Long id);

    List<Client> findAll();

    boolean existsById(Long id);

    void deleteById(Long id);
}
