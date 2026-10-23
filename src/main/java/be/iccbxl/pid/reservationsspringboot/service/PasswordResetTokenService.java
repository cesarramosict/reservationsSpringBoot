package be.iccbxl.pid.reservationsspringboot.service;

import be.iccbxl.pid.reservationsspringboot.model.PasswordResetToken;
import be.iccbxl.pid.reservationsspringboot.model.User;
import be.iccbxl.pid.reservationsspringboot.repository.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Gestion des jetons de réinitialisation de mot de passe : création, validation et suppression.
 *
 * @author Julio Ramos do Carmo
 */
@Service
@RequiredArgsConstructor
public class PasswordResetTokenService {

    private final PasswordResetTokenRepository tokenRepo;

    // Crée un jeton unique pour l'utilisateur, valable 1 heure
    public PasswordResetToken createTokenForUser(User user) {
        String token = UUID.randomUUID().toString();

        PasswordResetToken prt = new PasswordResetToken();
        prt.setToken(token);
        prt.setUser(user);
        prt.setExpiryDate(LocalDateTime.now().plusHours(1));

        return tokenRepo.save(prt);
    }

    // Retourne l'utilisateur lié au jeton, ou null si le jeton n'existe pas ou est expiré
    public User validatePasswordResetToken(String token) {
        PasswordResetToken prt = tokenRepo.findByToken(token).orElse(null);

        if (prt == null || prt.isExpired()) {
            return null;
        }

        return prt.getUser();
    }

    // Supprime le jeton (une suppression doit se faire dans une transaction)
    @Transactional
    public void deleteToken(String token) {
        tokenRepo.deleteByToken(token);
    }
}