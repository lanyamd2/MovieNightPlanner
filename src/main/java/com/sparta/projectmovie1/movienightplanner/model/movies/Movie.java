package com.sparta.projectmovie1.movienightplanner.model.movies;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparta.projectmovie1.movienightplanner.model.Production;
import com.sparta.projectmovie1.movienightplanner.model.SpokenLanguage;

public class Movie extends Production {

	@JsonProperty("overview")
	private String overview;

	@JsonProperty("original_language")
	private String originalLanguage;

	@JsonProperty("original_title")
	private String originalTitle;

	@JsonProperty("imdb_id")
	private String imdbId;

	@JsonProperty("runtime")
	private int runtime;

//	@JsonProperty("title")
//	private String title;

	@JsonProperty("poster_path")
	private String posterPath;

	@JsonProperty("spoken_languages")
	private List<SpokenLanguage> spokenLanguages;

	@JsonProperty("release_date")
	private String releaseDate;

	@JsonProperty("popularity")
	private Object popularity;

	@JsonProperty("vote_average")
	private Object voteAverage;

	@JsonProperty("production_countries")
	private List<ProductionCountry> productionCountries;

	@JsonProperty("tagline")
	private String tagline;

	@JsonProperty("vote_count")
	private int voteCount;

	@JsonProperty("status")
	private String status;




	public void setOverview(String overview){
		this.overview = overview;
	}

	public String getOverview(){
		return overview;
	}

	public void setOriginalLanguage(String originalLanguage){
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public void setOriginalTitle(String originalTitle){
		this.originalTitle = originalTitle;
	}

	public String getOriginalTitle(){
		return originalTitle;
	}

	public void setImdbId(String imdbId){
		this.imdbId = imdbId;
	}

	public String getImdbId(){
		return imdbId;
	}

	public void setRuntime(int runtime){
		this.runtime = runtime;
	}

	public int getRuntime(){
		return runtime;
	}

//	public void setTitle(String title){
//		this.title = title;
//	}
//
//	public String getTitle(){
//		return title;
//	}

	public void setPosterPath(String posterPath){
		this.posterPath = posterPath;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages){
		this.spokenLanguages = spokenLanguages;
	}

	public List<SpokenLanguage> getSpokenLanguages(){
		return spokenLanguages;
	}

	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;
	}

	public String getReleaseDate(){
		return releaseDate;
	}


	public void setPopularity(Object popularity){
		this.popularity = popularity;
	}

	public Object getPopularity(){
		return popularity;
	}

	public void setVoteAverage(Object voteAverage){
		this.voteAverage = voteAverage;
	}

	public Object getVoteAverage(){
		return voteAverage;
	}

	public void setProductionCountries(List<ProductionCountry> productionCountries){
		this.productionCountries = productionCountries;
	}

	public List<ProductionCountry> getProductionCountries(){
		return productionCountries;
	}

	public void setTagline(String tagline){
		this.tagline = tagline;
	}

	public String getTagline(){
		return tagline;
	}

	public void setVoteCount(int voteCount){
		this.voteCount = voteCount;
	}

	public int getVoteCount(){
		return voteCount;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Movie{" +
			",overview = '" + overview + '\'' + 
			",original_language = '" + originalLanguage + '\'' + 
			",original_title = '" + originalTitle + '\'' + 
			",imdb_id = '" + imdbId + '\'' + 
			",runtime = '" + runtime + '\'' + 
//			",title = '" + title + '\'' +
			",poster_path = '" + posterPath + '\'' + 
			",spoken_languages = '" + super.spokenLanguages + '\'' +
			",release_date = '" + releaseDate + '\'' + 
			",genres = '" + super.genres + '\'' +
			",popularity = '" + popularity + '\'' + 
			",vote_average = '" + voteAverage + '\'' + 
			",production_countries = '" + productionCountries + '\'' + 
			",tagline = '" + tagline + '\'' + 
			",vote_count = '" + voteCount + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}