package tn.esprit.autoloc.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Client;

@Repository
public class ClientRepositoryImpl implements IClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Client save(Client entite) {
        if (entite.getIdClient() == null) {
            entityManager.persist(entite);
            return entite;
        }
        return entityManager.merge(entite);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Client.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return entityManager
                .createQuery("select e from Client e", Client.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return entityManager.find(Client.class, id) != null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Client entite = entityManager.find(Client.class, id);
        if (entite != null) {
            entityManager.remove(entite);
        }
    }
}
