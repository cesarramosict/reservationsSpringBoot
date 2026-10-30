package be.iccbxl.pid.reservationsspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import be.iccbxl.pid.reservationsspringboot.model.Representation;
import be.iccbxl.pid.reservationsspringboot.service.RepresentationService;

/**
 * Contrôleur des représentations : répond aux adresses /representations.
 *
 * @author Julio Ramos do Carmo
 */
@Controller
public class RepresentationController {

    @Autowired
    RepresentationService service;

    // Liste de toutes les représentations
    @GetMapping("/representations")
    public String index(Model model) {
        List<Representation> representations = service.getAll();

        model.addAttribute("representations", representations);
        model.addAttribute("title", "Liste des représentations");

        return "representation/index";
    }

    // Fiche d'une représentation (date et heure séparées pour simplifier le template)
    @GetMapping("/representations/{id}")
    public String show(Model model, @PathVariable("id") long id) {
        Representation representation = service.get(id);

        model.addAttribute("representation", representation);
        model.addAttribute("date", representation.getWhen().toLocalDate());
        model.addAttribute("heure", representation.getWhen().toLocalTime());
        model.addAttribute("title", "Fiche d'une représentation");

        return "representation/show";
    }
}
