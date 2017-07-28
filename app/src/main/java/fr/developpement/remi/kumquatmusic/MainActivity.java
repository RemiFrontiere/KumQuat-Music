package fr.developpement.remi.kumquatmusic;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 0;
    private static final int REQUEST_CODE = 0;
    private Music maMusique;
    private ListView maListe;
    private ImageButton precedent;
    private ImageButton suivant;
    private ImageButton pause;
    private View bas;
    private static MusicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.maMusique = new Music();
        this.maListe = (ListView)findViewById(R.id.maListe);

        this.precedent = (ImageButton)findViewById(R.id.btPrecedent) ;
        this.suivant = (ImageButton)findViewById(R.id.btSuivant) ;
        this.pause = (ImageButton)findViewById(R.id.btPause) ;
        this.bas = findViewById(R.id.LayoutBas);
        this.bas.setVisibility(View.INVISIBLE);
        this.precedent.setTag(R.mipmap.previous);
        this.suivant.setTag(R.mipmap.next);
        this.pause.setTag(R.mipmap.pause);


        createNotification();
        ArrayList<LigneMusic> LignesMusics = genererLigneMusic();
        setAdapter(new MusicAdapter(MainActivity.this,LignesMusics));
        maListe.setAdapter(getAdapter());

        maListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bas.setVisibility(View.VISIBLE);
                pause.setImageResource(R.mipmap.pause);
                pause.setTag(R.mipmap.pause);
                if (maMusique.monPlayer != null && maMusique.monPlayer.isPlaying())
                {
                    maMusique.monPlayer.stop();
                }

                // #2196F3
                Object o = maListe.getItemAtPosition(position);
                LigneMusic UneLigne = (LigneMusic)o;
                getAdapter().couleur = Color.parseColor("#42A5F5");
                getAdapter().titreLigne = ((LigneMusic) o).getText();
                getAdapter().img = ((LigneMusic) o).getImg();
                maListe.setAdapter(getAdapter());

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


        precedent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pause.setImageResource(R.mipmap.pause);
                pause.setTag(R.mipmap.pause);

                if (maMusique.monPlayer.isPlaying())
                    maMusique.monPlayer.stop();

                int index = 0;
                for(int i = 0; i < maMusique.MesMusics.size(); i++)
                {
                    if(maMusique.MesMusics.get(i).leTitre == getAdapter().titreLigne) {
                        index = i - 1;
                        if(index < 0)
                            index = maMusique.MesMusics.size() - 1;
                        break;
                    }
                }

                LigneMusic UneLigne = new LigneMusic(maMusique.MesMusics.get(index).uneImg, maMusique.MesMusics.get(index).leTitre, maMusique.MesMusics.get(index).laDuree);
                getAdapter().couleur = Color.parseColor("#42A5F5");
                getAdapter().titreLigne = maMusique.MesMusics.get(index).leTitre;
                maListe.setAdapter(getAdapter());

                maMusique.Lire(MainActivity.this,UneLigne.getText());
            }
        });

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause.setImageResource(R.mipmap.pause);
                pause.setTag(R.mipmap.pause);

                if (maMusique.monPlayer.isPlaying())
                    maMusique.monPlayer.stop();

                int index = 0;
                for(int i = 0; i < maMusique.MesMusics.size(); i++)
                {
                    if(maMusique.MesMusics.get(i).leTitre == getAdapter().titreLigne) {
                        index = i + 1;
                        if(index > maMusique.MesMusics.size() - 1)
                            index = 0;
                        break;
                    }
                }

                LigneMusic UneLigne = new LigneMusic(maMusique.MesMusics.get(index).uneImg, maMusique.MesMusics.get(index).leTitre, maMusique.MesMusics.get(index).laDuree);
                getAdapter().couleur = Color.parseColor("#42A5F5");
                getAdapter().titreLigne = maMusique.MesMusics.get(index).leTitre;
                maListe.setAdapter(getAdapter());

                maMusique.Lire(MainActivity.this,UneLigne.getText());
            }
        });

    }

    private final void createNotification(){
        final NotificationManager mNotification = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        final Intent launchNotifiactionIntent = new Intent(this, MainActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this,REQUEST_CODE, launchNotifiactionIntent,
                PendingIntent.FLAG_ONE_SHOT);

        Notification.Builder builder = new Notification.Builder(this)
                .setWhen(System.currentTimeMillis())
                .setTicker("BEBEB")
                .setSmallIcon(R.mipmap.kqt)
                .setContentTitle(getResources().getString(R.string.notification_title))
                .setContentText(getResources().getString(R.string.notification_desc))
                .setContentIntent(pendingIntent);

        mNotification.notify(NOTIFICATION_ID, builder.build());
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

    // pour passer sur le lecteur
    public void page1 (View view) {
        startActivity(new Intent(this, Lecteur.class));
    }


    public static MusicAdapter getAdapter() {
        return MainActivity.adapter;
    }

    public static void setAdapter(MusicAdapter adapter) {
        MainActivity.adapter = adapter;
    }
}

