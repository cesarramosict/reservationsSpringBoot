package be.iccbxl.pid.reservationsspringboot.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import be.iccbxl.pid.reservationsspringboot.model.Location;
import be.iccbxl.pid.reservationsspringboot.model.Representation;
import be.iccbxl.pid.reservationsspringboot.model.Show;

/**
 * Accès aux données de la table representations.
 *
 * @author Julio Ramos do Carmo
 */
public interface RepresentationRepository extends CrudRepository<Representation, Long> {

    // Représentations d'un spectacle donné
    List<Representation> findByShow(Show show);

    // Représentations données dans un lieu donné
    List<Representation> findByLocation(Location location);

    // Représentations à une date et heure données
    List<Representation> findByWhen(LocalDateTime when);
}