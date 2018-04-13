package fr.alexisnadaud.upgradeit.Managers;

/**
 * Created by Alexis on 22/03/2018.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.alexisnadaud.upgradeit.Classes.Course;
import fr.alexisnadaud.upgradeit.Classes.Participe;
import fr.alexisnadaud.upgradeit.SQLite.MaBaseSQLite;

public class ParticipeBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "upgradeit.db";

    private static final String TABLE_PARTICIPE = "table_participe";
    private static final String COL_ID_COURSE ="id_course";
    private static final String COL_ID_JOUEUR = "id_joueur";
    private static final String COL_PLACEMENT = "participant_place";
    private static final String COL_TEMPS = "temps_course";

    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public ParticipeBDD(Context context){
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

    public long insertParticipe(Participe participe){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID_COURSE, participe.getIdCourse());
        values.put(COL_ID_JOUEUR, participe.getIdJoueur());
        values.put(COL_PLACEMENT, participe.getParticipant_place());
        values.put(COL_TEMPS, participe.getTemps_course());

        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_PARTICIPE, null, values);
    }

    public int updateParticipe(int idCourse, int idJoueur, Participe participe){
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quelle livre on doit mettre à jour grâce à l'ID

        ContentValues values = new ContentValues();
        values.put(COL_ID_COURSE, participe.getIdCourse());
        values.put(COL_ID_JOUEUR, participe.getIdJoueur());
        values.put(COL_PLACEMENT, participe.getParticipant_place());
        values.put(COL_TEMPS, participe.getTemps_course());

        return bdd.update(TABLE_PARTICIPE, values, COL_ID_COURSE + " = " +idCourse + " AND " + COL_ID_JOUEUR + " = " +idJoueur, null);
    }

    public int removeParticipe(int idCourse, int idJoueur, Participe participe){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_PARTICIPE, COL_ID_COURSE + " = " +idCourse + " AND " + COL_ID_JOUEUR + " = " +idJoueur, null);
    }

    public Participe getParticipeParId(int idCourse, int idJoueur){
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_PARTICIPE, new String[] {COL_ID_COURSE, COL_ID_JOUEUR, COL_PLACEMENT,COL_TEMPS}, COL_ID_COURSE + " = " +idCourse + " AND " + COL_ID_JOUEUR + " = " +idJoueur, null, null, null, null);
        return cursorToParticipe(c);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Participe cursorToParticipe(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();

        Participe participe = new Participe();

        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        participe.setIdCourse(c.getInt(0));
        participe.setIdJoueur(c.getInt(1));
        participe.setParticipant_place(c.getInt(2));
        participe.setTemps_course(c.getString(3));
        //On ferme le cursor
        c.close();

        //On retourne la course
        return participe;
    }
}