package be.iccbxl.pid.reservationsspringboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Contrôleur de la page d'accueil.
 * Répond aux requêtes envoyées à la racine du site.
 *
 * @author Julio Ramos do Carmo
 */
@Controller
public class HomeController {

    /**
     * Affiche la page d'accueil (template home.html).
     * @return le nom du template à afficher
     */
    @RequestMapping("/")
    public String index() {
        return "home";
    }
}