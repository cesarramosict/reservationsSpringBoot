package be.iccbxl.pid.reservationsspringboot.model;

import java.util.ArrayList;
import java.util.List;

import com.github.slugify.Slugify;
import jakarta.persistence.*;

/**
 * Lieu de spectacle, situé dans une localité.
 *
 * @author Julio Ramos do Carmo
 */
@Entity
@Table(name="locations")
public class Location {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String slug;

    private String designation;

    private String address;

    @ManyToOne
    @JoinColumn(name="locality_id", nullable=false)
    private Locality locality;

    private String website;

    private String phone;

    // Spectacles créés dans ce lieu (côté inverse de la relation)
    @OneToMany(targetEntity=Show.class, mappedBy="location")
    private List<Show> shows = new ArrayList<>();

    // Représentations données dans ce lieu (côté inverse de la relation)
    @OneToMany(targetEntity=Representation.class, mappedBy="location")
    private List<Representation> representations = new ArrayList<>();

    protected Location() { }

    public Location(String designation, String address, Locality locality, String website, String phone) {
        Slugify slg = new Slugify();

        this.slug = slg.slugify(designation);
        this.designation = designation;
        this.address = address;
        this.locality = locality;
        this.website = website;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    private void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDesignation() {
        return designation;
    }

    // Change la désignation et recalcule le slug
    public void setDesignation(String designation) {
        this.designation = designation;

        Slugify slg = new Slugify();
        this.setSlug(slg.slugify(designation));
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Locality getLocality() {
        return locality;
    }

    // Déménage le lieu vers une autre localité (met à jour les deux côtés de la relation)
    public void setLocality(Locality locality) {
        if (this.locality == locality) {
            return;
        }
        if (this.locality != null) {
            this.locality.getLocations().remove(this);   // déménager de l'ancienne localité
        }
        this.locality = locality;
        if (locality != null && !locality.getLocations().contains(this)) {
            locality.getLocations().add(this);           // emménager dans la nouvelle localité
        }
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Show> getShows() {
        return shows;
    }

    // Ajoute un spectacle à ce lieu
    public Location addShow(Show show) {
        if (!this.shows.contains(show)) {
            this.shows.add(show);
            show.setLocation(this);
        }
        return this;
    }

    // Retire un spectacle de ce lieu
    public Location removeShow(Show show) {
        if (this.shows.contains(show)) {
            this.shows.remove(show);
            if (show.getLocation() == this) {
                show.setLocation(null);
            }
        }
        return this;
    }

    public List<Representation> getRepresentations() {
        return representations;
    }

    // Ajoute une représentation à ce lieu
    public Location addRepresentation(Representation representation) {
        if (!this.representations.contains(representation)) {
            this.representations.add(representation);
            representation.setLocation(this);
        }
        return this;
    }

    // Retire une représentation de ce lieu
    public Location removeRepresentation(Representation representation) {
        if (this.representations.contains(representation)) {
            this.representations.remove(representation);
            if (representation.getLocation() == this) {
                representation.setLocation(null);
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return "Location [id=" + id + ", slug=" + slug + ", designation=" + designation
                + ", address=" + address + ", locality=" + locality + ", website="
                + website + ", phone=" + phone + ", shows=" + shows.size()
                + ", representations=" + representations.size() + "]";
    }
}