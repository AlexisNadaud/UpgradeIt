package fr.alexisnadaud.upgradeit.Activities;

import android.content.Intent;
import android.graphics.Typeface;
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


public class StatsActivity extends AppCompatActivity implements View.OnClickListener{

    private VehiculeBDD vehiculeBDD = new VehiculeBDD(this);
    private Vehicule vehicule = new Vehicule(1, 1, "Voiture de base", 1, 1, 1, 1, 1);
    private PrixLevelUpBDD prixLevelUpBDD = new PrixLevelUpBDD(this);
    private PrixLevelUp prixLevelUp = new PrixLevelUp();
    private JoueurBDD joueurBDD = new JoueurBDD(this);
    private Joueur joueur = new Joueur(1,1,"Joueur",0,0,0);

    public void setFontAdventPro(){
        TextView menu_garage = (TextView)this.findViewById(R.id.menu_garage);
        TextView menu_accueil = (TextView)this.findViewById(R.id.menu_game);
        TextView menu_course = (TextView)this.findViewById(R.id.menu_course);
        TextView stats_joueur = (TextView)this.findViewById(R.id.stats_joueur);
        TextView stats_clics = (TextView)this.findViewById(R.id.stats_clics);
        TextView stats_points = (TextView)this.findViewById(R.id.stats_pts);
        TextView stats_vehicule = (TextView)this.findViewById(R.id.stats_voiture);
        TextView stats_pseudo_joueur = (TextView)this.findViewById(R.id.stats_pseudo_joueur);
        TextView stats_nb_clics = (TextView)this.findViewById(R.id.stats_nb_clics);
        TextView stats_nb_points = (TextView)this.findViewById(R.id.stats_nb_pts);
        TextView stats_lvl_voiture = (TextView)this.findViewById(R.id.stats_lvl_voiture);

        String advent = "adventpro-medium.ttf";

        setFont(stats_joueur,advent);
        setFont(stats_points,advent);
        setFont(stats_clics,advent);
        setFont(stats_vehicule,advent);
        setFont(menu_garage,advent);
        setFont(menu_accueil,advent);
        setFont(menu_course,advent);
        setFont(stats_pseudo_joueur,advent);
        setFont(stats_nb_clics,advent);
        setFont(stats_nb_points,advent);
        setFont(stats_lvl_voiture,advent);
    }

    public void setFont(TextView textView, String fontName) {
        if(fontName != null){
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
        TextView menu_garage = (TextView)this.findViewById(R.id.menu_garage);
        menu_garage.setOnClickListener(this);

        TextView menu_course = (TextView)this.findViewById(R.id.menu_course);
        menu_course.setOnClickListener(this);

        TextView menu_game = (TextView)this.findViewById(R.id.menu_game);
        menu_game.setOnClickListener(this);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        prixLevelUpBDD.open();
        vehiculeBDD.open();
        joueurBDD.open();

        Joueur joueurFromBDD = joueurBDD.getJoueurParId(joueur.getIdJoueur());
        Vehicule vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(vehicule.getId_vehicule());

        TextView stats_pseudo_joueur = (TextView)this.findViewById(R.id.stats_pseudo_joueur);
        TextView stats_nb_clics = (TextView)this.findViewById(R.id.stats_nb_clics);
        TextView stats_nb_points = (TextView)this.findViewById(R.id.stats_nb_pts);
        TextView stats_lvl_voiture = (TextView)this.findViewById(R.id.stats_lvl_voiture);

        stats_pseudo_joueur.setText(joueurFromBDD.getNom());
        stats_nb_clics.setText(String.valueOf(joueurFromBDD.getNbClics()));
        stats_nb_points.setText(String.valueOf(joueurFromBDD.getNbPoints()));
        stats_lvl_voiture.setText(String.valueOf(vehiculeFromBDD.getNiveau_vehicule()));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView imageVoiture = (this).findViewById(R.id.imageVoiture);
        setImage(vehiculeFromBDD, imageVoiture);

        this.ajoutEcouteursSurBoutons();
        this.setFontAdventPro();

        vehiculeBDD.close();
        prixLevelUpBDD.close();
        joueurBDD.close();
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

    @Override
    public void onClick(View view) {
        //Méthode qui s'active si on appuie sur un des écouteurs de la vue
        if(view.getId() == R.id.menu_game){
            final Intent intentGame = new Intent(this, GameActivity.class);
            this.startActivity(intentGame);
        }

        if(view.getId() == R.id.menu_course){
            final Intent intentCourse = new Intent(this, CourseActivity.class);
            this.startActivity(intentCourse);
        }

        if(view.getId() == R.id.menu_garage){
            final Intent intentGarage = new Intent(this, GarageActivity.class);
            this.startActivity(intentGarage);
        }
    }
}