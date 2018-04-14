package fr.alexisnadaud.upgradeit.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import fr.alexisnadaud.upgradeit.GifImageView;
import fr.alexisnadaud.upgradeit.R;


public class CourseActivity extends AppCompatActivity implements View.OnClickListener {

    private int points;
    private boolean estLance;
    private int nbDemarrage = 0;

    public void setFontAdventPro(){
        TextView menu_garage = (TextView)this.findViewById(R.id.menu_garage);
        TextView menu_accueil = (TextView)this.findViewById(R.id.menu_game);
        TextView menu_course = (TextView)this.findViewById(R.id.menu_course);
        TextView titre_course = (TextView)this.findViewById(R.id.titre_course);
        TextView text_sec = (TextView)this.findViewById(R.id.texte_secondes);
        TextView temps_restant = (TextView)this.findViewById(R.id.temps_restant);
        Button bouton_demarrer = this.findViewById(R.id.bouton_demarrer);
        TextView j1 = (TextView)this.findViewById(R.id.joueur1);
        TextView j2 = (TextView)this.findViewById(R.id.joueur2);
        String advent = "adventpro-medium.ttf";

        setFont(titre_course,advent);
        setFont(text_sec,advent);
        setFont(j1,advent);
        setFont(j2,advent);
        setFont(temps_restant,advent);
        setFont(menu_garage,advent);
        setFont(menu_accueil,advent);
        setFont(menu_course,advent);
        setFont(bouton_demarrer,advent);
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

        ImageView imageVoiture = (ImageView)this.findViewById(R.id.imageVoiture);
        imageVoiture.setOnClickListener(this);

        TextView menu_stats = (TextView)this.findViewById(R.id.menu_stats);
        menu_stats.setOnClickListener(this);

        TextView menu_game = (TextView)this.findViewById(R.id.menu_game);
        menu_game.setOnClickListener(this);

        Button bouton_demarrer = this.findViewById(R.id.bouton_demarrer);
        bouton_demarrer.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setFontAdventPro();

        this.ajoutEcouteursSurBoutons();
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

        if(view.getId() == R.id.bouton_demarrer){
            final TextView temps_restant = this.findViewById(R.id.temps_restant);
            final Button bouton_demarrer = this.findViewById(R.id.bouton_demarrer);
            final TextView j1 = this.findViewById(R.id.joueur1);
            final TextView j2 = this.findViewById(R.id.joueur2);
            final TextView text1 = this.findViewById(R.id.temps_restant);
            final TextView text2 = this.findViewById(R.id.texte_secondes);
            estLance = true;
            points = 0;
            nbDemarrage++;

            bouton_demarrer.setClickable(false);
            CountDownTimer countDownTimer = new CountDownTimer(60 * 1000, 1000) {
                public void onTick(long millisUntilFinished) {
                    temps_restant.setText("" + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    temps_restant.setText("0");
                    estLance = false;
                    bouton_demarrer.setClickable(true);

                    if (nbDemarrage >= 2){
                        /*
                        if(Integer.parseInt(j1.getText().toString()) > Integer.parseInt(j2.getText().toString())){
                            text1.setText("Joueur 1");
                            text2.setText("a gagné !");
                        }
                        else if(Integer.parseInt(j2.getText().toString()) > Integer.parseInt(j1.getText().toString())){
                            text1.setText("Joueur 2");
                            text2.setText("a gagné !");
                        }
                        else {
                            text1.setText("Egalité !");
                        }
                        */
                    }
                    //Ici je met le "Terminé !" -> Gif ?
                }
            };
            countDownTimer.cancel();
            countDownTimer.start();
        }
        if(view.getId() == R.id.menu_game){
            final Intent intentGame = new Intent(this, GameActivity.class);
            this.startActivity(intentGame);
        }

        if(view.getId() == R.id.menu_garage){
            final Intent intentGarage = new Intent(this, GarageActivity.class);
            this.startActivity(intentGarage);
        }

        if(view.getId() == R.id.menu_stats){
            final Intent intentStats = new Intent(this, StatsActivity.class);
            this.startActivity(intentStats);
        }
    }

    public void onClickVoiture(View v) {

        TextView j1 = (TextView)this.findViewById(R.id.joueur1);
        TextView j2 = (TextView)this.findViewById(R.id.joueur2);
        if(estLance){

            ImageView zoom = (ImageView) findViewById(R.id.imageVoiture);
            Animation zoomAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.zoom);
            zoom.startAnimation(zoomAnimation);

            points ++;
            if(nbDemarrage == 1) {
                j1.setText("J1 : " + points + " points");
            }
            else if(nbDemarrage == 2) {
                j2.setText("J2 : " + points + " points");
            }
        }

    }
}