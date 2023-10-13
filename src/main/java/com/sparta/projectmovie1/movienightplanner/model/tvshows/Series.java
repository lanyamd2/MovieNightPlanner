package com.sparta.projectmovie1.movienightplanner.model.tvshows;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparta.projectmovie1.movienightplanner.model.Genre;
import com.sparta.projectmovie1.movienightplanner.model.Production;
import com.sparta.projectmovie1.movienightplanner.model.SpokenLanguage;

public class Series extends Production {

	@JsonProperty("original_language")
	private String originalLanguage;

	@JsonProperty("number_of_episodes")
	private int numberOfEpisodes;

	@JsonProperty("networks")
	private List<Network> networks;

	@JsonProperty("type")
	private String type;

	@JsonProperty("genres")
	private List<Genre> genres;

	@JsonProperty("popularity")
	private Object popularity;

	@JsonProperty("production_countries")
	private List<Object> productionCountries;

	@JsonProperty("id")
	private int id;

	@JsonProperty("number_of_seasons")
	private int numberOfSeasons;

	@JsonProperty("vote_count")
	private int voteCount;

	@JsonProperty("first_air_date")
	private String firstAirDate;

	@JsonProperty("overview")
	private String overview;

	@JsonProperty("seasons")
	private List<Season> seasons;

	@JsonProperty("languages")
	private List<String> languages;

	@JsonProperty("created_by")
	private List<Creator> createdBy;

	@JsonProperty("last_episode_to_air")
	private Episode lastEpisodeToAir;

	@JsonProperty("poster_path")
	private String posterPath;

	@JsonProperty("origin_country")
	private List<String> originCountry;

	@JsonProperty("spoken_languages")
	private List<SpokenLanguage> spokenLanguages;

	@JsonProperty("original_name")
	private String originalName;

	@JsonProperty("vote_average")
	private Object voteAverage;

	@JsonProperty("tagline")
	private String tagline;

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

	@JsonProperty("status")
	private String status;

	public void setOriginalLanguage(String originalLanguage){
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public void setNumberOfEpisodes(int numberOfEpisodes){
		this.numberOfEpisodes = numberOfEpisodes;
	}

	public int getNumberOfEpisodes(){
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

	public void setGenres(List<Genre> genres){
		this.genres = genres;
	}

	public List<Genre> getGenres(){
		return genres;
	}

	public void setPopularity(Object popularity){
		this.popularity = popularity;
	}

	public Object getPopularity(){
		return popularity;
	}

	public void setProductionCountries(List<Object> productionCountries){
		this.productionCountries = productionCountries;
	}

	public List<Object> getProductionCountries(){
		return productionCountries;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setNumberOfSeasons(int numberOfSeasons){
		this.numberOfSeasons = numberOfSeasons;
	}

	public int getNumberOfSeasons(){
		return numberOfSeasons;
	}

	public void setVoteCount(int voteCount){
		this.voteCount = voteCount;
	}

	public int getVoteCount(){
		return voteCount;
	}

	public void setFirstAirDate(String firstAirDate){
		this.firstAirDate = firstAirDate;
	}

	public String getFirstAirDate(){
		return firstAirDate;
	}

	public void setOverview(String overview){
		this.overview = overview;
	}

	public String getOverview(){
		return overview;
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

	public void setPosterPath(String posterPath){
		this.posterPath = posterPath;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public void setOriginCountry(List<String> originCountry){
		this.originCountry = originCountry;
	}

	public List<String> getOriginCountry(){
		return originCountry;
	}

	public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages){
		this.spokenLanguages = spokenLanguages;
	}

	public List<SpokenLanguage> getSpokenLanguages(){
		return spokenLanguages;
	}

	public void setOriginalName(String originalName){
		this.originalName = originalName;
	}

	public String getOriginalName(){
		return originalName;
	}

	public void setVoteAverage(Object voteAverage){
		this.voteAverage = voteAverage;
	}

	public Object getVoteAverage(){
		return voteAverage;
	}


	public void setTagline(String tagline){
		this.tagline = tagline;
	}

	public String getTagline(){
		return tagline;
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
			",spoken_languages = '" + super.spokenLanguages + '\'' +
			",genres = '" + super.genres + '\'' +
			"original_language = '" + originalLanguage + '\'' + 
			",number_of_episodes = '" + numberOfEpisodes + '\'' + 
			",networks = '" + networks + '\'' + 
			",type = '" + type + '\'' + 
			",genres = '" + genres + '\'' + 
			",popularity = '" + popularity + '\'' + 
			",production_countries = '" + productionCountries + '\'' + 
			",id = '" + id + '\'' + 
			",number_of_seasons = '" + numberOfSeasons + '\'' + 
			",vote_count = '" + voteCount + '\'' + 
			",first_air_date = '" + firstAirDate + '\'' + 
			",overview = '" + overview + '\'' + 
			",seasons = '" + seasons + '\'' + 
			",languages = '" + languages + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",last_episode_to_air = '" + lastEpisodeToAir + '\'' + 
			",poster_path = '" + posterPath + '\'' + 
			",origin_country = '" + originCountry + '\'' + 
			",spoken_languages = '" + spokenLanguages + '\'' + 
			",original_name = '" + originalName + '\'' + 
			",vote_average = '" + voteAverage + '\'' + 
			",name = '" + super.name + '\'' +
			",tagline = '" + tagline + '\'' +
			",episode_run_time = '" + episodeRunTime + '\'' + 
			",next_episode_to_air = '" + nextEpisodeToAir + '\'' + 
			",in_production = '" + inProduction + '\'' + 
			",last_air_date = '" + lastAirDate + '\'' + 
			",homepage = '" + homepage + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}