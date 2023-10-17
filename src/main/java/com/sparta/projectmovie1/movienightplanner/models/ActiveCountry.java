package com.sparta.projectmovie1.movienightplanner.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActiveCountry {

	@JsonProperty("country")
	private String country;

	@JsonProperty("url_part")
	private String urlPart;

	@JsonProperty("full_locale")
	private String fullLocale;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("status")
	private String status;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setUrlPart(String urlPart){
		this.urlPart = urlPart;
	}

	public String getUrlPart(){
		return urlPart;
	}

	public void setFullLocale(String fullLocale){
		this.fullLocale = fullLocale;
	}

	public String getFullLocale(){
		return fullLocale;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getCurrency(){
		return currency;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}