package be.iccbxl.pid.reservationsspringboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import be.iccbxl.pid.reservationsspringboot.model.Location;
import be.iccbxl.pid.reservationsspringboot.model.Show;

/**
 * Accès aux données de la table shows.
 *
 * @author Julio Ramos do Carmo
 */
public interface ShowRepository extends CrudRepository<Show, Long> {

    // Cherche un spectacle par son slug
    Show findBySlug(String slug);

    // Cherche un spectacle par son titre
    Show findByTitle(String title);

    // Liste des spectacles créés dans un lieu donné
    List<Show> findByLocation(Location location);
}