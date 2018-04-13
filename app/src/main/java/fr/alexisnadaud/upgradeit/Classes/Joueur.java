package fr.alexisnadaud.upgradeit.Classes;

public class Joueur {
    private int progressBar;
    private int idJoueur;
    private int idVehicule;
    private String nom;
    private int nbPoints;
    private int nbClics;

    public Joueur(){}

    public Joueur(int idJoueur, int idVehicule, String nom, int nbPoints, int nbClics, int progressBar) {
        this.idJoueur = idJoueur;
        this.idVehicule = idVehicule;
        this.nom = nom;
        this.nbPoints = nbPoints;
        this.nbClics = nbClics;
        this.progressBar = progressBar;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public int getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(int progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "progressBar=" + progressBar +
                ", idJoueur=" + idJoueur +
                ", idVehicule=" + idVehicule +
                ", nom='" + nom + '\'' +
                ", nbPoints=" + nbPoints +
                ", nbClics=" + nbClics +
                '}';
    }

    public int getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    public int getNbClics() {
        return nbClics;
    }

    public void setNbClics(int nbClics) {
        this.nbClics = nbClics;
    }
}