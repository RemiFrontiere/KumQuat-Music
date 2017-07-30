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
        this.TitresMusics.add("Dreaming Star");

        Collections.sort(TitresMusics);

        this.MesMusics.add(new UneMusique(TitresMusics.get(0), "2.59",R.mipmap.coldpicture,"https://www.youtube.com/watch?v=ZSOQ6dZDQ7U" ));
        this.MesMusics.add(new UneMusique(TitresMusics.get(1), "2.34",R.mipmap.dreamstar,"https://www.youtube.com/watch?v=VYGiQMHetFo&t=6s"));
        this.MesMusics.add(new UneMusique(TitresMusics.get(2), "3.08",R.mipmap.energipicture,"https://www.youtube.com/watch?v=4nYidtMvPoM"));
        this.MesMusics.add(new UneMusique(TitresMusics.get(3), "3.18",R.mipmap.justpicture, "https://www.youtube.com/watch?v=r_tfLuKSI8U"));
        this.MesMusics.add(new UneMusique(TitresMusics.get(4), "3.35",R.mipmap.kindapicture,"https://www.youtube.com/watch?v=e5omNXCwzOQ"));
        this.MesMusics.add(new UneMusique(TitresMusics.get(5), "3.13",R.mipmap.littlepicture,"https://www.youtube.com/watch?v=02CP9uHZgNA"));
        this.MesMusics.add(new UneMusique(TitresMusics.get(6), "2.32",R.mipmap.sjflpicture,"https://www.youtube.com/watch?v=6p8BRPwpKfw"));
        this.MesMusics.add(new UneMusique(TitresMusics.get(7), "3.20",R.mipmap.lastpicture,"https://www.youtube.com/watch?v=EmLZgSPDM3c"));
        this.MesMusics.add(new UneMusique(TitresMusics.get(8), "3.17",R.mipmap.toolatepicture,"https://www.youtube.com/watch?v=_8QDDCH0-To"));
        this.MesMusics.add(new UneMusique(TitresMusics.get(9), "3.45",R.mipmap.windpicture,"https://www.youtube.com/watch?v=YD5RDC04amQ"));
        this.MesMusics.add(new UneMusique(TitresMusics.get(10), "3.05",R.mipmap.wishespicture,"https://www.youtube.com/watch?v=UzU6f1xLq0U"));


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
            case "Dreaming Star":
                MP3 = R.raw.dreamingstar;
                break;
        }
        monPlayer = MediaPlayer.create(context,MP3);
        monPlayer.start();
    }

}

class UneMusique{

    public String leTitre;
    public  String laDuree;
    public int uneImg;
    public String adresseYoutube;

    public UneMusique(String titre, String duree, int img, String youtube){
        this.laDuree = duree;
        this.leTitre = titre;
        this.uneImg = img;
        this.adresseYoutube = youtube;
    }


}
