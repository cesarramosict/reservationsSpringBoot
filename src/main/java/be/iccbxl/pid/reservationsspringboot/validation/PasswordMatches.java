package be.iccbxl.pid.reservationsspringboot.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation qui vérifie que le mot de passe et sa confirmation sont identiques.
 *
 * @author Julio Ramos do Carmo
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {

    String message() default "Les mots de passe ne correspondent pas";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}