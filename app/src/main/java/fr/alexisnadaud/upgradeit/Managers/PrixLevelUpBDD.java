package fr.alexisnadaud.upgradeit.Managers;

/**
 * Created by Alexis on 22/03/2018.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.alexisnadaud.upgradeit.Classes.PrixLevelUp;
import fr.alexisnadaud.upgradeit.Classes.Vehicule;
import fr.alexisnadaud.upgradeit.SQLite.MaBaseSQLite;

public class PrixLevelUpBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "upgradeit.db";

    private static final String TABLE_PRIXLEVELUP = "table_prix";
    private static final String COL_LEVEL_PLU = "plu_level";
    private static final String COL_PRIX_PLU ="plu_prix";
    private static final String COL_POINTS_PLU ="plu_points";

    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public PrixLevelUpBDD(Context context){
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

    public void insertValues(){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        int prixDeBase = 100;
        int exp = 100;
        for (int i=1; i<=30; i++) {
            values.put(COL_LEVEL_PLU, i);
            values.put(COL_PRIX_PLU, prixDeBase);
            values.put(COL_POINTS_PLU, exp);
            bdd.insert(TABLE_PRIXLEVELUP, null, values);
            prixDeBase = (int) (1.15 * prixDeBase + prixDeBase);
            exp = (int) (1.2 * prixDeBase + prixDeBase);
        }
    }

    public PrixLevelUp getPrixParLevel(int level){
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_PRIXLEVELUP, new String[] {COL_LEVEL_PLU,COL_PRIX_PLU,COL_POINTS_PLU}, COL_LEVEL_PLU + " = " + level +" ", null, null, null, null);
        return cursorToVehicule(c);
    }

    private PrixLevelUp cursorToVehicule(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();

        PrixLevelUp prixLevelUp = new PrixLevelUp();

        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        prixLevelUp.setLevel(c.getInt(0));
        prixLevelUp.setPrix(c.getInt(1));
        prixLevelUp.setPoints(c.getInt(2));

        //On ferme le cursor
        c.close();

        return prixLevelUp;
    }
}