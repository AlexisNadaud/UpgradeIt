package fr.alexisnadaud.upgradeit.Managers;

/**
 * Created by Alexis on 22/03/2018.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.alexisnadaud.upgradeit.Classes.Vehicule;
import fr.alexisnadaud.upgradeit.SQLite.MaBaseSQLite;

public class VehiculeBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "upgradeit.db";

    private static final String TABLE_VEHICULE = "table_vehicule";

    private static final String COL_ID_VEHICULE = "id_vehicule";
    private static final String COL_NOM_VEHICULE ="nom_vehicule";
    private static final String COL_NIV_VEHICULE = "niveau_vehicule";
    private static final String COL_NIV_ROUES = "niveau_roues";
    private static final String COL_NIV_CARROSSERIE = "niveau_carrosserie";
    private static final String COL_NIV_MOTEUR = "niveau_moteur";
    private static final String COL_NIV_FREINS = "niveau_freins";
    private static final String COL_NIV_BOITE = "niveau_boite";

    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public VehiculeBDD(Context context){
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

    public long insertVehicule(Vehicule vehicule){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID_VEHICULE, vehicule.getId_vehicule());
        values.put(COL_NOM_VEHICULE, vehicule.getNom_vehicule());
        values.put(COL_NIV_VEHICULE,vehicule.getNiveau_vehicule());
        values.put(COL_NIV_ROUES,vehicule.getNiveau_roues());
        values.put(COL_NIV_CARROSSERIE,vehicule.getNiveau_carrosserie());
        values.put(COL_NIV_MOTEUR,vehicule.getNiveau_moteur());
        values.put(COL_NIV_FREINS,vehicule.getNiveau_freins());
        values.put(COL_NIV_BOITE,vehicule.getNiveau_boite());

        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_VEHICULE, null, values);
    }

    public int updateVehicule(int id, Vehicule vehicule){
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quelle livre on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_ID_VEHICULE, vehicule.getId_vehicule());
        values.put(COL_NOM_VEHICULE, vehicule.getNom_vehicule());
        values.put(COL_NIV_VEHICULE,vehicule.getNiveau_vehicule());
        values.put(COL_NIV_ROUES,vehicule.getNiveau_roues());
        values.put(COL_NIV_CARROSSERIE,vehicule.getNiveau_carrosserie());
        values.put(COL_NIV_MOTEUR,vehicule.getNiveau_moteur());
        values.put(COL_NIV_FREINS,vehicule.getNiveau_freins());
        values.put(COL_NIV_BOITE,vehicule.getNiveau_boite());
        return bdd.update(TABLE_VEHICULE, values, COL_ID_VEHICULE + " = " +id, null);
    }

    public int removeVehiculeWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_VEHICULE, COL_ID_VEHICULE + " = " +id, null);
    }

    public Vehicule getVehiculeWithId(int id){
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_VEHICULE, new String[] {COL_ID_VEHICULE, COL_NOM_VEHICULE, COL_NIV_VEHICULE, COL_NIV_ROUES, COL_NIV_CARROSSERIE, COL_NIV_MOTEUR, COL_NIV_FREINS, COL_NIV_BOITE}, COL_ID_VEHICULE + " = " + id +" ", null, null, null, null);
        return cursorToVehicule(c);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Vehicule cursorToVehicule(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();

        //On créé un véhicule
        Vehicule vehicule = new Vehicule();

        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        vehicule.setId_vehicule(c.getInt(0));
        vehicule.setNom_vehicule(c.getString(1));
        vehicule.setNiveau_vehicule(c.getInt(2));
        vehicule.setNiveau_roues(c.getInt(3));
        vehicule.setNiveau_carrosserie(c.getInt(4));
        vehicule.setNiveau_moteur(c.getInt(5));
        vehicule.setNiveau_freins(c.getInt(6));
        vehicule.setNiveau_boite(c.getInt(7));
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return vehicule;
    }
}