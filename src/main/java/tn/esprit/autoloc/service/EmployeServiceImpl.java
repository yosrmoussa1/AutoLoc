package tn.esprit.autoloc.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IEmployeRepository;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private final IEmployeRepository employeRepository;

    @Override
    public Employe create(Employe entite) {
        if (entite.getIdEmploye() != null) {
            throw new IllegalArgumentException(
                    "Nouvelle entité Employe : l'identifiant doit être null");
        }
        valider(entite);
        return employeRepository.save(entite);
    }

    @Override
    public Employe findById(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employe", id));
    }

    @Override
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    @Override
    public Employe update(Long id, Employe entite) {
        Employe existant = findById(id);
        valider(entite);
        // Les associations (liens vers d'autres entités) ne sont pas recopiées ici :
        // leur gestion est l'objet du Chapitre 9.
        existant.setNom(entite.getNom());
        existant.setPrenom(entite.getPrenom());
        existant.setRole(entite.getRole());
        return employeRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!employeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employe", id);
        }
        employeRepository.deleteById(id);
    }

    private void valider(Employe entite) {
        if (entite.getNom() == null || entite.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom de l'employé est obligatoire");
        }
        if (entite.getPrenom() == null || entite.getPrenom().isBlank()) {
            throw new IllegalArgumentException("Le prénom de l'employé est obligatoire");
        }
    }
}
