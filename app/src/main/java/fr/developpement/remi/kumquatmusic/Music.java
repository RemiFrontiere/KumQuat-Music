package fr.developpement.remi.kumquatmusic;

import java.util.ArrayList;
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

    public Music(){
        this.MesMusics = new ArrayList<UneMusique>();

        this.MesMusics.add(new UneMusique("Côld vibe", "2.58",R.mipmap.coldpicture));
        this.MesMusics.add(new UneMusique("Energized", "3.08",R.mipmap.energipicture));
        this.MesMusics.add(new UneMusique("Kinda Loud", "3.35",R.mipmap.kindapicture));
        this.MesMusics.add(new UneMusique("The last thing I felt", "3.20",R.mipmap.lastpicture));
        this.MesMusics.add(new UneMusique("Little spoon", "3.13",R.mipmap.littlepicture));
        this.MesMusics.add(new UneMusique("Too Late", "3.17",R.mipmap.toolatepicture));
        this.MesMusics.add(new UneMusique("Just in Time", "3.18",R.mipmap.justpicture));
        this.MesMusics.add(new UneMusique("SJFL", "2.32",R.mipmap.sjflpicture));
        this.MesMusics.add(new UneMusique("Wishes", "3.05",R.mipmap.wishespicture));
        this.MesMusics.add(new UneMusique("Wind&Storms", "3.45",R.mipmap.windpicture));
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
