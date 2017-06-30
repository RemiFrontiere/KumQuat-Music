package fr.developpement.remi.kumquatmusic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    private Music maMusique;
    private TextView montexte;
    private Button monBouton;
    private Integer num = 0;
    private Button Boutonstop;


    private MediaPlayer monMP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.maMusique = new Music();
        this.monBouton = (Button)findViewById(R.id.button2);
        this.montexte = (TextView)findViewById(R.id.textView6);
        this.Boutonstop = (Button)findViewById(R.id.button3);

        this.monBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (num <= 9)
                {
                    montexte.setText(maMusique.MesMusics.get(num));
                    String ui = montexte.getText().toString();
                    maMusique.Lire(MainActivity.this,ui);
                    num++;
                }
                else
                {
                    num = 0;
                    montexte.setText(maMusique.MesMusics.get(num));
                }

            }
        });

        this.Boutonstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(maMusique.monPlayer.isPlaying())
                   maMusique.Stop();
            }
        });

    }

    public void onDestroy() {

        if(monMP.isPlaying())
            monMP.stop();
        super.onDestroy();

    }
}
