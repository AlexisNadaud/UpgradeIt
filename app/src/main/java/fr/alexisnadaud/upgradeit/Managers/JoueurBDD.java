package fr.alexisnadaud.upgradeit.Managers;

/**
 * Created by Alexis on 22/03/2018.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.alexisnadaud.upgradeit.Classes.Joueur;
import fr.alexisnadaud.upgradeit.Classes.Vehicule;
import fr.alexisnadaud.upgradeit.SQLite.MaBaseSQLite;

public class JoueurBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "upgradeit.db";

    private static final String TABLE_JOUEUR = "table_joueur";
    private static final String COL_ID_JOUEUR ="id_joueur";
    private static final String COL_ID_VEHICULE = "id_vehicule";
    private static final String COL_NOM_JOUEUR = "nom_joueur";
    private static final String COL_NB_POINTS = "nombre_points";
    private static final String COL_NB_CLICS = "nombre_clics";
    private static final String COL_PROGRESS_BAR = "progress_bar";

    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public JoueurBDD(Context context){
        //On créer la BDD et sa table
        maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertJoueur(Joueur joueur){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID_JOUEUR, joueur.getIdJoueur());
        values.put(COL_ID_VEHICULE, joueur.getIdVehicule());
        values.put(COL_NOM_JOUEUR, joueur.getNom());
        values.put(COL_NB_POINTS, joueur.getNbPoints());
        values.put(COL_NB_CLICS, joueur.getNbClics());
        values.put(COL_PROGRESS_BAR, joueur.getProgressBar());

        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_JOUEUR, null, values);
    }

    public int updateJoueur(int id, Joueur joueur){
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quelle livre on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_ID_JOUEUR, joueur.getIdJoueur());
        values.put(COL_ID_VEHICULE, joueur.getIdVehicule());
        values.put(COL_NOM_JOUEUR, joueur.getNom());
        values.put(COL_NB_POINTS, joueur.getNbPoints());
        values.put(COL_NB_CLICS, joueur.getNbClics());
        values.put(COL_PROGRESS_BAR, joueur.getProgressBar());
        return bdd.update(TABLE_JOUEUR, values, COL_ID_JOUEUR + " = " +id, null);
    }

    public int removeJoueur(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_JOUEUR, COL_ID_JOUEUR + " = " +id, null);
    }

    public Joueur getJoueurParId(int id){
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_JOUEUR, new String[] {COL_ID_JOUEUR, COL_ID_VEHICULE, COL_NOM_JOUEUR, COL_NB_POINTS, COL_NB_CLICS, COL_PROGRESS_BAR}, COL_ID_JOUEUR + " = " + id +" ", null, null, null, null);
        return cursorToJoueur(c);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Joueur cursorToJoueur(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();

        Joueur joueur = new Joueur();

        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        joueur.setIdJoueur(c.getInt(0));
        joueur.setIdVehicule(c.getInt(1));
        joueur.setNom(c.getString(2));
        joueur.setNbPoints(c.getInt(3));
        joueur.setNbClics(c.getInt(4));
        joueur.setProgressBar(c.getInt(5));

        //On ferme le cursor
        c.close();

        //On retourne le livre
        return joueur;
    }
}