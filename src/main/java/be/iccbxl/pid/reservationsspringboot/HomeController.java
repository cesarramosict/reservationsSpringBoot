package be.iccbxl.pid.reservationsspringboot;

/**
 * Contrôleur de la page réception (accueil).
 * Répond aux requetes envoyées a la racine du site.
 *
 * @author Julio Ramos do Carmo
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {

    /**
     * Affiche un message de bienvenue à l'adresse ( / )
     * @return le texte affiché dans le navigateur
     */
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
