package fr.alexisnadaud.upgradeit.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import fr.alexisnadaud.upgradeit.Classes.Joueur;
import fr.alexisnadaud.upgradeit.Classes.PrixLevelUp;
import fr.alexisnadaud.upgradeit.Classes.Vehicule;
import fr.alexisnadaud.upgradeit.GifImageView;
import fr.alexisnadaud.upgradeit.Managers.JoueurBDD;
import fr.alexisnadaud.upgradeit.Managers.PrixLevelUpBDD;
import fr.alexisnadaud.upgradeit.Managers.VehiculeBDD;
import fr.alexisnadaud.upgradeit.R;


public class GameActivity extends AppCompatActivity implements View.OnClickListener  {

    private VehiculeBDD vehiculeBDD = new VehiculeBDD(this);
    private Vehicule vehicule = new Vehicule(1, 1, "Voiture de base", 1, 1, 1, 1, 1);
    private PrixLevelUpBDD prixLevelUpBDD = new PrixLevelUpBDD(this);
    private PrixLevelUp prixLevelUp = new PrixLevelUp();
    private JoueurBDD joueurBDD = new JoueurBDD(this);
    private Joueur joueur = new Joueur(1,1,"Joueur",0,0,0);

    public void setFontAdventPro(){
        TextView menu_garage = (TextView)this.findViewById(R.id.menu_garage);
        TextView menu_stats = (TextView)this.findViewById(R.id.menu_stats);
        TextView menu_course = (TextView)this.findViewById(R.id.menu_course);
        TextView main_nb_points = (TextView)this.findViewById(R.id.MainNbPoints);
        TextView main_points = (TextView)this.findViewById(R.id.MainPoints);
        TextView main_niveau = (TextView)this.findViewById(R.id.MainNiveau);
        TextView main_nb_niveau = (TextView)this.findViewById(R.id.MainNbNiveau);

        String advent = "adventpro-medium.ttf";

        setFont(main_niveau,advent);
        setFont(main_nb_points,advent);
        setFont(main_points,advent);
        setFont(main_nb_niveau,advent);
        setFont(menu_garage,advent);
        setFont(menu_stats,advent);
        setFont(menu_course,advent);
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
        ImageView imageVoiture = (ImageView)this.findViewById(R.id.imageVoiture);
        imageVoiture.setOnClickListener(this);

        TextView menu_garage = (TextView)this.findViewById(R.id.menu_garage);
        menu_garage.setOnClickListener(this);

        TextView menu_stats = (TextView)this.findViewById(R.id.menu_stats);
        menu_stats.setOnClickListener(this);

        TextView menu_course = (TextView)this.findViewById(R.id.menu_course);
        menu_course.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GifImageView gifImageView = (GifImageView) findViewById(R.id.GifImageView);
        gifImageView.setGifImageResource(R.drawable.level_up);
        gifImageView.setVisibility(View.INVISIBLE);

        prixLevelUpBDD.open();

        prixLevelUpBDD.insertValues();


        vehiculeBDD.open();
        joueurBDD.open();

        Joueur joueurFromBDD = joueurBDD.getJoueurParId(joueur.getIdJoueur());

        if (joueurFromBDD == null){
            joueurBDD.insertJoueur(joueur);
            joueurFromBDD = joueurBDD.getJoueurParId(1);
        }

        TextView nbPoints = findViewById(R.id.MainNbPoints);
        nbPoints.setText(String.valueOf(joueurFromBDD.getNbPoints()));


        Vehicule vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(1);



        if (vehiculeFromBDD == null){
            vehiculeBDD.insertVehicule(vehicule);
            vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(1);
        }

        TextView nbNiveaux = findViewById(R.id.MainNbNiveau);
        nbNiveaux.setText(String.valueOf(vehiculeFromBDD.getNiveau_vehicule()));

        joueurFromBDD = joueurBDD.getJoueurParId(joueur.getIdJoueur());

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(joueurFromBDD.getProgressBar());
        if(vehiculeFromBDD.getNiveau_vehicule()<=30) {
            PrixLevelUp prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_vehicule());
            progressBar.setMax(prixLevelUpFromBDD.getPoints());
        }

        ImageView imageVoiture = (this).findViewById(R.id.imageVoiture);
        setImage(vehiculeFromBDD, imageVoiture);

        vehiculeBDD.close();
        joueurBDD.close();
        prixLevelUpBDD.close();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.ajoutEcouteursSurBoutons();
        this.setFontAdventPro();
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
        if(view.getId() == R.id.imageVoiture){
            onClickVoiture(view);
        }

        if(view.getId() == R.id.menu_course){
            final Intent intentCourse = new Intent(GameActivity.this, CourseActivity.class);
            this.startActivity(intentCourse);
        }

        if(view.getId() == R.id.menu_garage){
            final Intent intentGarage = new Intent(GameActivity.this, GarageActivity.class);
            this.startActivity(intentGarage);
        }

        if(view.getId() == R.id.menu_stats){
            final Intent intentStats = new Intent(GameActivity.this, StatsActivity.class);
            this.startActivity(intentStats);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        prixLevelUpBDD.open();
        vehiculeBDD.open();
        joueurBDD.open();

        Joueur joueurFromBDD = joueurBDD.getJoueurParId(joueur.getIdJoueur());

        TextView nbPoints = findViewById(R.id.MainNbPoints);
        nbPoints.setText(String.valueOf(joueurFromBDD.getNbPoints()));

        Vehicule vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(1);



        if (vehiculeFromBDD == null){
            vehiculeBDD.insertVehicule(vehicule);
            vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(1);
        }

        TextView nbNiveaux = findViewById(R.id.MainNbNiveau);
        nbNiveaux.setText(String.valueOf(vehiculeFromBDD.getNiveau_vehicule()));

        joueurFromBDD = joueurBDD.getJoueurParId(joueur.getIdJoueur());

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(joueurFromBDD.getProgressBar());
        if(vehiculeFromBDD.getNiveau_vehicule()<=30) {
            PrixLevelUp prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_vehicule());
            progressBar.setMax(prixLevelUpFromBDD.getPoints());
        }
        vehiculeBDD.close();
        joueurBDD.close();
        prixLevelUpBDD.close();
    }

    public void onClickVoiture(View v) {

        joueurBDD.open();
        prixLevelUpBDD.open();
        vehiculeBDD.open();

        Joueur joueurFromBDD = joueurBDD.getJoueurParId(1);
        Vehicule vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(1);
        PrixLevelUp prixLevelUpFromBDD;

        if(vehiculeFromBDD.getNiveau_vehicule()<=30) {
            prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_vehicule());
        }else {
            prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(30);
        }

        ImageView zoom = (ImageView) findViewById(R.id.imageVoiture);
        Animation zoomAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.zoom);
        zoom.startAnimation(zoomAnimation);


        //Vehicule niveau 1 = 1 Vehicule niveau 50 = 50 pts d'un coup
        int nbPointsAugment = vehiculeFromBDD.getNiveau_vehicule();

        TextView NombrePoints = findViewById(R.id.MainNbPoints);
        int nbPoints = joueurFromBDD.getNbPoints() + nbPointsAugment;
        joueurFromBDD.setNbPoints(nbPoints);
        NombrePoints.setText(String.valueOf(nbPoints));

        ProgressBar progressBar = findViewById(R.id.progressBar);

        //On augmente la progressBar
        int nbProgress = joueurFromBDD.getProgressBar();

        TextView nbNiveaux = findViewById(R.id.MainNbNiveau);

        
        if (nbProgress >= prixLevelUpFromBDD.getPoints()){

            vehiculeFromBDD.setNiveau_vehicule(vehiculeFromBDD.getNiveau_vehicule() + 1);
            nbNiveaux.setText(String.valueOf(vehiculeFromBDD.getNiveau_vehicule()));

            vehiculeBDD.updateVehicule(1, vehiculeFromBDD);
            vehiculeFromBDD = vehiculeBDD.getVehiculeWithId(1);
            prixLevelUpFromBDD = prixLevelUpBDD.getPrixParLevel(vehiculeFromBDD.getNiveau_vehicule());

            progressBar.setProgress(0);
            progressBar.setMax(prixLevelUpFromBDD.getPoints());

            joueurFromBDD.setProgressBar(0);

            new CountDownTimer(3000, 1000) {

                public void onTick(long millisUntilFinished) {
                    GifImageView gifImageView = (GifImageView) findViewById(R.id.GifImageView);
                    gifImageView.setVisibility(View.VISIBLE);
                }

                public void onFinish() {
                    GifImageView gifImageView = (GifImageView) findViewById(R.id.GifImageView);
                    gifImageView.setVisibility(View.INVISIBLE);
                }
            }.start();

            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(2500, VibrationEffect.DEFAULT_AMPLITUDE));
            }else{
                //deprecated in API 26
                vibrator.vibrate(2500);
            }

            final MediaPlayer mp = MediaPlayer.create(this, R.raw.level_up_sound);
            mp.start();

            joueurFromBDD.setNbClics(joueurFromBDD.getNbClics()+1);
        }
        else {
            nbProgress ++;
            progressBar.setProgress(nbProgress);
            joueurFromBDD.setProgressBar(nbProgress);
            joueurFromBDD.setNbClics(joueurFromBDD.getNbClics()+1);
        }


        joueurBDD.updateJoueur(1, joueurFromBDD);

        //REMPLACER LE ++ PAR UN GETTEUR DU NB POINTS A GAGNER EN FCT DU NIVEAU
        nbPoints= nbPoints + nbPointsAugment;

        ImageView imageVoiture = (this).findViewById(R.id.imageVoiture);
        setImage(vehiculeFromBDD, imageVoiture);

        joueurBDD.close();
        prixLevelUpBDD.close();
        vehiculeBDD.close();
    }
}