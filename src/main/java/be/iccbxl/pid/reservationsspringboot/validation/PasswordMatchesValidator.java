package be.iccbxl.pid.reservationsspringboot.validation;

import be.iccbxl.pid.reservationsspringboot.dto.UserProfileDto;
import be.iccbxl.pid.reservationsspringboot.dto.UserRegistrationDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Logique de validation de l'annotation @PasswordMatches :
 * compare le mot de passe et sa confirmation.
 * Fonctionne pour le formulaire d'inscription et pour le formulaire de profil.
 *
 * @author Julio Ramos do Carmo
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        String pwd;
        String confirm;

        // On récupère les deux mots de passe selon le type de formulaire
        if (obj instanceof UserRegistrationDto dto) {
            pwd = dto.getPassword();
            confirm = dto.getConfirmPassword();
        } else if (obj instanceof UserProfileDto dto) {
            pwd = dto.getPassword();
            confirm = dto.getConfirmPassword();
        } else {
            return false;
        }

        // Si les deux champs sont vides, on considère que le mot de passe n'est pas modifié => OK
        if ((pwd == null || pwd.isBlank()) && (confirm == null || confirm.isBlank())) {
            return true;
        }

        // Sinon, il faut qu'ils soient identiques
        if (pwd == null) return false;
        return pwd.equals(confirm);
    }
}