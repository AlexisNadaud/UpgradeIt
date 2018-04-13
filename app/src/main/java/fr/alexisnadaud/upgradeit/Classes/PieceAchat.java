package fr.alexisnadaud.upgradeit.Classes;

public class PieceAchat {
    private int idPiece;
    private int prix;
    private String type;
    private int niveau;
    private String nom;

    public PieceAchat(int idPiece, int prix, String type, int niveau, String nom) {
        this.idPiece = idPiece;
        this.prix = prix;
        this.type = type;
        this.niveau = niveau;
        this.nom = nom;
    }

    public int getIdPiece() {
        return idPiece;
    }

    public void setIdPiece(int idPiece) {
        this.idPiece = idPiece;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}