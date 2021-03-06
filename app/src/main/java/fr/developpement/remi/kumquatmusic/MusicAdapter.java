package fr.developpement.remi.kumquatmusic;

/**
 * Created by REMI on 30/06/2017.
 */

import android.graphics.Color;
import android.graphics.drawable.InsetDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Context;
import android.widget.ListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;

import java.util.ArrayList;


class LigneMusic {
    private int img;
    private String text;
    private String duree;
    private String youtube;


    public LigneMusic(int color, String text, String duree, String youtube) {
        this.img = color;
        this.text = text;
        this.duree = duree;
        this.youtube = youtube;
    }

    public String getText()
    {
        return this.text;
    }
    public int getImg()
    {
        return this.img;
    }
    public String getDuree()
    {
        return this.duree;
    }
    public String getYoutube()
    {
        return this.youtube;
    }

}

public class MusicAdapter extends ArrayAdapter<LigneMusic> {

    public int couleur = Color.WHITE;
    public String titreLigne;
    public int img;
    public String youtube;

    //tweets est la liste des models à afficher
    public MusicAdapter(Context context, ArrayList<LigneMusic> lignesmusics) {
        super(context, 0, lignesmusics);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_music,parent, false);
        }

        MusicViewHolder viewHolder = (MusicViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new MusicViewHolder();
            viewHolder.titre = (TextView) convertView.findViewById(R.id.text);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            viewHolder.duree = (TextView) convertView.findViewById(R.id.duree);

        /*    new AlertDialog.Builder(getContext())
                    .setTitle("OK")
                    .setMessage(viewHolder.titre.getText())
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setNegativeButton("No", null).show();*/


            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        LigneMusic ligneMusic = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.titre.setText(ligneMusic.getText());
        viewHolder.duree.setText(ligneMusic.getDuree());
        viewHolder.avatar.setImageResource(ligneMusic.getImg());
        viewHolder.titre.setTextColor(Color.WHITE);
        viewHolder.duree.setTextColor(Color.WHITE);
        if (titreLigne!= null && viewHolder.titre.getText() == titreLigne)
        {
            viewHolder.titre.setTextColor(couleur);
            viewHolder.duree.setTextColor(couleur);
        }


        return convertView;
    }

    private class MusicViewHolder{
        public TextView titre;
        public ImageView avatar;
        public TextView duree;
    }
}