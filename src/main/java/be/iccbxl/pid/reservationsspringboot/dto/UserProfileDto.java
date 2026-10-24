package be.iccbxl.pid.reservationsspringboot.dto;

import be.iccbxl.pid.reservationsspringboot.validation.PasswordMatches;
import be.iccbxl.pid.reservationsspringboot.validation.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Objet de transfert pour l'affichage et la modification du profil.
 * Le mot de passe est optionnel ; le login et le rôle ne sont pas modifiables.
 *
 * @author Julio Ramos do Carmo
 */
@Data
@PasswordMatches(message = "Les mots de passe ne correspondent pas")
public class UserProfileDto {

    private Long id;

    @NotBlank(message = "Le prénom est obligatoire")
    private String firstname;

    @NotBlank(message = "Le nom est obligatoire")
    private String lastname;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;

    @NotBlank(message = "La langue est obligatoire")
    private String langue;

    private String login; // visible mais non modifiable

    private String role;  // visible mais non modifiable

    @StrongPassword
    private String password; // nouveau mot de passe (optionnel)

    private String confirmPassword; // confirmation du mot de passe
}