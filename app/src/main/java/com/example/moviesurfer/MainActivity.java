package com.example.moviesurfer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    EditText searchBar;
    TextView plotTextView;
    TextView ratedTextView;
    TextView runtimeTextView;
    TextView imdbRatingTextView;
    ImageView moviePosterImageView;
    String moviePosterUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBar = findViewById(R.id.search_bar);
        plotTextView = findViewById(R.id.plot_tv);
        ratedTextView = findViewById(R.id.rated_tv);
        runtimeTextView = findViewById(R.id.runtime_tv);
        imdbRatingTextView = findViewById(R.id.imdb_rating_tv);
        moviePosterImageView = findViewById(R.id.movie_poster_iv);
    }


    public void fetchData(View view) {

        String movieSearched = searchBar.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.omdbapi.com/?apikey=388d5f18&t=" + movieSearched;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseObject = new JSONObject(response);
                            String plotText = responseObject.getString("Plot");
                            plotTextView.setText(plotText);
                            String ratedText = "Rated: " + responseObject.getString("Rated");
                            ratedTextView.setText(ratedText);
                            String runtimeText = "Runtime: " + responseObject.getString("Runtime");
                            runtimeTextView.setText(runtimeText);
                            String imdbRatingText = "IMDB Rating: " + responseObject.getString("imdbRating");
                            imdbRatingTextView.setText(imdbRatingText);
                            moviePosterUrl = responseObject.getString("Poster");
                            Picasso.get().load(moviePosterUrl).into(moviePosterImageView);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }


    public void addToFavs(View view) {
        //Get a reference to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        //Get the favorites string from SharedPreferences (or an empty string if there are no favorites saved)
        String favorites = sharedPreferences.getString("favorites", "");

        //Get a reference to edit so we can update the favorites string
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Add the current movie poster URL to the favorites
        //if there are favorites, concatenate the new poster URL being added and separate it with a comma
        //if not, just add the moviePoster URL
        if(favorites.length() > 0) {
            editor.putString("favorites", favorites + "," + moviePosterUrl);
        } else {
            editor.putString("favorites", moviePosterUrl);
        }

        //apply the changes to SharedPreferences
        editor.apply();
    }

    public void openFavorites(View view) {
        Intent intentToFavorites = new Intent(this, FavoritesActivity.class);
        startActivity(intentToFavorites);
    }
}
