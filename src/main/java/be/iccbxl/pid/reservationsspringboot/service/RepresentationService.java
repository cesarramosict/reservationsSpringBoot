package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.model.Location;
import be.iccbxl.pid.reservationsspringboot.model.Representation;
import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.repository.RepresentationRepository;

/**
 * Logique métier des représentations.
 *
 * @author Julio Ramos do Carmo
 */
@Service
public class RepresentationService {

    @Autowired
    private RepresentationRepository repository;

    // Retourne la liste de toutes les représentations
    public List<Representation> getAll() {
        List<Representation> representations = new ArrayList<>();
        repository.findAll().forEach(representations::add);
        return representations;
    }

    // Retourne une représentation à partir de son identifiant (ou null si elle n'existe pas)
    public Representation get(long id) {
        return repository.findById(id).orElse(null);
    }

    // Ajoute une nouvelle représentation
    public void add(Representation representation) {
        repository.save(representation);
    }

    // Modifie une représentation existante
    public void update(long id, Representation representation) {
        repository.save(representation);
    }

    // Supprime une représentation à partir de son identifiant
    public void delete(long id) {
        repository.deleteById(id);
    }

    // Représentations données dans un lieu donné
    public List<Representation> getFromLocation(Location location) {
        return repository.findByLocation(location);
    }

    // Représentations d'un spectacle donné
    public List<Representation> getFromShow(Show show) {
        return repository.findByShow(show);
    }
}