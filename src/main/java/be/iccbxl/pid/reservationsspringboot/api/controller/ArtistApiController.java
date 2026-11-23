package be.iccbxl.pid.reservationsspringboot.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.iccbxl.pid.reservationsspringboot.model.Artist;
import be.iccbxl.pid.reservationsspringboot.api.hateoas.ArtistModelAssembler;
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
    private final ArtistModelAssembler assembler;

    public ArtistApiController(ArtistRepository repository, ArtistModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // GET : liste de tous les artistes, chacun avec ses liens + un lien vers la liste
    @GetMapping("/artists")
    public CollectionModel<EntityModel<Artist>> all() {
        List<EntityModel<Artist>> artists = ((List<Artist>) repository.findAll()).stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(artists,
                linkTo(methodOn(ArtistApiController.class).all()).withSelfRel());
    }

    // GET : un seul artiste avec ses liens
    @GetMapping("/artists/{id}")
    public EntityModel<Artist> one(@PathVariable Long id) {
        Artist artist = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));
        return assembler.toModel(artist);
    }
    // POST : ajouter un artiste (réservé à l'administrateur) -> 201 Created + liens
    @PostMapping("/admin/artists")
    public ResponseEntity<?> newArtist(@RequestBody Artist newArtist) {
        Artist savedArtist = repository.save(newArtist);

        return ResponseEntity
                .created(linkTo(methodOn(ArtistApiController.class).one(savedArtist.getId())).toUri())
                .body(assembler.toModel(savedArtist));
    }

    // PUT : modifier un artiste (réservé à l'administrateur) -> 201 + liens
    @PutMapping("/admin/artists/{id}")
    public ResponseEntity<?> replaceArtist(@RequestBody Artist newArtist, @PathVariable Long id) {
        Artist updatedArtist = repository.findById(id)
                .map(artist -> {
                    artist.setFirstname(newArtist.getFirstname());
                    artist.setLastname(newArtist.getLastname());
                    return repository.save(artist);
                })
                .orElseGet(() -> {
                    newArtist.setId(id);
                    return repository.save(newArtist);
                });

        return ResponseEntity
                .created(linkTo(methodOn(ArtistApiController.class).one(updatedArtist.getId())).toUri())
                .body(assembler.toModel(updatedArtist));
    }

    // DELETE : supprimer un artiste (réservé à l'administrateur) -> 204 No Content
    @DeleteMapping("/admin/artists/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}