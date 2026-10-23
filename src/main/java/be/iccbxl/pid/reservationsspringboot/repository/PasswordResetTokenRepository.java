package be.iccbxl.pid.reservationsspringboot.repository;

import be.iccbxl.pid.reservationsspringboot.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Accès aux données de la table personal_access_tokens.
 *
 * @author Julio Ramos do Carmo
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    // Cherche un jeton par sa valeur
    Optional<PasswordResetToken> findByToken(String token);

    // Supprime un jeton par sa valeur
    void deleteByToken(String token);
}