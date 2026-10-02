package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

public class Artist {
    @Id
    @GeneratedValue()
    private Long Id;
    private String firstname;
    private String lastname;

    protected Artist() {    }
    public Artist(String firstname, String lastname)
        {
        this.firstname = firstname;
        this.lastname = lastname;
        }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
        return "Artist{" +
                "Id=" + Id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
