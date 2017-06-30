package fr.developpement.remi.kumquatmusic;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        final ArrayAdapter<LigneMusic> adapter = new ArrayAdapter<LigneMusic>(MainActivity.this,android.R.layout.simple_list_item_1,LignesMusics);
        maListe.setAdapter(adapter);
        
    }
    
    private ArrayList<LigneMusic> genererLigneMusic(){
        ArrayList<LigneMusic> MesLignesMusics = new ArrayList<LigneMusic>();
        for (int i = 0; i <= maMusique.MesMusics.size(); i++){
            MesLignesMusics.add(new LigneMusic(Color.BLACK, maMusique.MesMusics.get(i)));
        }

        return MesLignesMusics;
    }
    public void onDestroy() {
        super.onDestroy();

    }
}

