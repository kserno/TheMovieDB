package com.kserno.themoviedb.objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by filipsollar on 4.10.17.
 */

public class Movie implements Serializable {

    private boolean adult;

    @SerializedName("backdrop_path")
    private String backdropPath;

    private int budget;
    private Genre[] genres;
    private String homepage;
    private int id;

    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("official_language")
    private String officialLanguage;

    @SerializedName("official_title")
    private String officialTitle;

    private String overview;
    private double popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("production_companies")
    private Company[] productionCompanies;

    @SerializedName("production_countries")
    private Country[] productionCountries;

    @SerializedName("release_date")
    private String releaseDate;

    private int revenue;
    private int runtime;
    private Language[] spokenLanguages;

    private String status;
    private String tagline;
    private String title;
    private boolean video;
    @SerializedName("vote_average")
    private double voteAverage;
    @SerializedName("vote_count")
    private double voteCount;

    public Movie() {
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public int getBudget() {
        return budget;
    }

    public Genre[] getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getId() {
        return id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getOfficialLanguage() {
        return officialLanguage;
    }

    public String getOfficialTitle() {
        return officialTitle;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public Company[] getProductionCompanies() {
        return productionCompanies;
    }

    public Country[] getProductionCountries() {
        return productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getRevenue() {
        return revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public Language[] getSpokenLanguages() {
        return spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public double getVoteCount() {
        return voteCount;
    }

    public void fillMovie(Movie movie) {
        backdropPath = movie.getBackdropPath();
        budget = movie.getBudget();
        if (movie.getGenres() != null) {
            genres = movie.getGenres().clone();
        }

        homepage = movie.getHomepage();
        imdbId = movie.getImdbId();
        officialLanguage = movie.getOfficialLanguage();
        officialTitle = movie.getOfficialTitle();
        overview = movie.getOverview();
        popularity = movie.getPopularity();
        posterPath = movie.getPosterPath();
        if (movie.getProductionCompanies() != null) {
            productionCompanies = movie.getProductionCompanies().clone();
        }

        if (movie.getProductionCountries() != null) {
            productionCountries = movie.getProductionCountries().clone();

        }
        releaseDate = movie.getReleaseDate();
        revenue = movie.getRevenue();
        runtime = movie.getRuntime();

        if (movie.getSpokenLanguages() != null) {
            spokenLanguages = movie.getSpokenLanguages().clone();
        }

        status = movie.getStatus();
        tagline = movie.getTagline();
        title = movie.getTitle();
        video = movie.isVideo();
        voteAverage = movie.getVoteAverage();
        voteCount = movie.getVoteCount();
    }
}
