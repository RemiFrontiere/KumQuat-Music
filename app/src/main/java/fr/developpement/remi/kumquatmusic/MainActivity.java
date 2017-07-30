package fr.developpement.remi.kumquatmusic;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 0;
    private static final int REQUEST_CODE = 0;
    public static Music maMusique;
    private ListView maListe;
    public static ImageButton precedent;
    public static ImageButton suivant;
    public static ImageButton pause;
    private View bas;
    private NotificationManager notif;
    private ImageButton youtube;
    private ImageButton lecteur;
    private static MusicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            this.maMusique = new Music();
            this.maListe = (ListView) findViewById(R.id.maListe);

            this.precedent = (ImageButton) findViewById(R.id.btPrecedent);
            this.suivant = (ImageButton) findViewById(R.id.btSuivant);
            this.pause = (ImageButton) findViewById(R.id.btPause);
            this.bas = findViewById(R.id.LayoutBas);
            this.bas.setVisibility(View.INVISIBLE);
            this.lecteur = (ImageButton) findViewById(R.id.btLecteur);

            this.precedent.setTag(R.mipmap.previous);
            this.suivant.setTag(R.mipmap.next);
            this.pause.setTag(R.mipmap.pause);
            this.youtube = (ImageButton) findViewById((R.id.btYoutube));


            ArrayList<LigneMusic> LignesMusics = genererLigneMusic();
            setAdapter(new MusicAdapter(MainActivity.this, LignesMusics));
            maListe.setAdapter(getAdapter());

            maListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (bas.getVisibility() == View.INVISIBLE) {
                        bas.setVisibility(View.VISIBLE);
                        maListe.setPadding(0, 0, 0, 150);
                    }

                    Object o = maListe.getItemAtPosition(position);
                    LigneMusic UneLigne = (LigneMusic) o;

                    if (adapter.titreLigne != ((LigneMusic) o).getText()) {
                        pause.setImageResource(R.mipmap.pause);
                        pause.setTag(R.mipmap.pause);
                        if (maMusique.monPlayer != null && maMusique.monPlayer.isPlaying()) {
                            maMusique.monPlayer.stop();
                        }


                        adapter.couleur = Color.parseColor("#42A5F5");
                        adapter.titreLigne = ((LigneMusic) o).getText();
                        adapter.youtube = ((LigneMusic) o).getYoutube();
                        adapter.img = ((LigneMusic) o).getImg();
                        maListe.setAdapter(adapter);

                        maMusique.Lire(MainActivity.this, UneLigne.getText());

                        createNotification(adapter.titreLigne, adapter.img);
                    } else
                        startActivity(new Intent(MainActivity.this, Lecteur.class));
                }
            });

            pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if ((Integer) pause.getTag() == R.mipmap.pause) {
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

            lecteur.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, Lecteur.class));
                }

            });

            youtube.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent webOpen = new Intent(android.content.Intent.ACTION_VIEW);
                    webOpen.setData(Uri.parse("https://www.youtube.com/channel/UCMFaOzZvLl-fMfU0FET7tmw"));
                    startActivity(webOpen);
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
                    for (int i = 0; i < maMusique.MesMusics.size(); i++) {
                        if (maMusique.MesMusics.get(i).leTitre == getAdapter().titreLigne) {
                            index = i - 1;
                            if (index < 0)
                                index = maMusique.MesMusics.size() - 1;
                            break;
                        }
                    }

                    LigneMusic UneLigne = new LigneMusic(maMusique.MesMusics.get(index).uneImg, maMusique.MesMusics.get(index).leTitre, maMusique.MesMusics.get(index).laDuree, maMusique.MesMusics.get(index).adresseYoutube);
                    adapter.couleur = Color.parseColor("#42A5F5");
                    adapter.titreLigne = maMusique.MesMusics.get(index).leTitre;
                    adapter.youtube = maMusique.MesMusics.get(index).adresseYoutube;
                    adapter.img = maMusique.MesMusics.get(index).uneImg;
                    maListe.setAdapter(adapter);
                    maMusique.Lire(MainActivity.this, UneLigne.getText());

                    createNotification(adapter.titreLigne, adapter.img);

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
                    for (int i = 0; i < maMusique.MesMusics.size(); i++) {
                        if (maMusique.MesMusics.get(i).leTitre == getAdapter().titreLigne) {
                            index = i + 1;
                            if (index > maMusique.MesMusics.size() - 1)
                                index = 0;
                            break;
                        }
                    }

                    LigneMusic UneLigne = new LigneMusic(maMusique.MesMusics.get(index).uneImg, maMusique.MesMusics.get(index).leTitre, maMusique.MesMusics.get(index).laDuree, maMusique.MesMusics.get(index).adresseYoutube);
                    adapter.couleur = Color.parseColor("#42A5F5");
                    adapter.titreLigne = maMusique.MesMusics.get(index).leTitre;
                    adapter.img = maMusique.MesMusics.get(index).uneImg;
                    adapter.youtube = maMusique.MesMusics.get(index).adresseYoutube;
                    maListe.setAdapter(adapter);

                    maMusique.Lire(MainActivity.this, UneLigne.getText());

                    createNotification(adapter.titreLigne, adapter.img);
                }
            });
        }
        catch (Exception e) {
            new AlertDialog.Builder(this)
                    .setTitle("Erreur")
                    .setMessage("Une erreur s'est produite !")
                    .setPositiveButton("OK", null)
                    .show();
        }
    }


    private final void createNotification(String text, int img){
        try {
            notif = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            final Intent launchNotifiactionIntent = new Intent(this, MainActivity.class);
            final PendingIntent pendingIntent = PendingIntent.getActivity(this, REQUEST_CODE, launchNotifiactionIntent,
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
                    .bigPicture(BitmapFactory.decodeResource(getResources(), img)).build();


            notification.color = this.getResources()
                    .getColor(R.color.background);
            // notification.flags = Notification.FLAG_ONGOING_EVENT;
            notification.priority = Notification.PRIORITY_MAX;
            notif.notify(NOTIFICATION_ID, notification);
        }
        catch (Exception e)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Erreur")
                    .setMessage("Une erreur s'est produite !")
                    .setPositiveButton("OK", null)
                    .show();
        }
    }

    private ArrayList<LigneMusic> genererLigneMusic(){
        ArrayList<LigneMusic> MesLignesMusics = new ArrayList<LigneMusic>();
        for (int i = 0; i < maMusique.MesMusics.size(); i++){
            MesLignesMusics.add(new LigneMusic(maMusique.MesMusics.get(i).uneImg,maMusique.MesMusics.get(i).leTitre,maMusique.MesMusics.get(i).laDuree,maMusique.MesMusics.get(i).adresseYoutube));
        }

        return MesLignesMusics;
    }



    public void onDestroy() {

        try {
            if (maMusique.monPlayer != null && maMusique.monPlayer.isPlaying()) {
                maMusique.monPlayer.stop();
            }
            notif.cancelAll();
            super.onDestroy();
        }
        catch (Exception e)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Erreur")
                    .setMessage("Une erreur s'est produite !")
                    .setPositiveButton("OK", null)
                    .show();
        }
    }



    public static MusicAdapter getAdapter() {
        return MainActivity.adapter;
    }

    public static void setAdapter(MusicAdapter adapter) {
        MainActivity.adapter = adapter;
    }
}

