package com.example.moviesurfer;

public class Movie {

    String movieTitle;
    String moviePosterUrl;

    public Movie(String movieTitle, String moviePosterUrl) {
        this.movieTitle = movieTitle;
        this.moviePosterUrl = moviePosterUrl;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMoviePosterUrl() {
        return moviePosterUrl;
    }
}
