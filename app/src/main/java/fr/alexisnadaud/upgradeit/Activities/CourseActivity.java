package fr.alexisnadaud.upgradeit.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import fr.alexisnadaud.upgradeit.R;


public class CourseActivity extends AppCompatActivity implements View.OnClickListener {

    private void ajoutEcouteursSurBoutons() {
        //Rajout des écouteurs sur les boutons
        TextView menu_garage = (TextView)this.findViewById(R.id.menu_garage);
        menu_garage.setOnClickListener(this);

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

        if(view.getId() == R.id.bouton_demarrer){
            final TextView temps_restant = this.findViewById(R.id.temps_restant);
            CountDownTimer countDownTimer = new CountDownTimer(60 * 1000, 1000) {
                public void onTick(long millisUntilFinished) {
                    temps_restant.setText("" + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    temps_restant.setText("0");

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
}