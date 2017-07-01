package fr.developpement.remi.kumquatmusic;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Music maMusique;
    private ListView maListe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.maMusique = new Music();
        this.maListe = (ListView)findViewById(R.id.maListe);
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

                Object o = maListe.getItemAtPosition(position);
                LigneMusic UneLigne = (LigneMusic)o;
                maMusique.Lire(MainActivity.this,UneLigne.getText());

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
        super.onDestroy();

    }
}

