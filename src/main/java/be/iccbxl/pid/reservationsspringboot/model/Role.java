package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;

/**
 * Rôle d'un utilisateur (admin, member, affiliate...).
 *
 * @author Julio Ramos do Carmo
 */
@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String role;

    protected Role() { }

    public Role(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role [id=" + id + ", role=" + role + "]";
    }
}