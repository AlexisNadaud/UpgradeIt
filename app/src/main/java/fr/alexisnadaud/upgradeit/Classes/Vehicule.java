package fr.alexisnadaud.upgradeit.Classes;

public class Vehicule {

    private int id_vehicule;
    private int niveau_vehicule;
    private String nom_vehicule;
    private int niveau_roues;
    private int niveau_carrosserie;
    private int niveau_moteur;
    private int niveau_boite;
    private int niveau_freins;

    public Vehicule() {

    }
    public Vehicule(int id_vehicule, String nom_vehicule) {
        this.nom_vehicule = nom_vehicule;
        this.id_vehicule = id_vehicule;
    }

    public Vehicule(int niveau_vehicule, String nom_vehicule, int niveau_roues, int niveau_carrosserie, int niveau_moteur, int niveau_boite, int niveau_freins) {
        this.niveau_vehicule = niveau_vehicule;
        this.nom_vehicule = nom_vehicule;
        this.niveau_roues = niveau_roues;
        this.niveau_carrosserie = niveau_carrosserie;
        this.niveau_moteur = niveau_moteur;
        this.niveau_boite = niveau_boite;
        this.niveau_freins = niveau_freins;
    }
    public Vehicule(int id, int niveau_vehicule, String nom_vehicule, int niveau_roues, int niveau_carrosserie, int niveau_moteur, int niveau_boite, int niveau_freins) {
        this.id_vehicule=id;
        this.niveau_vehicule = niveau_vehicule;
        this.nom_vehicule = nom_vehicule;
        this.niveau_roues = niveau_roues;
        this.niveau_carrosserie = niveau_carrosserie;
        this.niveau_moteur = niveau_moteur;
        this.niveau_boite = niveau_boite;
        this.niveau_freins = niveau_freins;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "id_vehicule=" + id_vehicule +
                ", niveau_vehicule=" + niveau_vehicule +
                ", nom_vehicule='" + nom_vehicule + '\'' +
                ", niveau_roues=" + niveau_roues +
                ", niveau_carrosserie=" + niveau_carrosserie +
                ", niveau_moteur=" + niveau_moteur +
                ", niveau_boite=" + niveau_boite +
                ", niveau_freins=" + niveau_freins +
                '}';
    }

    public int getId_vehicule() {
        return id_vehicule;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public int getNiveau_vehicule() {
        return niveau_vehicule;
    }

    public void setNiveau_vehicule(int niveau_vehicule) {
        this.niveau_vehicule = niveau_vehicule;
    }

    public String getNom_vehicule() {
        return nom_vehicule;
    }

    public void setNom_vehicule(String nom_vehicule) {
        this.nom_vehicule = nom_vehicule;
    }

    public int getNiveau_roues() {
        return niveau_roues;
    }

    public void setNiveau_roues(int niveau_roues) {
        this.niveau_roues = niveau_roues;
    }

    public int getNiveau_carrosserie() {
        return niveau_carrosserie;
    }

    public void setNiveau_carrosserie(int niveau_carrosserie) {
        this.niveau_carrosserie = niveau_carrosserie;
    }

    public int getNiveau_moteur() {
        return niveau_moteur;
    }

    public void setNiveau_moteur(int niveau_moteur) {
        this.niveau_moteur = niveau_moteur;
    }

    public int getNiveau_boite() {
        return niveau_boite;
    }

    public void setNiveau_boite(int niveau_boite) {
        this.niveau_boite = niveau_boite;
    }

    public int getNiveau_freins() {
        return niveau_freins;
    }

    public void setNiveau_freins(int niveau_freins) {
        this.niveau_freins = niveau_freins;
    }

}