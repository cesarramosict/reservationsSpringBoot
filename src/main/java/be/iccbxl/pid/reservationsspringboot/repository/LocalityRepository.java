package be.iccbxl.pid.reservationsspringboot.repository;

import org.springframework.data.repository.CrudRepository;

import be.iccbxl.pid.reservationsspringboot.model.Locality;

/**
 * Accès aux données de la table localities.
 *
 * @author Julio Ramos do Carmo
 */
public interface LocalityRepository extends CrudRepository<Locality, Long> {

    // Cherche une localité par son code postal
    Locality findByPostalCode(String postalCode);

    // Cherche une localité par son nom
    Locality findByLocality(String locality);
}