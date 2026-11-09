package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    // Utilisateurs qui ont ce rôle — côté propriétaire de la relation ManyToMany
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();

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
    public List<User> getUsers() {
        return users;
    }

    // Ajoute un utilisateur à ce rôle (met à jour les deux côtés)
    public Role addUser(User user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
            user.addRole(this);
        }
        return this;
    }

    // Retire un utilisateur de ce rôle (met à jour les deux côtés)
    public Role removeUser(User user) {
        if (this.users.contains(user)) {
            this.users.remove(user);
            user.getRoles().remove(this);
        }
        return this;
    }

    @Override
    public String toString() {
        return "Role [id=" + id + ", role=" + role + "]";
    }
}