package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.model.Locality;
import be.iccbxl.pid.reservationsspringboot.repository.LocalityRepository;

/**
 * Logique métier des localités.
 *
 * @author Julio Ramos do Carmo
 */
@Service
public class LocalityService {

    @Autowired
    private LocalityRepository repository;

    // Retourne la liste de toutes les localités
    public List<Locality> getAll() {
        List<Locality> localities = new ArrayList<>();
        repository.findAll().forEach(localities::add);
        return localities;
    }

    // Retourne une localité à partir de son identifiant (ou null si elle n'existe pas)
    public Locality get(long id) {
        return repository.findById(id).orElse(null);
    }

    // Ajoute une nouvelle localité
    public void add(Locality locality) {
        repository.save(locality);
    }

    // Modifie une localité existante
    public void update(long id, Locality locality) {
        repository.save(locality);
    }

    // Supprime une localité à partir de son identifiant
    public void delete(long id) {
        repository.deleteById(id);
    }
}
