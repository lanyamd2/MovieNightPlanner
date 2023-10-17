package com.sparta.projectmovie1.movienightplanner.models.tvshows;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Season {
	@JsonProperty("air_date")
	private String airDate;

	@JsonProperty("overview")
	private String overview;

	@JsonProperty("episode_count")
	private int episodeCount;

	@JsonProperty("vote_average")
	private int voteAverage;

	@JsonProperty("name")
	private String name;

	@JsonProperty("season_number")
	private int seasonNumber;

	@JsonProperty("id")
	private int id;

	@JsonProperty("poster_path")
	private Object posterPath;

	public void setAirDate(String airDate){
		this.airDate = airDate;
	}

	public String getAirDate(){
		return airDate;
	}

	public void setOverview(String overview){
		this.overview = overview;
	}

	public String getOverview(){
		return overview;
	}

	public void setEpisodeCount(int episodeCount){
		this.episodeCount = episodeCount;
	}

	public int getEpisodeCount(){
		return episodeCount;
	}

	public void setVoteAverage(int voteAverage){
		this.voteAverage = voteAverage;
	}

	public int getVoteAverage(){
		return voteAverage;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setSeasonNumber(int seasonNumber){
		this.seasonNumber = seasonNumber;
	}

	public int getSeasonNumber(){
		return seasonNumber;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPosterPath(Object posterPath){
		this.posterPath = posterPath;
	}

	public Object getPosterPath(){
		return posterPath;
	}

	@Override
 	public String toString(){
		return 
			"SeasonsItem{" + 
			"air_date = '" + airDate + '\'' + 
			",overview = '" + overview + '\'' + 
			",episode_count = '" + episodeCount + '\'' + 
			",vote_average = '" + voteAverage + '\'' + 
			",name = '" + name + '\'' + 
			",season_number = '" + seasonNumber + '\'' + 
			",id = '" + id + '\'' + 
			",poster_path = '" + posterPath + '\'' + 
			"}";
		}
}