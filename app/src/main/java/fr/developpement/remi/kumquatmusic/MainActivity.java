package fr.developpement.remi.kumquatmusic;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Music maMusique;
    private ListView maListe;
    private ImageButton precedent;
    private ImageButton suivant;
    private ImageButton pause;
    private ImageButton stop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.maMusique = new Music();
        this.maListe = (ListView)findViewById(R.id.maListe);

        this.precedent = (ImageButton)findViewById(R.id.btPrecedent) ;
        this.suivant = (ImageButton)findViewById(R.id.btSuivant) ;
        this.stop = (ImageButton)findViewById(R.id.btStop) ;
        this.pause = (ImageButton)findViewById(R.id.btPause) ;
        this.pause.setTag(R.mipmap.pause);


        ArrayList<LigneMusic> LignesMusics = genererLigneMusic();
        final MusicAdapter adapter = new MusicAdapter(MainActivity.this,LignesMusics);
        maListe.setAdapter(adapter);

        maListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (maMusique.monPlayer != null && maMusique.monPlayer.isPlaying())
                {
                    maMusique.monPlayer.stop();
                }

                // #2196F3
                Object o = maListe.getItemAtPosition(position);
                LigneMusic UneLigne = (LigneMusic)o;
                adapter.couleur = Color.parseColor("#42A5F5");
                adapter.titreLigne = ((LigneMusic) o).getText();
                maListe.setAdapter(adapter);

                maMusique.Lire(MainActivity.this,UneLigne.getText());

            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    if ((Integer)pause.getTag() == R.mipmap.pause) {
                        if (maMusique.monPlayer.isPlaying()) {
                            maMusique.monPlayer.pause();
                            pause.setImageResource(R.mipmap.play);
                            pause.setTag(R.mipmap.play);
                        }

                    } else {
                            maMusique.monPlayer.start();
                            pause.setImageResource(R.mipmap.pause);
                            pause.setTag(R.mipmap.pause);
                    }
                }

        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(maMusique.monPlayer.isPlaying())
                    maMusique.monPlayer.stop();
            }
        });

        precedent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }
    
    private ArrayList<LigneMusic> genererLigneMusic(){
        ArrayList<LigneMusic> MesLignesMusics = new ArrayList<LigneMusic>();
        for (int i = 0; i < maMusique.MesMusics.size(); i++){
            MesLignesMusics.add(new LigneMusic(maMusique.MesMusics.get(i).uneImg,maMusique.MesMusics.get(i).leTitre,maMusique.MesMusics.get(i).laDuree));
        }

        return MesLignesMusics;
    }



    public void onDestroy() {

        if (maMusique.monPlayer != null && maMusique.monPlayer.isPlaying())
        {
            maMusique.monPlayer.stop();
        }
        super.onDestroy();

    }
}

