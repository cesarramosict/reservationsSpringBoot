package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.model.Artist;
import be.iccbxl.pid.reservationsspringboot.repository.ArtistRepository;

/**
 * Logique métier des artistes : fait le lien entre les contrôleurs et le repository.
 *
 * @author Julio Ramos do Carmo
 */
@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    // Retourne la liste de tous les artistes
    public List<Artist> getAllArtists() {
        List<Artist> artists = new ArrayList<>();
        artistRepository.findAll().forEach(artists::add);
        return artists;
    }

    // Retourne un artiste à partir de son identifiant
    public Artist getArtist(long id) {
        return artistRepository.findById(id);
    }

    // Ajoute un nouvel artiste
    public void addArtist(Artist artist) {
        artistRepository.save(artist);
    }

    // Modifie un artiste existant
    public void updateArtist(long id, Artist artist) {
        artistRepository.save(artist);
    }

    // Supprime un artiste à partir de son identifiant
    public void deleteArtist(long id) {
        artistRepository.deleteById(id);
    }
}