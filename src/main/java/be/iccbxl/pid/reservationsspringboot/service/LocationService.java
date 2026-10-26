package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.model.Location;
import be.iccbxl.pid.reservationsspringboot.repository.LocationRepository;

/**
 * Logique métier des lieux de spectacle.
 *
 * @author Julio Ramos do Carmo
 */
@Service
public class LocationService {

    @Autowired
    private LocationRepository repository;

    // Retourne la liste de tous les lieux
    public List<Location> getAll() {
        List<Location> locations = new ArrayList<>();
        repository.findAll().forEach(locations::add);
        return locations;
    }

    // Retourne un lieu à partir de son identifiant (ou null s'il n'existe pas)
    public Location get(long id) {
        return repository.findById(id).orElse(null);
    }

    // Ajoute un nouveau lieu
    public void add(Location location) {
        repository.save(location);
    }

    // Modifie un lieu existant
    public void update(long id, Location location) {
        repository.save(location);
    }

    // Supprime un lieu à partir de son identifiant
    public void delete(long id) {
        repository.deleteById(id);
    }
}