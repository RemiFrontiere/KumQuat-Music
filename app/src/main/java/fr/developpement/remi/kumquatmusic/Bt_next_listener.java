package fr.developpement.remi.kumquatmusic;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by REMI on 30/07/2017.
 */

public class Bt_next_listener extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        MainActivity.suivant.performClick();
        if(MainActivity.monLecteur != null) {
            ((Activity) MainActivity.monLecteur).finish();
            MainActivity.monLecteur.startActivity(((Activity) MainActivity.monLecteur).getIntent());
        }
    }
}

