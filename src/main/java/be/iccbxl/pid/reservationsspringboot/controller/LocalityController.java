package be.iccbxl.pid.reservationsspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import be.iccbxl.pid.reservationsspringboot.model.Locality;
import be.iccbxl.pid.reservationsspringboot.service.LocalityService;

/**
 * Contrôleur des localités : répond aux adresses /localities.
 *
 * @author Julio Ramos do Carmo
 */
@Controller
public class LocalityController {

    @Autowired
    LocalityService service;

    // Liste de toutes les localités
    @GetMapping("/localities")
    public String index(Model model) {
        List<Locality> localities = service.getAll();

        model.addAttribute("localities", localities);
        model.addAttribute("title", "Liste des localités");

        return "locality/index";
    }

    // Fiche d'une localité
    @GetMapping("/localities/{id}")
    public String show(Model model, @PathVariable("id") long id) {
        Locality locality = service.get(id);

        model.addAttribute("locality", locality);
        model.addAttribute("title", "Fiche d'une localité");

        return "locality/show";
    }
}