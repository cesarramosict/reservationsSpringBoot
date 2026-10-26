package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.model.Location;
import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.repository.ShowRepository;

/**
 * Logique métier des spectacles.
 *
 * @author Julio Ramos do Carmo
 */
@Service
public class ShowService {

    @Autowired
    private ShowRepository repository;

    // Retourne la liste de tous les spectacles
    public List<Show> getAll() {
        List<Show> shows = new ArrayList<>();
        repository.findAll().forEach(shows::add);
        return shows;
    }

    // Retourne un spectacle à partir de son identifiant (ou null s'il n'existe pas)
    public Show get(long id) {
        return repository.findById(id).orElse(null);
    }

    // Ajoute un nouveau spectacle
    public void add(Show show) {
        repository.save(show);
    }

    // Modifie un spectacle existant
    public void update(long id, Show show) {
        repository.save(show);
    }

    // Supprime un spectacle à partir de son identifiant
    public void delete(long id) {
        repository.deleteById(id);
    }

    // Retourne les spectacles créés dans un lieu donné
    public List<Show> getFromLocation(Location location) {
        return repository.findByLocation(location);
    }
}