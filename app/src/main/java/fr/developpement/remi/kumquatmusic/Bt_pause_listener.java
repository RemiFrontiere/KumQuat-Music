package fr.developpement.remi.kumquatmusic;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by REMI on 30/07/2017.
 */

public class Bt_pause_listener extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        MainActivity.pause.performClick();

        if ((Integer) MainActivity.pause.getTag() == R.mipmap.pause) {
            if (MainActivity.maMusique.monPlayer.isPlaying()) {
                if (MainActivity.monLecteur != null) {
                    Lecteur.pause.setImageResource(R.mipmap.pause);
                    Lecteur.pause.setTag(R.mipmap.pause);
            }
            }

        } else {
            if (MainActivity.monLecteur != null) {
                Lecteur.pause.setImageResource(R.mipmap.play);
                Lecteur.pause.setTag(R.mipmap.play);
            }
        }
    }
}

