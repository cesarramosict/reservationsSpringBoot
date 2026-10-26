package be.iccbxl.pid.reservationsspringboot.model;

import java.time.LocalDateTime;

import com.github.slugify.Slugify;
import jakarta.persistence.*;

/**
 * Spectacle, créé dans un lieu de spectacle.
 *
 * @author Julio Ramos do Carmo
 */
@Entity
@Table(name="shows")
public class Show {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String slug;

    private String title;

    private String description;

    @Column(name="poster_url")
    private String posterUrl;

    /**
     * Lieu de création du spectacle
     */
    @ManyToOne
    @JoinColumn(name="location_id", nullable=true)
    private Location location;

    private boolean bookable;

    private double price;

    /**
     * Date de création du spectacle
     */
    @Column(name="created_at")
    private LocalDateTime createdAt;

    /**
     * Date de modification du spectacle
     */
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    public Show() { }

    public Show(String title, String description, String posterUrl, Location location, boolean bookable, double price) {
        Slugify slg = new Slugify();

        this.slug = slg.slugify(title);
        this.title = title;
        this.description = description;
        this.posterUrl = posterUrl;
        this.location = location;
        this.bookable = bookable;
        this.price = price;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
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

    public String getTitle() {
        return title;
    }

    // Change le titre et recalcule le slug
    public void setTitle(String title) {
        this.title = title;

        Slugify slg = new Slugify();
        this.setSlug(slg.slugify(title));
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public Location getLocation() {
        return location;
    }

    // Déplace le spectacle vers un autre lieu (met à jour les deux côtés de la relation)
    public void setLocation(Location location) {
        if (this.location == location) {
            return;
        }
        if (this.location != null) {
            this.location.getShows().remove(this);   // déménager de l'ancien lieu
        }
        this.location = location;
        if (location != null && !location.getShows().contains(this)) {
            location.getShows().add(this);           // emménager dans le nouveau lieu
        }
    }

    public boolean isBookable() {
        return bookable;
    }

    public void setBookable(boolean bookable) {
        this.bookable = bookable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Show [id=" + id + ", slug=" + slug + ", title=" + title
                + ", description=" + description + ", posterUrl=" + posterUrl + ", location="
                + location + ", bookable=" + bookable + ", price=" + price
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }
}