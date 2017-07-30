package fr.developpement.remi.kumquatmusic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by REMI on 30/07/2017.
 */



public class Bt_previous_listener extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            MainActivity.precedent.performClick();
            if (MainActivity.monLecteur != null) {
                if (MainActivity.positionMainActivity != true) {
                    ((Activity) MainActivity.monLecteur).finish();
                }
            }
        }
        catch (Exception e) {
            new AlertDialog.Builder(MainActivity.ici)
                    .setTitle("Erreur")
                    .setMessage("Une erreur s'est produite !")
                    .setPositiveButton("OK", null)
                    .show();
        }
    }
}