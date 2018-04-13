package fr.alexisnadaud.upgradeit.Managers;

/**
 * Created by Alexis on 22/03/2018.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.alexisnadaud.upgradeit.Classes.Course;
import fr.alexisnadaud.upgradeit.SQLite.MaBaseSQLite;

public class CourseBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "upgradeit.db";

    private static final String TABLE_COURSE = "table_course";
    private static final String COL_ID_COURSE ="id_course";
    private static final String COL_CLICS_COURSE = "course_clics";
    private static final String COL_NB_PARTICIPANTS = "course_participants";

    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public CourseBDD(Context context){
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

    public long insertCourse(Course course){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID_COURSE, course.getId_course());
        values.put(COL_CLICS_COURSE, course.getCourse_clics());
        values.put(COL_NB_PARTICIPANTS, course.getCourse_participants());

        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_COURSE, null, values);
    }

    public int updateCourse(int id, Course course){
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quelle livre on doit mettre à jour grâce à l'ID

        ContentValues values = new ContentValues();
        values.put(COL_ID_COURSE, course.getId_course());
        values.put(COL_CLICS_COURSE, course.getCourse_clics());
        values.put(COL_NB_PARTICIPANTS, course.getCourse_participants());

        return bdd.update(TABLE_COURSE, values, COL_ID_COURSE + " = " +id, null);
    }

    public int removeCourse(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_COURSE, COL_ID_COURSE + " = " +id, null);
    }

    public Course getCourseParId(int id){
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_COURSE, new String[] {COL_ID_COURSE, COL_CLICS_COURSE, COL_NB_PARTICIPANTS}, COL_ID_COURSE + " = " + id +" ", null, null, null, null);
        return cursorToCourse(c);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Course cursorToCourse(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();

        Course course = new Course();

        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        course.setId_course(c.getInt(0));
        course.setCourse_clics(c.getInt(1));
        course.setCourse_participants(c.getInt(2));

        //On ferme le cursor
        c.close();

        //On retourne la course
        return course;
    }
}