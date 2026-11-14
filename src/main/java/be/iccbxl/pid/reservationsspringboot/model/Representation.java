package be.iccbxl.pid.reservationsspringboot.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Représentation (séance) d'un spectacle à une date donnée.
 *
 * @author Julio Ramos do Carmo
 */
@Entity
@Table(name="representations")
public class Representation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="show_id", nullable=false)
    private Show show;

    /**
     * Date et heure de la représentation
     * (« when » est un mot réservé en SQL : on l'entoure d'accents graves)
     */
    @Column(name="`when`")
    private LocalDateTime when;

    /**
     * Lieu de prestation de la représentation
     */
    @ManyToOne
    @JoinColumn(name="location_id", nullable=true)
    private Location location;

    // Utilisateurs qui ont réservé cette représentation (table reservations) — côté propriétaire
    @ManyToMany
    @JoinTable(
            name = "reservations",
            joinColumns = @JoinColumn(name = "representation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();

    public Representation() { }

    public Representation(Show show, LocalDateTime when, Location location) {
        this.show = show;
        this.when = when;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    public List<User> getUsers() {
        return users;
    }

    // Ajoute un utilisateur qui réserve cette représentation (met à jour les deux côtés)
    public Representation addUser(User user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
            user.addRepresentation(this);
        }
        return this;
    }

    // Retire un utilisateur de cette représentation (met à jour les deux côtés)
    public Representation removeUser(User user) {
        if (this.users.contains(user)) {
            this.users.remove(user);
            user.getRepresentations().remove(this);
        }
        return this;
    }

    @Override
    public String toString() {
        return "Representation [id=" + id + ", show=" + show + ", when=" + when
                + ", location=" + location + "]";
    }
}