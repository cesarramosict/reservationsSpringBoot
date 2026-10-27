package be.iccbxl.pid.reservationsspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.service.ShowService;

/**
 * Contrôleur des spectacles : répond aux adresses /shows.
 *
 * @author Julio Ramos do Carmo
 */
@Controller
public class ShowController {

    @Autowired
    ShowService service;

    // Liste de tous les spectacles
    @GetMapping("/shows")
    public String index(Model model) {
        List<Show> shows = service.getAll();

        model.addAttribute("shows", shows);
        model.addAttribute("title", "Liste des spectacles");

        return "show/index";
    }

    // Fiche d'un spectacle
    @GetMapping("/shows/{id}")
    public String show(Model model, @PathVariable("id") long id) {
        Show show = service.get(id);

        model.addAttribute("show", show);
        model.addAttribute("title", "Fiche d'un spectacle");

        return "show/show";
    }
}