package be.iccbxl.pid.reservationsspringboot.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.iccbxl.pid.reservationsspringboot.model.Artist;
import be.iccbxl.pid.reservationsspringboot.repository.ArtistRepository;

/**
 * API REST des artistes : renvoie les données au format JSON (et non une page HTML).
 *
 * @author Julio Ramos do Carmo
 */
@RestController
@RequestMapping("/api")
public class ArtistApiController {

    private final ArtistRepository repository;

    public ArtistApiController(ArtistRepository repository) {
        this.repository = repository;
    }

    // GET : liste de tous les artistes
    @GetMapping("/artists")
    public List<Artist> all() {
        return (List<Artist>) repository.findAll();
    }

    // GET : un seul artiste
    @GetMapping("/artists/{id}")
    public Artist one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));
    }

    // POST : ajouter un artiste (réservé à l'administrateur)
    @PostMapping("/admin/artists")
    public Artist newArtist(@RequestBody Artist newArtist) {
        return repository.save(newArtist);
    }

    // PUT : modifier un artiste (réservé à l'administrateur)
    @PutMapping("/admin/artists/{id}")
    public Artist replaceArtist(@RequestBody Artist newArtist, @PathVariable Long id) {
        return repository.findById(id)
                .map(artist -> {
                    artist.setFirstname(newArtist.getFirstname());
                    artist.setLastname(newArtist.getLastname());
                    return repository.save(artist);
                })
                .orElseGet(() -> {
                    newArtist.setId(id);
                    return repository.save(newArtist);
                });
    }

    // DELETE : supprimer un artiste (réservé à l'administrateur)
    @DeleteMapping("/admin/artists/{id}")
    public void deleteArtist(@PathVariable Long id) {
        repository.deleteById(id);
    }
}