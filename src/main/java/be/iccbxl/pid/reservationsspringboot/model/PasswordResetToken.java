package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Jeton temporaire de réinitialisation de mot de passe (table personal_access_tokens).
 *
 * @author Julio Ramos do Carmo
 */
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name="personal_access_tokens")
public class PasswordResetToken {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String token;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "expires_at")
    private LocalDateTime expiryDate;

    // Vrai si la date d'expiration est déjà passée
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDateTime.now());
    }
}