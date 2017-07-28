package fr.developpement.remi.kumquatmusic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Enumeration;

import android.content.Context;
import android.graphics.Picture;
import android.media.MediaPlayer;

/**
 * Created by REMI on 30/06/2017.
 */

public class Music {

    public ArrayList<UneMusique> MesMusics;
    public MediaPlayer monPlayer;
    public ArrayList<String> TitresMusics;

    public Music(){
        this.MesMusics = new ArrayList<>();
        this.TitresMusics = new ArrayList<>();
        this.TitresMusics.add("Côld vibe");
        this.TitresMusics.add("Energized");
        this.TitresMusics.add("Kinda Loud");
        this.TitresMusics.add("The last thing I felt");
        this.TitresMusics.add("Little spoon");
        this.TitresMusics.add("Just in Time");
        this.TitresMusics.add("SJFL");
        this.TitresMusics.add("Wishes");
        this.TitresMusics.add("Wind&Storms");
        this.TitresMusics.add("Too Late");

        Collections.sort(TitresMusics);

        this.MesMusics.add(new UneMusique(TitresMusics.get(0), "2.59",R.mipmap.coldpicture));
        this.MesMusics.add(new UneMusique(TitresMusics.get(1), "3.08",R.mipmap.energipicture));
        this.MesMusics.add(new UneMusique(TitresMusics.get(2), "3.18",R.mipmap.justpicture));
        this.MesMusics.add(new UneMusique(TitresMusics.get(3), "3.35",R.mipmap.kindapicture));
        this.MesMusics.add(new UneMusique(TitresMusics.get(4), "3.13",R.mipmap.littlepicture));
        this.MesMusics.add(new UneMusique(TitresMusics.get(5), "2.32",R.mipmap.sjflpicture));
        this.MesMusics.add(new UneMusique(TitresMusics.get(6), "3.20",R.mipmap.lastpicture));
        this.MesMusics.add(new UneMusique(TitresMusics.get(7), "3.17",R.mipmap.toolatepicture));
        this.MesMusics.add(new UneMusique(TitresMusics.get(8), "3.45",R.mipmap.windpicture));
        this.MesMusics.add(new UneMusique(TitresMusics.get(9), "3.05",R.mipmap.wishespicture));


    }

    public void Lire(Context context, String nom){

        int MP3 = 0;
        switch (nom){
            case "Côld vibe":
                MP3 = R.raw.coldvibe;
                break;
            case "Energized":
                MP3 = R.raw.energized;
                break;
            case "Wind&Storms":
                MP3 = R.raw.windstorms;
                break;
            case "Wishes":
                MP3 = R.raw.wishes;
                break;
            case "SJFL":
                MP3 = R.raw.shfl;
                break;
            case "Just in Time":
                MP3 = R.raw.justintime;
                break;
            case "Too Late":
                MP3 = R.raw.toolate;
                break;
            case "Little spoon":
                MP3 = R.raw.littlespoon;
                break;
            case "The last thing I felt":
                MP3 = R.raw.thelastthingifelt;
                break;
            case "Kinda Loud":
                MP3 = R.raw.kindaloud;
                break;
        }
        monPlayer = MediaPlayer.create(context,MP3);
        monPlayer.start();
    }

    public void Stop(){
        monPlayer.stop();
    }
}

class UneMusique{

    public String leTitre;
    public  String laDuree;
    public int uneImg;

    public UneMusique(String titre, String duree, int img){
        this.laDuree = duree;
        this.leTitre = titre;
        this.uneImg = img;
    }


}
