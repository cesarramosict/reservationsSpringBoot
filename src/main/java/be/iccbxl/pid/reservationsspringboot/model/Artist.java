package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;

/**
 * Classe artist
 * @author Julio Ramos do Carmo
 */

@Entity
@Table (name="artists")



public class Artist {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;

    protected Artist() {    }
    public Artist(String firstname, String lastname)
        {
        this.firstname = firstname;
        this.lastname = lastname;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}
