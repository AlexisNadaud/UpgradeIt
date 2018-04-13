package fr.alexisnadaud.upgradeit.Classes;

public class Niveau {
    private int idNiveau;
    private int nbPointsGagnes;
    private int pointsNecessaires;

    public Niveau(int idNiveau, int nbPointsGagnes, int pointsNecessaires) {
        this.idNiveau = idNiveau;
        this.nbPointsGagnes = nbPointsGagnes;
        this.pointsNecessaires = pointsNecessaires;
    }

    public int getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(int idNiveau) {
        this.idNiveau = idNiveau;
    }

    public int getNbPointsGagnes() {
        return nbPointsGagnes;
    }

    public void setNbPointsGagnes(int nbPointsGagnes) {
        this.nbPointsGagnes = nbPointsGagnes;
    }

    public int getPointsNecessaires() {
        return pointsNecessaires;
    }

    public void setPointsNecessaires(int pointsNecessaires) {
        this.pointsNecessaires = pointsNecessaires;
    }
}