package fr.developpement.remi.kumquatmusic;

/**
 * Created by REMI on 30/06/2017.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private int color;
    private String text;

    public LigneMusic(int color, String text) {
        this.color = color;
        this.text = text;
    }

    public String getText()
    {
        return this.text;
    }
    public int getColor()
    {
        return this.color;
    }

}

public class MusicAdapter extends ArrayAdapter<LigneMusic> {

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
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        LigneMusic ligneMusic = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.titre.setText(ligneMusic.getText());
        viewHolder.avatar.setImageDrawable(new ColorDrawable(ligneMusic.getColor()));

        return convertView;
    }

    private class MusicViewHolder{
        public TextView titre;
        public ImageView avatar;
    }
}