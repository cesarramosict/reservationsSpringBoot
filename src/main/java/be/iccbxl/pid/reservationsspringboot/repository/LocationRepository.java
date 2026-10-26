package be.iccbxl.pid.reservationsspringboot.repository;

import org.springframework.data.repository.CrudRepository;

import be.iccbxl.pid.reservationsspringboot.model.Location;

/**
 * Accès aux données de la table locations.
 *
 * @author Julio Ramos do Carmo
 */
public interface LocationRepository extends CrudRepository<Location, Long> {

    // Cherche un lieu par sa désignation
    Location findByDesignation(String designation);
}