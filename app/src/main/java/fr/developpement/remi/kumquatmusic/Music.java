package fr.developpement.remi.kumquatmusic;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by REMI on 30/06/2017.
 */

public class Music {

    public ArrayList<String> MesMusics;
    public MediaPlayer monPlayer;

    public Music(){
        this.MesMusics = new ArrayList<String>();

        this.MesMusics.add("Côld vibe [TRAP]");
        this.MesMusics.add("Energized [ELECTRO]");
        this.MesMusics.add("Kinda Loud [BASSHOUSE]");
        this.MesMusics.add("The last thing I felt [DRUM N BASS]");
        this.MesMusics.add("Little spoon [MELBOURNE BOUNCE]");
        this.MesMusics.add("Too Late [TRAP]");
        this.MesMusics.add("Just in Time [BASSHOUSE]");
        this.MesMusics.add("SJFL [FUNK]");
        this.MesMusics.add("Wishes [DEEP HOUSE]");
        this.MesMusics.add("Wind&Storms [PROGRESSIVE HOUSE]");
    }

    public void Lire(Context context, String nom){

        int MP3 = 0;
        switch (nom){
            case "Côld vibe [TRAP]":
                MP3 = R.raw.coldvibe;
                break;
            case "Energized [ELECTRO]":
                MP3 = R.raw.energized;
                break;
            case "Wind&Storms [PROGRESSIVE HOUSE]":
                MP3 = R.raw.windstorms;
                break;
            case "Wishes [DEEP HOUSE]":
                MP3 = R.raw.wishes;
                break;
            case "SJFL [FUNK]":
                MP3 = R.raw.shfl;
                break;
            case "Just in Time [BASSHOUSE]":
                MP3 = R.raw.justintime;
                break;
            case "Too Late [TRAP]":
                MP3 = R.raw.toolate;
                break;
            case "Little spoon [MELBOURNE BOUNCE]":
                MP3 = R.raw.littlespoon;
                break;
            case "The last thing I felt [DRUM N BASS]":
                MP3 = R.raw.thelastthingifelt;
                break;
            case "Kinda Loud [BASSHOUSE]":
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
