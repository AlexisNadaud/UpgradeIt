package fr.alexisnadaud.upgradeit.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import fr.alexisnadaud.upgradeit.Classes.Joueur;
import fr.alexisnadaud.upgradeit.Classes.PrixLevelUp;
import fr.alexisnadaud.upgradeit.Classes.Vehicule;
import fr.alexisnadaud.upgradeit.Managers.JoueurBDD;
import fr.alexisnadaud.upgradeit.Managers.PrixLevelUpBDD;
import fr.alexisnadaud.upgradeit.Managers.VehiculeBDD;
import fr.alexisnadaud.upgradeit.R;


public class GarageActivity extends AppCompatActivity implements View.OnClickListener {

    private VehiculeBDD vehiculeBDD = new VehiculeBDD(this);
    private Vehicule vehicule = new Vehicule(1, 1, "Voiture de base", 1, 1, 1, 1, 1);
    private PrixLevelUpBDD prixLevelUpBDD = new PrixLevelUpBDD(this);
    private PrixLevelUp prixLevelUp = new PrixLevelUp();
    private JoueurBDD joueurBDD = new JoueurBDD(this);
    private Joueur joueur = new Joueur(1,1,"Joueur",0,0,0);

    public void setFontAdventPro() {
        TextView roues = (TextView) this.findViewById(R.id.roues);
        TextView erreur = (TextView) this.findViewById(R.id.message_erreur);
        TextView freins = (TextView) this.findViewById(R.id.freins);
        TextView carrosserie = (TextView) this.findViewById(R.id.carrosserie);
        TextView moteur = (TextView) this.findViewById(R.id.moteur);
        TextView bte_de_vit = (TextView) this.findViewById(R.id.bte_de_vit);
        TextView titre_garage = (TextView) this.findViewById(R.id.titre_garage);
        TextView ameliorations = (TextView) this.findViewById(R.id.ameliorations);
        TextView roues_prix = (TextView) this.findViewById(R.id.roues_prix);
        TextView carrosserie_prix = (TextView) this.findViewById(R.id.carrosserie_prix);
        TextView moteur_prix = (TextView) this.findViewById(R.id.moteur_prix);
        TextView freins_prix = (TextView) this.findViewById(R.id.freins_prix);
        TextView bte_de_vit_prix = (TextView) this.findViewById(R.id.bte_de_vit_prix);
        TextView garage_nb_points = (TextView) this.findViewById(R.id.garage_nb_points);
        TextView menu_game = (TextView) this.findViewById(R.id.menu_game);
        TextView menu_stats = (TextView) this.findViewById(R.id.menu_stats);
        TextView menu_course = (TextView) this.findViewById(R.id.menu_course);

        String advent = "adventpro-medium.ttf";

        setFont(erreur, advent);
        setFont(titre_garage, advent);
        setFont(ameliorations, advent);
        setFont(roues, advent);
        setFont(roues_prix, advent);
        setFont(carrosserie, advent);
        setFont(carrosserie_prix, advent);
        setFont(moteur, advent);
        setFont(moteur_prix, advent);
        setFont(freins, advent);
        setFont(freins_prix, advent);
        setFont(bte_de_vit, advent);
        setFont(bte_de_vit_prix, advent);
        setFont(garage_nb_points, advent);
        setFont(menu_game, advent);
        setFont(menu_stats, advent);
        setFont(menu_course, advent);
    }

    public void setFont(TextView textView, String fontName) {
        if (fontName != null) {
            try {
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/" + fontName);
                textView.setTypeface(typeface);
            } catch (Exception e) {
                Log.e("FONT", fontName + " not found", e);
            }
        }
    }

    private void ajoutEcouteursSurBoutons() {
        //Rajout des écouteurs sur les boutons
        TextView menu_course = (TextView) this.findViewById(R.id.menu_course);
        menu_course.setOnClickListener(this);

        TextView menu_stats = (TextView) this.findViewById(R.id.menu_stats);
        menu_stats.setOnClickListener(this);

        TextView menu_game = (TextView) this.findViewById(R.id.menu_game);
        menu_game.setOnClickListener(this);

        ImageView achat_niveau_roues = (ImageView) this.findViewById(R.id.lvl_up_roues);
        achat_niveau_roues.setOnClickListener(this);

        ImageView achat_niveau_carrosserie = (ImageView) this.findViewById(R.id.lvl_up_carrosserie);
        achat_niveau_carrosserie.setOnClickListener(this);

        ImageView achat_niveau_moteur = (ImageView) this.findViewById(R.id.lvl_up_moteur);
        achat_niveau_moteur.setOnClickListener(this);

        ImageView achat_niveau_freins = (ImageView) this.findViewById(R.id.lvl_up_freins);
        achat_niveau_freins.setOnClickListener(this);

        ImageView achat_niveau_bte_de_vit = (ImageView) this.findViewById(R.id.lvl_up_bte_de_vit);
        achat_niveau_bte_de_vit.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);

        prixLevelUpBDD.open();
        vehiculeBDD.open();
        joueurBDD.open();

        Joueur joueurFromBDD = joueurBDD.getJoueurParId(joueur.getIdJoueur());

        if (joueurFromBDD == null){
            joueurBDD.insertJoueur(joueur);
            joueurFromBDD = joueurBDD.getJoueurParId(1);
        }

        TextView nbPoints = findViewById(R.id.garage_nb_points);
        nbPoints.setText("[Dispo] " + String.valueOf(joueurFromBDD.getNbPoints()) + " points !");



        joueurBDD.close();

        Vehicule vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(vehicule.getId_vehicule());

        if (vehiculeFromBDD == null){
            vehiculeBDD.insertVehicule(vehicule);
        }

        vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.ajoutEcouteursSurBoutons();
        this.setFontAdventPro();

        TextView roues = findViewById(R.id.roues);
        roues.setText("Roues : " + vehiculeFromBDD.getNiveau_roues());

        TextView carrosserie = findViewById(R.id.carrosserie);
        carrosserie.setText("Carrosserie : " + vehiculeFromBDD.getNiveau_carrosserie());

        TextView moteur = findViewById(R.id.moteur);
        moteur.setText("Moteur : " + vehiculeFromBDD.getNiveau_moteur());

        TextView freins = findViewById(R.id.freins);
        freins.setText("Freins : " + vehiculeFromBDD.getNiveau_freins());

        TextView boite = findViewById(R.id.bte_de_vit);
        boite.setText("Bte de vitesse : " + vehiculeFromBDD.getNiveau_boite());

        PrixLevelUp prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_roues());
        TextView rouesP = findViewById(R.id.roues_prix);
        rouesP.setText(String.valueOf(prixLevelUpFromBDD.getPrix()) + " pts");

        prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_carrosserie());
        TextView carrosserieP = findViewById(R.id.carrosserie_prix);
        carrosserieP.setText(String.valueOf(prixLevelUpFromBDD.getPrix()) + " pts");

        prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_moteur());
        TextView moteurP = findViewById(R.id.moteur_prix);
        moteurP.setText(String.valueOf(prixLevelUpFromBDD.getPrix()) + " pts");

        prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_freins());
        TextView freinsP = findViewById(R.id.freins_prix);
        freinsP.setText(String.valueOf(prixLevelUpFromBDD.getPrix()) + " pts");

        prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_boite());
        TextView boiteP = findViewById(R.id.bte_de_vit_prix);
        boiteP.setText(String.valueOf(prixLevelUpFromBDD.getPrix()) + " pts");

        ImageView imageVoiture = (this).findViewById(R.id.imageVoiture);
        setImage(vehiculeFromBDD, imageVoiture);

        vehiculeBDD.close();
        prixLevelUpBDD.close();
    }

    private void setImage(Vehicule vehiculeFromBDD, ImageView imageVoiture) {
        if(vehiculeFromBDD.getNiveau_vehicule() < 5){
            imageVoiture.setImageResource(R.mipmap.level0);
        }
        else if(vehiculeFromBDD.getNiveau_vehicule() <10 && vehiculeFromBDD.getNiveau_vehicule()>= 5){
            imageVoiture.setImageResource(R.mipmap.level5);
        }
        else if(vehiculeFromBDD.getNiveau_vehicule() <15 && vehiculeFromBDD.getNiveau_vehicule()>= 10){
            imageVoiture.setImageResource(R.mipmap.level10);
        }
        else if(vehiculeFromBDD.getNiveau_vehicule() <20 && vehiculeFromBDD.getNiveau_vehicule()>= 15){
            imageVoiture.setImageResource(R.mipmap.level15);
        }
        else if(vehiculeFromBDD.getNiveau_vehicule() <25 && vehiculeFromBDD.getNiveau_vehicule()>= 20){
            imageVoiture.setImageResource(R.mipmap.level20);
        }
        else if(vehiculeFromBDD.getNiveau_vehicule() <30 && vehiculeFromBDD.getNiveau_vehicule()>= 25){
            imageVoiture.setImageResource(R.mipmap.level25);
        }
        else if(vehiculeFromBDD.getNiveau_vehicule()>= 30){
            imageVoiture.setImageResource(R.mipmap.level30);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void afficheMsgErreur(){
        TextView erreur = (TextView) this.findViewById(R.id.message_erreur);
        erreur.setVisibility(View.VISIBLE);
    }

    public void cacheMsgErreur(){
        TextView erreur = (TextView) this.findViewById(R.id.message_erreur);
        erreur.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.upgrade_sound);

        if (view.getId() == R.id.menu_game) {
            final Intent intentGame = new Intent(this, GameActivity.class);
            this.startActivity(intentGame);
        }

        if (view.getId() == R.id.menu_course) {
            final Intent intentCourse = new Intent(this, CourseActivity.class);
            this.startActivity(intentCourse);
        }

        if (view.getId() == R.id.menu_stats) {
            final Intent intentStats = new Intent(this, StatsActivity.class);
            this.startActivity(intentStats);
        }

        if (view.getId() == R.id.lvl_up_roues) {

            vehiculeBDD.open();
            joueurBDD.open();
            prixLevelUpBDD.open();

            Joueur joueurFromBDD = joueurBDD.getJoueurParId(1);
            Vehicule vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(1);

            PrixLevelUp prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_roues());

            int nbPoints = joueurFromBDD.getNbPoints();
            int cout = prixLevelUpFromBDD.getPrix();

            if (vehiculeFromBDD != null && nbPoints >= cout) {

                vehiculeFromBDD.setNiveau_roues(vehiculeFromBDD.getNiveau_roues() + 1);
                vehiculeBDD.updateVehicule(vehicule.getId_vehicule(), vehiculeFromBDD);
                joueurFromBDD.setNbPoints(nbPoints-cout);
                joueurBDD.updateJoueur(1, joueurFromBDD);
                mp.start();
            }
            else {
                afficheMsgErreur();
            }

            vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(vehicule.getId_vehicule());
            prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_roues());

            TextView roues = (TextView) this.findViewById(R.id.roues);
            roues.setText("Roues : " + vehiculeFromBDD.getNiveau_roues());

            TextView coutRoues = (TextView) this.findViewById(R.id.roues_prix);
            coutRoues.setText(String.valueOf(prixLevelUpFromBDD.getPrix()) + " pts");

            TextView nbPoints2 = findViewById(R.id.garage_nb_points);
            nbPoints2.setText("[Dispo] " + String.valueOf(joueurFromBDD.getNbPoints()) + " points !");

            vehiculeBDD.close();
            joueurBDD.close();
            prixLevelUpBDD.close();
        }

        if (view.getId() == R.id.lvl_up_carrosserie) {

            vehiculeBDD.open();
            joueurBDD.open();
            prixLevelUpBDD.open();

            Joueur joueurFromBDD = joueurBDD.getJoueurParId(1);
            Vehicule vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(1);
            PrixLevelUp prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_carrosserie());

            int nbPoints = joueurFromBDD.getNbPoints();
            int cout = prixLevelUpFromBDD.getPrix();

            if (vehiculeFromBDD != null && nbPoints >= cout) {

                vehiculeFromBDD.setNiveau_carrosserie(vehiculeFromBDD.getNiveau_carrosserie() + 1);
                vehiculeBDD.updateVehicule(vehicule.getId_vehicule(), vehiculeFromBDD);
                joueurFromBDD.setNbPoints(nbPoints-cout);
                joueurBDD.updateJoueur(1, joueurFromBDD);
                mp.start();
            }
            else {
                afficheMsgErreur();
            }

            vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(vehicule.getId_vehicule());
            prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_carrosserie());

            TextView carrosserie = (TextView) this.findViewById(R.id.carrosserie);
            carrosserie.setText("Carrosserie : " + vehiculeFromBDD.getNiveau_carrosserie());

            TextView coutCarrosserie = (TextView) this.findViewById(R.id.carrosserie_prix);
            coutCarrosserie.setText(String.valueOf(prixLevelUpFromBDD.getPrix())+ " pts");

            TextView nbPoints2 = findViewById(R.id.garage_nb_points);
            nbPoints2.setText("[Dispo] " + String.valueOf(joueurFromBDD.getNbPoints()) + " points !");

            vehiculeBDD.close();
            joueurBDD.close();
            prixLevelUpBDD.close();
        }

        if (view.getId() == R.id.lvl_up_moteur) {

            vehiculeBDD.open();
            joueurBDD.open();
            prixLevelUpBDD.open();

            Joueur joueurFromBDD = joueurBDD.getJoueurParId(1);
            Vehicule vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(1);
            PrixLevelUp prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_moteur());

            int nbPoints = joueurFromBDD.getNbPoints();
            int cout = prixLevelUpFromBDD.getPrix();

            if (vehiculeFromBDD != null && nbPoints >= cout) {

                vehiculeFromBDD.setNiveau_moteur(vehiculeFromBDD.getNiveau_moteur() + 1);
                vehiculeBDD.updateVehicule(vehicule.getId_vehicule(), vehiculeFromBDD);
                joueurFromBDD.setNbPoints(nbPoints-cout);
                joueurBDD.updateJoueur(1, joueurFromBDD);
                mp.start();
            }
            else {
                afficheMsgErreur();
            }

            vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(vehicule.getId_vehicule());
            prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_moteur());

            TextView moteur = (TextView) this.findViewById(R.id.moteur);
            moteur.setText("Moteur : " + vehiculeFromBDD.getNiveau_moteur());

            TextView coutMoteur = (TextView) this.findViewById(R.id.moteur_prix);
            coutMoteur.setText(String.valueOf(prixLevelUpFromBDD.getPrix())+ " pts");

            TextView nbPoints2 = findViewById(R.id.garage_nb_points);
            nbPoints2.setText("[Dispo] " + String.valueOf(joueurFromBDD.getNbPoints()) + " points !");

            vehiculeBDD.close();
            joueurBDD.close();
            prixLevelUpBDD.close();
        }

        if (view.getId() == R.id.lvl_up_freins) {

            vehiculeBDD.open();
            joueurBDD.open();
            prixLevelUpBDD.open();

            Joueur joueurFromBDD = joueurBDD.getJoueurParId(1);
            Vehicule vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(1);
            PrixLevelUp prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_freins());

            int nbPoints = joueurFromBDD.getNbPoints();
            int cout = prixLevelUpFromBDD.getPrix();

            if (vehiculeFromBDD != null && nbPoints >= cout) {

                vehiculeFromBDD.setNiveau_freins(vehiculeFromBDD.getNiveau_freins() + 1);
                vehiculeBDD.updateVehicule(vehicule.getId_vehicule(), vehiculeFromBDD);
                joueurFromBDD.setNbPoints(nbPoints-cout);
                joueurBDD.updateJoueur(1, joueurFromBDD);
                mp.start();
            }
            else {
                afficheMsgErreur();
            }

            vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(vehicule.getId_vehicule());
            prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_freins());

            TextView freins = (TextView) this.findViewById(R.id.freins);
            freins.setText("Freins : " + vehiculeFromBDD.getNiveau_freins());

            TextView coutFreins = (TextView) this.findViewById(R.id.freins_prix);
            coutFreins.setText(String.valueOf(prixLevelUpFromBDD.getPrix())+ " pts");

            TextView nbPoints2 = findViewById(R.id.garage_nb_points);
            nbPoints2.setText("[Dispo] " + String.valueOf(joueurFromBDD.getNbPoints()) + " points !");

            vehiculeBDD.close();
            joueurBDD.close();
            prixLevelUpBDD.close();
        }

        if (view.getId() == R.id.lvl_up_bte_de_vit) {

            vehiculeBDD.open();
            joueurBDD.open();
            prixLevelUpBDD.open();

            Joueur joueurFromBDD = joueurBDD.getJoueurParId(1);
            Vehicule vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(1);

            PrixLevelUp prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_boite());

            int nbPoints = joueurFromBDD.getNbPoints();
            int cout = prixLevelUpFromBDD.getPrix();

            if (vehiculeFromBDD != null && nbPoints >= cout) {

                vehiculeFromBDD.setNiveau_boite(vehiculeFromBDD.getNiveau_boite() + 1);
                vehiculeBDD.updateVehicule(vehicule.getId_vehicule(), vehiculeFromBDD);
                joueurFromBDD.setNbPoints(nbPoints-cout);
                joueurBDD.updateJoueur(1, joueurFromBDD);
                mp.start();
            }
            else {
                afficheMsgErreur();
            }

            vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(vehicule.getId_vehicule());
            prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_boite());

            TextView bte = (TextView) this.findViewById(R.id.bte_de_vit);
            bte.setText("Bte de vitesse : " + vehiculeFromBDD.getNiveau_boite());

            TextView coutBte = (TextView) this.findViewById(R.id.bte_de_vit_prix);
            coutBte.setText(String.valueOf(prixLevelUpFromBDD.getPrix())+ " pts");

            TextView nbPoints2 = findViewById(R.id.garage_nb_points);
            nbPoints2.setText("[Dispo] " + String.valueOf(joueurFromBDD.getNbPoints()) + " points !");

            vehiculeBDD.close();
            joueurBDD.close();
            prixLevelUpBDD.close();
        }



    }

}