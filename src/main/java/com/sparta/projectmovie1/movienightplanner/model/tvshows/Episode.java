package com.sparta.projectmovie1.movienightplanner.model.tvshows;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparta.projectmovie1.movienightplanner.model.Production;

public class Episode {

	@JsonProperty("episode_type")
	private String episodeType;

	@JsonProperty("production_code")
	private String productionCode;

	@JsonProperty("overview")
	private String overview;

	@JsonProperty("show_id")
	private int showId;

	@JsonProperty("season_number")
	private int seasonNumber;

	@JsonProperty("runtime")
	private int runtime;

	@JsonProperty("still_path")
	private Object stillPath;

	@JsonProperty("air_date")
	private String airDate;

	@JsonProperty("episode_number")
	private int episodeNumber;

	@JsonProperty("vote_average")
	private int voteAverage;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private int id;

	@JsonProperty("vote_count")
	private int voteCount;

	public void setEpisodeType(String episodeType){
		this.episodeType = episodeType;
	}

	public String getEpisodeType(){
		return episodeType;
	}

	public void setProductionCode(String productionCode){
		this.productionCode = productionCode;
	}

	public String getProductionCode(){
		return productionCode;
	}

	public void setOverview(String overview){
		this.overview = overview;
	}

	public String getOverview(){
		return overview;
	}

	public void setShowId(int showId){
		this.showId = showId;
	}

	public int getShowId(){
		return showId;
	}

	public void setSeasonNumber(int seasonNumber){
		this.seasonNumber = seasonNumber;
	}

	public int getSeasonNumber(){
		return seasonNumber;
	}

	public void setRuntime(int runtime){
		this.runtime = runtime;
	}

	public int getRuntime(){
		return runtime;
	}

	public void setStillPath(Object stillPath){
		this.stillPath = stillPath;
	}

	public Object getStillPath(){
		return stillPath;
	}

	public void setAirDate(String airDate){
		this.airDate = airDate;
	}

	public String getAirDate(){
		return airDate;
	}

	public void setEpisodeNumber(int episodeNumber){
		this.episodeNumber = episodeNumber;
	}

	public int getEpisodeNumber(){
		return episodeNumber;
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

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setVoteCount(int voteCount){
		this.voteCount = voteCount;
	}

	public int getVoteCount(){
		return voteCount;
	}

	@Override
 	public String toString(){
		return 
			"NextEpisodeToAir{" + 
			"episode_type = '" + episodeType + '\'' + 
			",production_code = '" + productionCode + '\'' + 
			",overview = '" + overview + '\'' + 
			",show_id = '" + showId + '\'' + 
			",season_number = '" + seasonNumber + '\'' + 
			",runtime = '" + runtime + '\'' + 
			",still_path = '" + stillPath + '\'' + 
			",air_date = '" + airDate + '\'' + 
			",episode_number = '" + episodeNumber + '\'' + 
			",vote_average = '" + voteAverage + '\'' + 
			",name = '" + name + '\'' +
			",id = '" + id + '\'' +
			",vote_count = '" + voteCount + '\'' + 
			"}";
		}
}