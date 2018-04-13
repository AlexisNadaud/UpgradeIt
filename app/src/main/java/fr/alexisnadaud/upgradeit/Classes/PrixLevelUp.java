package fr.alexisnadaud.upgradeit.Classes;

/**
 * Created by Alexis on 22/03/2018.
 */

public class PrixLevelUp {
    private int level;
    private int prix;
    private int points;

    public PrixLevelUp(){}

    public PrixLevelUp(int level) {
        this.level = level;
    }

    public PrixLevelUp(int level, int prix) {
        this.level = level;
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "PrixLevelUp{" +
                "level=" + level +
                ", points=" + points +
                ", prix=" + prix +
                '}';
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
