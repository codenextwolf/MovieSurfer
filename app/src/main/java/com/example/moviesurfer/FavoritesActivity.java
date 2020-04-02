package com.example.moviesurfer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        //Get a reference to the SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        //Get the favorites and convert to an array
        String favorites = sharedPreferences.getString("favorites", "");
        String[] favoritesArray = favorites.split(",");

        FavoritesAdapter favoritesAdapter = new FavoritesAdapter(this, favoritesArray);
        ListView favoritesListView = findViewById(R.id.fav_list_view);
        favoritesListView.setAdapter(favoritesAdapter);
    }
}
