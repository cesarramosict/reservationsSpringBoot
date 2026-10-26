package be.iccbxl.pid.reservationsspringboot.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Localité (code postal + nom de la commune).
 *
 * @author Julio Ramos do Carmo
 */
@Entity
@Table(name="localities")
public class Locality {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String postalCode;

    private String locality;

    // Lieux de spectacle situés dans cette localité (côté inverse de la relation)
    @OneToMany(targetEntity=Location.class, mappedBy="locality")
    private List<Location> locations = new ArrayList<>();

    protected Locality() { }

    public Locality(String postalCode, String locality) {
        this.postalCode = postalCode;
        this.locality = locality;
    }

    public Long getId() {
        return id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public List<Location> getLocations() {
        return locations;
    }

    // Ajoute un lieu à cette localité
    public Locality addLocation(Location location) {
        if (!this.locations.contains(location)) {
            this.locations.add(location);
            location.setLocality(this);
        }
        return this;
    }

    // Retire un lieu de cette localité
    public Locality removeLocation(Location location) {
        if (this.locations.contains(location)) {
            this.locations.remove(location);
            if (location.getLocality() == this) {
                location.setLocality(null);
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return "Locality [id=" + id + ", postalCode=" + postalCode + ", locality=" + locality + "]";
    }
}