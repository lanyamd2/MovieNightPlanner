package com.sparta.projectmovie1.movienightplanner.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpokenLanguage {

	@JsonProperty("name")
	private String name;

	@JsonProperty("iso_639_1")
	private String iso6391;

	@JsonProperty("english_name")
	private String englishName;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setIso6391(String iso6391){
		this.iso6391 = iso6391;
	}

	public String getIso6391(){
		return iso6391;
	}

	public void setEnglishName(String englishName){
		this.englishName = englishName;
	}

	public String getEnglishName(){
		return englishName;
	}

	@Override
 	public String toString(){
		return 
			"SpokenLanguagesItem{" + 
			"name = '" + name + '\'' + 
			",iso_639_1 = '" + iso6391 + '\'' + 
			",english_name = '" + englishName + '\'' + 
			"}";
		}
}