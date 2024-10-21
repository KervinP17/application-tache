package cal335.lab.todolist.modele;

public class Tache {
    private String nom;
    private String description;
    private boolean indicateur;

    public Tache(String nom, String description, boolean indicateur) {
        this.nom = nom;
        this.description = description;
        this.indicateur = indicateur;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }
    public boolean isIndicateur() {
        return indicateur;
    }
}
