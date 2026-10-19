package be.iccbxl.pid.reservationsspringboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import be.iccbxl.pid.reservationsspringboot.model.Artist;

/**
 * Accès aux données de la table artists.
 *
 * @author Julio Ramos do Carmo
 */
public interface ArtistRepository extends CrudRepository<Artist, Long> {

    // Cherche les artistes par leurs nom de famille
    List<Artist> findByLastname(String lastname);

    // Cherche un artiste par son identifiant
    Artist findById(long id);
}