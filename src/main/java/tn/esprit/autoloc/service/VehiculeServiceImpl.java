package tn.esprit.autoloc.service;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.mapper.IMapper;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.repository.IEquipementRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;
import tn.esprit.autoloc.web.dto.VehiculeDTO;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;
    private final IAgenceRepository agenceRepository;
    private final IEquipementRepository equipementRepository;
    private final IMapper vehiculeMapper;
    private static final String ENTITY_NAME = "Maintenance";
    @Override
    public VehiculeDTO create(VehiculeDTO dto) {

        if (dto.getIdVehicule() != null) {
            throw new IllegalArgumentException(
                    "Nouveau véhicule : l'identifiant doit être null");
        }

        Vehicule vehicule = vehiculeMapper.toEntity(dto);

        // Affecter une agence si son identifiant est fourni
        if (dto.getIdAgence() != null) {
            Agence agence = agenceRepository.findById(dto.getIdAgence())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Agence", dto.getIdAgence()));

            vehicule.setAgence(agence);
        }

        Vehicule saved = vehiculeRepository.save(vehicule);

        return vehiculeMapper.toDto(saved);
    }

    @Override
    public VehiculeDTO findById(Long id) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(ENTITY_NAME, id));

        return vehiculeMapper.toDto(vehicule);
    }

    @Override
    public List<VehiculeDTO> findAll() {
        return vehiculeRepository.findAll().stream()
                .map(vehiculeMapper::toDto)
                .toList();
    }

    @Override
    public VehiculeDTO update(Long id, VehiculeDTO dto) {

        Vehicule existant = vehiculeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(ENTITY_NAME, id));

        existant.setImmatriculation(dto.getImmatriculation());
        existant.setMarque(dto.getMarque());
        existant.setModele(dto.getModele());
        existant.setCategorie(dto.getCategorie());
        existant.setTarifJournalier(dto.getTarifJournalier());
        existant.setStatut(dto.getStatut());

        Vehicule updated = vehiculeRepository.save(existant);

        return vehiculeMapper.toDto(updated);
    }

    // Affecter un véhicule à une agence
    @Override
    public VehiculeDTO affecterVehiculeAAgence(Long idVehicule, Long idAgence) {

        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() ->
                        new ResourceNotFoundException(ENTITY_NAME, idVehicule));

        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Agence", idAgence));

        vehicule.setAgence(agence);

        Vehicule updated = vehiculeRepository.save(vehicule);

        return vehiculeMapper.toDto(updated);
    }

    // Affecter un équipement à un véhicule
    @Override
    public VehiculeDTO affecterEquipementAVehicule(Long idVehicule, Long idEquipement) {

        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() ->
                        new ResourceNotFoundException(ENTITY_NAME, idVehicule));

        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Equipement", idEquipement));

        if (vehicule.getEquipements() == null) {
            vehicule.setEquipements(new HashSet<>());
        }

        vehicule.getEquipements().add(equipement);

        Vehicule updated = vehiculeRepository.save(vehicule);

        return vehiculeMapper.toDto(updated);
    }
// Supprimer un vehicule
    @Override
    public void deleteById(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException(ENTITY_NAME, id);
        }

        vehiculeRepository.deleteById(id);
    }

    // Afficher le vehicule à partir de l'idContrat
    @Override
    public VehiculeDTO findReservationVehiculeByIdContrat(Long id) {

        Vehicule vehicule = vehiculeRepository.findByReservationsContratIdContrat(id);

        if (vehicule == null) {
            throw new ResourceNotFoundException(ENTITY_NAME, id);
        }

        return vehiculeMapper.toDto(vehicule);
    }

    //Afficher un vehicule a partir de l'idMaintenance
    @Override
    public VehiculeDTO findVehiculeByIdMaintenance(Long id) {

        Vehicule vehicule = vehiculeRepository.findVehiculesByIdMaintenance(id);

        if (vehicule == null) {
            throw new ResourceNotFoundException(ENTITY_NAME, id);
        }
        return vehiculeMapper.toDto(vehicule);
    }


}