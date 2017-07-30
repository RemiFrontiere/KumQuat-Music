package fr.developpement.remi.kumquatmusic;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by olivi on 28/07/2017.
 */

public class Lecteur extends AppCompatActivity {

    private ImageButton lecteur;
    private ImageButton precedent;
    private ImageButton suivant;
    private ImageButton pause;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecteur);
        this.lecteur = (ImageButton) findViewById(R.id.btLecteur);
        TextView titreMusique = (TextView) findViewById(R.id.TitreMusiqueLecteur);
        titreMusique.setText(MainActivity.getAdapter().titreLigne);
        ImageView imageMusique = (ImageView) findViewById(R.id.imageMusiqueLecteur);
        imageMusique.setImageBitmap(BitmapFactory.decodeResource(getResources(), MainActivity.getAdapter().img));
        this.precedent = (ImageButton) findViewById(R.id.btPrecedent);
        this.suivant = (ImageButton) findViewById(R.id.btSuivant);
        this.pause = (ImageButton) findViewById(R.id.btPause);

        if ((Integer) MainActivity.pause.getTag() == R.mipmap.pause){
            this.pause.setTag(R.mipmap.pause);
            this.pause.setImageResource(R.mipmap.pause);
    }
        else{
            this.pause.setTag(R.mipmap.play);
            this.pause.setImageResource(R.mipmap.play);
        }

        this.precedent.setTag(R.mipmap.previous);
        this.suivant.setTag(R.mipmap.next);



        lecteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lecteur.this.finish();
            }

        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((Integer)MainActivity.pause.getTag() == R.mipmap.pause) {
                    if (MainActivity.maMusique.monPlayer.isPlaying()) {
                        MainActivity.maMusique.monPlayer.pause();
                        MainActivity.pause.setImageResource(R.mipmap.play);
                        MainActivity.pause.setTag(R.mipmap.play);
                        pause.setImageResource(R.mipmap.play);
                        pause.setTag(R.mipmap.play);
                    }

                } else {
                    MainActivity.maMusique.monPlayer.start();
                    MainActivity.pause.setImageResource(R.mipmap.pause);
                    MainActivity.pause.setTag(R.mipmap.pause);
                    pause.setImageResource(R.mipmap.pause);
                    pause.setTag(R.mipmap.pause);
                }
            }

        });

        precedent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lecteur.this.finish();
                MainActivity.precedent.performClick();
            }
        });

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lecteur.this.finish();
                MainActivity.suivant.performClick();
            }
        });
    }
}
