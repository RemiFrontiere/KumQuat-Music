package fr.developpement.remi.kumquatmusic;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

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
    private NotificationManager notif;


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



        ArrayList<LigneMusic> LignesMusics = genererLigneMusic();
        final MusicAdapter adapter = new MusicAdapter(MainActivity.this,LignesMusics);
        maListe.setAdapter(adapter);

        maListe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
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
                adapter.couleur = Color.parseColor("#42A5F5");
                adapter.titreLigne = ((LigneMusic) o).getText();
                adapter.img = ((LigneMusic) o).getImg();
                maListe.setAdapter(adapter);

                maMusique.Lire(MainActivity.this,UneLigne.getText());

                createNotification(adapter.titreLigne,adapter.img);

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
                    if(maMusique.MesMusics.get(i).leTitre == adapter.titreLigne) {
                        index = i - 1;
                        if(index < 0)
                            index = maMusique.MesMusics.size() - 1;
                        break;
                    }
                }

                LigneMusic UneLigne = new LigneMusic(maMusique.MesMusics.get(index).uneImg, maMusique.MesMusics.get(index).leTitre, maMusique.MesMusics.get(index).laDuree);
                adapter.couleur = Color.parseColor("#42A5F5");
                adapter.titreLigne = maMusique.MesMusics.get(index).leTitre;
                adapter.img = maMusique.MesMusics.get(index).uneImg;
                maListe.setAdapter(adapter);

                maMusique.Lire(MainActivity.this,UneLigne.getText());

                createNotification(adapter.titreLigne,adapter.img);
            }
        });

        suivant.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                pause.setImageResource(R.mipmap.pause);
                pause.setTag(R.mipmap.pause);

                if (maMusique.monPlayer.isPlaying())
                    maMusique.monPlayer.stop();

                int index = 0;
                for(int i = 0; i < maMusique.MesMusics.size(); i++)
                {
                    if(maMusique.MesMusics.get(i).leTitre == adapter.titreLigne) {
                        index = i + 1;
                        if(index > maMusique.MesMusics.size() - 1)
                            index = 0;
                        break;
                    }
                }

                LigneMusic UneLigne = new LigneMusic(maMusique.MesMusics.get(index).uneImg, maMusique.MesMusics.get(index).leTitre, maMusique.MesMusics.get(index).laDuree);
                adapter.couleur = Color.parseColor("#42A5F5");
                adapter.titreLigne = maMusique.MesMusics.get(index).leTitre;
                adapter.img = maMusique.MesMusics.get(index).uneImg;
                maListe.setAdapter(adapter);

                maMusique.Lire(MainActivity.this,UneLigne.getText());

                createNotification(adapter.titreLigne,adapter.img);
            }
        });

    }

    private final void createNotification(String text, int img){
        notif = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        final Intent launchNotifiactionIntent = new Intent(this, MainActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this,REQUEST_CODE, launchNotifiactionIntent,
                PendingIntent.FLAG_ONE_SHOT);

        Notification.Builder builder = new Notification.Builder(this)
                .setWhen(System.currentTimeMillis())
                .setTicker("BEBEB")
                .setSmallIcon(R.mipmap.kqt)
                .setContentTitle(text)
                .setContentText(null)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
               // .addAction(R.mipmap.previous, null,
               //         PendingIntent.getActivity(getApplicationContext(), 0,getIntent(), 0, null))
             //   .addAction(R.mipmap.pause, null,
              //          PendingIntent.getActivity(getApplicationContext(), 0,getIntent(), 0, null))
              //  .addAction(R.mipmap.next, null,
              //          PendingIntent.getActivity(getApplicationContext(), 0,getIntent(), 0, null))
                ;

        Notification notification = new Notification.BigPictureStyle(builder)
                .bigPicture(BitmapFactory.decodeResource(getResources(),img)).build();


        notification.color = this.getResources()
                .getColor(R.color.background);
       // notification.flags = Notification.FLAG_ONGOING_EVENT;
        notification.priority = Notification.PRIORITY_MAX;
        notif.notify(NOTIFICATION_ID, notification);
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
        notif.cancelAll();
        super.onDestroy();

    }
}

