package be.iccbxl.pid.reservationsspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import be.iccbxl.pid.reservationsspringboot.model.Artist;
import be.iccbxl.pid.reservationsspringboot.service.ArtistService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Contrôleur des artistes : répond aux adresses /artists.
 *
 * @author Julio Ramos do Carmo
 */
@Controller
public class ArtistController {

    @Autowired
    ArtistService service;

    // Prépare la liste des artistes et l'envoie au template artist/index.html
    @GetMapping("/artists")
    public String index(Model model) {
        List<Artist> artists = service.getAllArtists();

        model.addAttribute("artists", artists);
        model.addAttribute("title", "Liste des artistes");

        return "artist/index";
    }

    // Récupère un artiste grâce à l'id présent dans l'adresse et l'envoie au template artist/show.html
    @GetMapping("/artists/{id}")
    public String show(Model model, @PathVariable("id") long id) {
        Artist artist = service.getArtist(id);

        model.addAttribute("artist", artist);
        model.addAttribute("title", "Fiche d'un artiste");

        return "artist/show";
    }
}