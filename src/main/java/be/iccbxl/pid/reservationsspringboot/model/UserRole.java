package be.iccbxl.pid.reservationsspringboot.model;

/**
 * Rôles possibles d'un utilisateur de l'application.
 *
 * @author Julio Ramos do Carmo
 */
public enum UserRole {
    ADMIN("Administrateur"),
    MEMBER("Membre"),
    AFFILIATE("Affilié"),
    PRESS("Critique de presse"),
    PRODUCER("Producteur");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    // Retourne le libellé lisible du rôle (ex. « Administrateur »)
    public String getValue() {
        return role;
    }
}