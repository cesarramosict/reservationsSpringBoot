package be.iccbxl.pid.reservationsspringboot.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Collaboration d'un artiste dans un type de fonction (ligne de la table artist_type),
 * liée aux spectacles auxquels cette collaboration participe.
 *
 * @author Julio Ramos do Carmo
 */
@Entity
@Table(name="artist_type")
public class ArtistType {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="artist_id", nullable=false)
    private Artist artist;

    @ManyToOne
    @JoinColumn(name="type_id", nullable=false)
    private Type type;

    // Spectacles auxquels participe cette collaboration — côté propriétaire de la relation ManyToMany
    @ManyToMany(targetEntity=Show.class)
    @JoinTable(
            name = "artist_type_show",
            joinColumns = @JoinColumn(name = "artist_type_id"),
            inverseJoinColumns = @JoinColumn(name = "show_id"))
    private List<Show> shows = new ArrayList<>();

    protected ArtistType() { }

    public ArtistType(Artist artist, Type type, List<Show> shows) {
        this.artist = artist;
        this.type = type;
        this.shows = shows;
    }

    public Long getId() {
        return id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Show> getShows() {
        return shows;
    }

    // Ajoute un spectacle à cette collaboration (met à jour les deux côtés)
    public ArtistType addShow(Show show) {
        if (!this.shows.contains(show)) {
            this.shows.add(show);
            show.addArtistType(this);
        }
        return this;
    }

    // Retire un spectacle de cette collaboration (met à jour les deux côtés)
    public ArtistType removeShow(Show show) {
        if (this.shows.contains(show)) {
            this.shows.remove(show);
            show.getArtistTypes().remove(this);
        }
        return this;
    }

    @Override
    public String toString() {
        return "ArtistType [id=" + id + ", artist=" + artist + ", type=" + type
                + ", shows=" + shows.size() + "]";
    }
}