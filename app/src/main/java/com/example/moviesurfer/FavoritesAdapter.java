package com.example.moviesurfer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

public class FavoritesAdapter extends ArrayAdapter<String> {

    public FavoritesAdapter(@NonNull Context context, String[] arr) {
        super(context, 0, arr);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.favorite_list_item, parent, false);
        String currentMovieUrl = getItem(position);
        ImageView favoriteMovieImageView = convertView.findViewById(R.id.fav_movie_iv);
        Picasso.get().load(currentMovieUrl).into(favoriteMovieImageView);
        return convertView;
    }

}
