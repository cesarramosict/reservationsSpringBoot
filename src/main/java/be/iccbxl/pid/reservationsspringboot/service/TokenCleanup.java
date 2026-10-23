package be.iccbxl.pid.reservationsspringboot.service;

import be.iccbxl.pid.reservationsspringboot.repository.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import jakarta.transaction.Transactional;

/**
 * Tâche programmée qui supprime les jetons de réinitialisation expirés.
 *
 * @author Julio Ramos do Carmo
 */
@Component
@RequiredArgsConstructor
public class TokenCleanup {

    private final PasswordResetTokenRepository tokenRepo;

    @Scheduled(cron = "0 0 * * * *") // toutes les heures
    @Transactional
    public void removeExpiredTokens() {
        tokenRepo.findAll().stream()
                .filter(token -> token.getExpiryDate().isBefore(java.time.LocalDateTime.now()))
                .forEach(token -> tokenRepo.delete(token));
    }
}