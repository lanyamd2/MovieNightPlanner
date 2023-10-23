package com.sparta.projectmovie1.movienightplanner.models.tvshows;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparta.projectmovie1.movienightplanner.models.Production;

public class Series extends Production {
	@JsonProperty("number_of_episodes")
	private Integer numberOfEpisodes;

	@JsonProperty("networks")
	private List<Network> networks;

	@JsonProperty("type")
	private String type;

	@JsonProperty("number_of_seasons")
	private Integer numberOfSeasons;

	@JsonProperty("seasons")
	private List<Season> seasons;

	@JsonProperty("languages")
	private List<String> languages;

	@JsonProperty("created_by")
	private List<Creator> createdBy;

	@JsonProperty("last_episode_to_air")
	private Episode lastEpisodeToAir;

	@JsonProperty("origin_country")
	private List<String> originCountry;

	@JsonProperty("episode_run_time")
	private List<Integer> episodeRunTime;

	@JsonProperty("next_episode_to_air")
	private Episode nextEpisodeToAir;

	@JsonProperty("in_production")
	private boolean inProduction;

	@JsonProperty("last_air_date")
	private String lastAirDate;

	@JsonProperty("homepage")
	private String homepage;


	public void setNumberOfEpisodes(Integer numberOfEpisodes){
		this.numberOfEpisodes = numberOfEpisodes;
	}

	public Integer getNumberOfEpisodes(){
		return numberOfEpisodes;
	}

	public void setNetworks(List<Network> networks){
		this.networks = networks;
	}

	public List<Network> getNetworks(){
		return networks;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setNumberOfSeasons(Integer numberOfSeasons){
		this.numberOfSeasons = numberOfSeasons;
	}

	public Integer getNumberOfSeasons(){
		return numberOfSeasons;
	}

	public void setSeasons(List<Season> seasons){
		this.seasons = seasons;
	}

	public List<Season> getSeasons(){
		return seasons;
	}

	public void setLanguages(List<String> languages){
		this.languages = languages;
	}

	public List<String> getLanguages(){
		return languages;
	}

	public void setCreatedBy(List<Creator> createdBy){
		this.createdBy = createdBy;
	}

	public List<Creator> getCreatedBy(){
		return createdBy;
	}

	public void setLastEpisodeToAir(Episode lastEpisodeToAir){
		this.lastEpisodeToAir = lastEpisodeToAir;
	}

	public Episode getLastEpisodeToAir(){
		return lastEpisodeToAir;
	}

	public void setOriginCountry(List<String> originCountry){
		this.originCountry = originCountry;
	}

	public List<String> getOriginCountry(){
		return originCountry;
	}

	public void setEpisodeRunTime(List<Integer> episodeRunTime){
		this.episodeRunTime = episodeRunTime;
	}

	public List<Integer> getEpisodeRunTime(){
		return episodeRunTime;
	}

	public void setNextEpisodeToAir(Episode nextEpisodeToAir){
		this.nextEpisodeToAir = nextEpisodeToAir;
	}

	public Episode getNextEpisodeToAir(){
		return nextEpisodeToAir;
	}

	public void setInProduction(boolean inProduction){
		this.inProduction = inProduction;
	}

	public boolean isInProduction(){
		return inProduction;
	}

	public void setLastAirDate(String lastAirDate){
		this.lastAirDate = lastAirDate;
	}

	public String getLastAirDate(){
		return lastAirDate;
	}

	public void setHomepage(String homepage){
		this.homepage = homepage;
	}

	public String getHomepage(){
		return homepage;
	}

	@Override
	public String toString(){
		return
				"Movie{" +
						",spoken_languages = '" + super.spokenLanguages + '\'' +
						",genres = '" + super.genres + '\'' +
						"original_language = '" + super.originalLanguage + '\'' +
						",number_of_episodes = '" + numberOfEpisodes + '\'' +
						",networks = '" + networks + '\'' +
						",type = '" + type + '\'' +
						",popularity = '" + super.popularity + '\'' +
						",production_countries = '" +super.productionCountries + '\'' +
						",id = '" + super.id + '\'' +
						",number_of_seasons = '" + numberOfSeasons + '\'' +
						",vote_count = '" + super.voteCount + '\'' +
						",first_air_date = '" + super.releaseDate + '\'' +
						",overview = '" + super.overview + '\'' +
						",seasons = '" + seasons + '\'' +
						",languages = '" + languages + '\'' +
						",created_by = '" + createdBy + '\'' +
						",last_episode_to_air = '" + lastEpisodeToAir + '\'' +
						",poster_path = '" + super.posterPath + '\'' +
						",origin_country = '" + originCountry + '\'' +
						",spoken_languages = '" + spokenLanguages + '\'' +
						",original_name = '" + super.originalTitle + '\'' +
						",vote_average = '" + super.voteAverage + '\'' +
						",name = '" + super.name + '\'' +
						",tagline = '" + super.tagline + '\'' +
						",episode_run_time = '" + episodeRunTime + '\'' +
						",next_episode_to_air = '" + nextEpisodeToAir + '\'' +
						",in_production = '" + inProduction + '\'' +
						",last_air_date = '" + lastAirDate + '\'' +
						",homepage = '" + homepage + '\'' +
						",status = '" + super.status + '\'' +
						"}";
	}
}