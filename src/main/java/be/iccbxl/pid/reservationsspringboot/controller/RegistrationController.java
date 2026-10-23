package be.iccbxl.pid.reservationsspringboot.controller;

import be.iccbxl.pid.reservationsspringboot.dto.UserRegistrationDto;
import be.iccbxl.pid.reservationsspringboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Contrôleur de l'inscription d'un nouveau membre.
 *
 * @author Julio Ramos do Carmo
 */
@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    // Affiche le formulaire d'inscription vide
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "authentication/register";
    }

    // Traite le formulaire d'inscription
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserRegistrationDto dto,
                               BindingResult result, Model model, RedirectAttributes redirAttrs) {

        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Erreurs de validation !");
            return "authentication/register";
        }

        // Vérification de doublons
        if (!userService.isLoginAndEmailAvailable(dto.getLogin(), dto.getEmail())) {
            model.addAttribute("errorMessage", "Email ou login déjà utilisé !");
            return "authentication/register";
        }

        userService.registerFromDto(dto);
        redirAttrs.addFlashAttribute("successMessage", "Inscription réussie !");
        return "redirect:login";
    }
}