package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Type d'artiste (auteur, scénographe, comédien...).
 *
 * @author Julio Ramos do Carmo
 */
@Entity
@Table(name="types")
public class Type {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String type;

    // Artistes qui exercent ce type — côté propriétaire de la relation ManyToMany
    @ManyToMany
    @JoinTable(
            name = "artist_type",
            joinColumns = @JoinColumn(name = "type_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private List<Artist> artists = new ArrayList<>();

    protected Type() { }

    public Type(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    // Ajoute un artiste à ce type (met à jour les deux côtés de la relation)
    public Type addArtist(Artist artist) {
        if (!this.artists.contains(artist)) {
            this.artists.add(artist);
            artist.addType(this);
        }
        return this;
    }

    // Retire un artiste de ce type (met à jour les deux côtés de la relation)
    public Type removeArtist(Artist artist) {
        if (this.artists.contains(artist)) {
            this.artists.remove(artist);
            artist.getTypes().remove(this);
        }
        return this;
    }

    @Override
    public String toString() {
        return "Type [id=" + id + ", type=" + type + "]";
    }
}