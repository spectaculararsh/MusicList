package com.example.musiclist.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musiclist.R;

public class SongDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_details);
        TextView kind = findViewById(R.id.kind);
        TextView wrappertype = findViewById(R.id.wrapperType);
        TextView currency = findViewById(R.id.currency);
        TextView country = findViewById(R.id.country);
        TextView collectionname = findViewById(R.id.collectionname);
        TextView artistid = findViewById(R.id.artistid);
        TextView artistname = findViewById(R.id.artistname);
        TextView trackcensoredname = findViewById(R.id.trackcensoredname);
        TextView releasedate = findViewById(R.id.releasedate);
        TextView trackname = findViewById(R.id.trackname);
        kind.setText("Kind :  " + getIntent().getExtras().getString("kind"));
        wrappertype.setText("Wrapper Type :  " + getIntent().getExtras().getString("wrapperType"));
        artistname.setText("Artist Name :  " + getIntent().getExtras().getString("artistName"));
        artistid.setText("Artist Id :  " + getIntent().getExtras().getString("artistId"));
        collectionname.setText("Collection Name :  " + getIntent().getExtras().getString("collectionName"));
        trackname.setText("Track Name :  " + getIntent().getExtras().getString("trackName"));
        releasedate.setText("Release Date :  " + getIntent().getExtras().getString("releaseDate"));
        trackcensoredname.setText("Track Censored Name :  " + getIntent().getExtras().getString("trackCensoredName"));
        currency.setText("Currency :  " + getIntent().getExtras().getString("currency"));
        country.setText("Country :  " + getIntent().getExtras().getString("country"));
    }
}
