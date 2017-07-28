package fr.developpement.remi.kumquatmusic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by olivi on 28/07/2017.
 */

public class Lecteur extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecteur);
        // Titre musique
        TextView titreMusique = (TextView) findViewById(R.id.TitreMusiqueLecteur);
        titreMusique.setText(MainActivity.getAdapter().titreLigne);

        // image musique
        ImageView imageMusique = (ImageView) findViewById(R.id.imageMusiqueLecteur);
        imageMusique.setImageResource(MainActivity.getAdapter().img);
    }
}
