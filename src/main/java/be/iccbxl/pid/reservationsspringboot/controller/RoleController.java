package be.iccbxl.pid.reservationsspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import be.iccbxl.pid.reservationsspringboot.model.Role;
import be.iccbxl.pid.reservationsspringboot.service.RoleService;

/**
 * Contrôleur des rôles : répond aux adresses /roles.
 *
 * @author Julio Ramos do Carmo
 */
@Controller
public class RoleController {

    @Autowired
    RoleService service;

    // Liste de tous les rôles
    @GetMapping("/roles")
    public String index(Model model) {
        List<Role> roles = service.getAll();

        model.addAttribute("roles", roles);
        model.addAttribute("title", "Liste des rôles");

        return "role/index";
    }

    // Fiche d'un rôle
    @GetMapping("/roles/{id}")
    public String show(Model model, @PathVariable("id") long id) {
        Role role = service.get(id);

        model.addAttribute("role", role);
        model.addAttribute("title", "Fiche d'un rôle");

        return "role/show";
    }
}