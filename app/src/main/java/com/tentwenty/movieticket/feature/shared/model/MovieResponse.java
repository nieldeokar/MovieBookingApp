package com.tentwenty.movieticket.feature.shared.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
  @SerializedName("results") private List<Movie> movies;

  public List<Movie> getMovies() {
    return movies;
  }
}
