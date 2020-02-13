package com.example.musiclist.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musiclist.Model.Songs;
import com.example.musiclist.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SongListAdapter extends ArrayAdapter<Songs> {
    ArrayList<Songs> songs;
    Context context;
    int resource;
    public SongListAdapter(Context context, int resource, ArrayList<Songs> songs) {
        super(context, resource, songs);
        this.context = context;
        this.resource = resource;
        this.songs = songs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.song_list_item, null, true);

        }
        Songs songs = getItem(position);

        ImageView imageView = convertView.findViewById(R.id.songTile);
        Picasso.with(context).load(songs.getImage()).into(imageView);

        TextView trackName = convertView.findViewById(R.id.songName);
        trackName.setText(songs.getSongName());

        TextView collectionName = (TextView) convertView.findViewById(R.id.albumName);
        collectionName.setText(songs.getAlbumName());

        TextView artistName = (TextView) convertView.findViewById(R.id.singerName);
        artistName.setText(songs.getArtistName());
        return convertView;
    }
}
