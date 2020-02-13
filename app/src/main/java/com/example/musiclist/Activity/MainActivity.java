package com.example.musiclist.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musiclist.Adapter.SongListAdapter;
import com.example.musiclist.Constant.Constant;
import com.example.musiclist.Model.Songs;
import com.example.musiclist.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Songs> songList;
    ListView listView;
    JSONObject songsDetailObject;
    JSONObject jsonObject;
    private JSONArray jsonArray;
    private String wrapperType;
    private String kind;
    private String artistId;
    private String artistName;
    private String collectionName;
    private String trackCensoredName;
    private String releaseDate;
    private String country;
    private String currency;
    private String trackName;

    private static String getUrl(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setProgressBarIndeterminateVisibility(true);
        setContentView(R.layout.activity_main);
        songList = new ArrayList<>();
        listView = findViewById(R.id.list_view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getData(position);

            }
        });


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new SongListData().execute(Constant.URL);

            }
        });
    }

    private void getData(int position) {
        try {
            jsonArray = jsonObject.getJSONArray("results");
            songsDetailObject = jsonArray.getJSONObject(position);
            kind = songsDetailObject.getString("kind");
            wrapperType = songsDetailObject.getString("wrapperType");
            trackName = songsDetailObject.getString("trackName");
            artistId = songsDetailObject.getString("artistId");
            artistName = songsDetailObject.getString("artistName");
            collectionName = songsDetailObject.getString("collectionName");
            trackCensoredName = songsDetailObject.getString("trackCensoredName");
            releaseDate = songsDetailObject.getString("releaseDate");
            country = songsDetailObject.getString("country");
            currency = songsDetailObject.getString("currency");
            Intent intent = new Intent(MainActivity.this, SongDetailsActivity.class);
            intent.putExtra("kind", kind);
            intent.putExtra("wrapperType", wrapperType);
            intent.putExtra("trackName", trackName);
            intent.putExtra("artistId", artistId);
            intent.putExtra("artistName", artistName);
            intent.putExtra("collectionName", collectionName);
            intent.putExtra("trackCensoredName", trackCensoredName);
            intent.putExtra("releaseDate", releaseDate);
            intent.putExtra("country", country);
            intent.putExtra("currency", currency);
            startActivity(intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class SongListData extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return getUrl(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                jsonObject = new JSONObject(content);
                jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    songsDetailObject = jsonArray.getJSONObject(i);
                    songList.add(new Songs(
                            songsDetailObject.getString("artworkUrl100"),
                            songsDetailObject.getString("trackName"),
                            songsDetailObject.getString("artistName"),
                            songsDetailObject.getString("collectionName")
                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            SongListAdapter adapter = new SongListAdapter(
                    getApplicationContext(), R.layout.song_list_item, songList
            );
            setProgressBarIndeterminateVisibility(false);
            listView.setAdapter(adapter);
        }
    }
}
