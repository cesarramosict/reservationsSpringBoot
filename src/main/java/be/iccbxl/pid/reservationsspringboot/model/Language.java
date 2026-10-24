package be.iccbxl.pid.reservationsspringboot.model;

/**
 * Langues disponibles : fait le lien entre le code (fr, nl, en) et son libellé.
 *
 * @author Julio Ramos do Carmo
 */
public enum Language {
    EN("Anglais"),
    FR("Français"),
    NL("Néerlandais");

    private String description;

    private Language(String description) {
        this.description = description;
    }

    // Retourne le nom de la langue en toutes lettres
    public String getDescription() {
        return this.description;
    }
}
