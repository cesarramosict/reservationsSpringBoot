package be.iccbxl.pid.reservationsspringboot.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.NoArgsConstructor;

/**
 * Entité représentant un utilisateur (table users).
 *
 * @author Julio Ramos do Carmo
 */
@Data
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    private String firstname;
    private String lastname;

    @Column(unique = true, nullable = false)
    private String email;

    private String langue;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private LocalDateTime created_at = LocalDateTime.now();

    // Rôles de l'utilisateur (table user_role) — côté inverse de la relation ManyToMany
    @ManyToMany(mappedBy = "users")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Role> roles = new ArrayList<>();

    // Ajoute un rôle à l'utilisateur (met à jour les deux côtés)
    public User addRole(Role role) {
        if (!this.roles.contains(role)) {
            this.roles.add(role);
            role.addUser(this);
        }
        return this;
    }

    // Retire un rôle de l'utilisateur (met à jour les deux côtés)
    public User removeRole(Role role) {
        if (this.roles.contains(role)) {
            this.roles.remove(role);
            role.getUsers().remove(this);
        }
        return this;
    }

    @Override
    public String toString() {
        return login + " (" + firstname + " " + lastname + " - " + role + ")";
    }
}
