package com.sparta.projectmovie1.movienightplanner.models;


import com.fasterxml.jackson.annotation.JsonProperty;


public class Offer {

	@JsonProperty("urls")
	private Urls urls;

	@JsonProperty("presentation_type")
	private String presentationType;

	@JsonProperty("date_created")
	private String dateCreated;

	@JsonProperty("provider_id")
	private int providerId;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("retail_price")
	private Object retailPrice;

	@JsonProperty("monetization_type")
	private String monetizationType;

	public void setUrls(Urls urls){
		this.urls = urls;
	}

	public Urls getUrls(){
		return urls;
	}

	public void setPresentationType(String presentationType){
		this.presentationType = presentationType;
	}

	public String getPresentationType(){
		return presentationType;
	}

	public void setDateCreated(String dateCreated){
		this.dateCreated = dateCreated;
	}

	public String getDateCreated(){
		return dateCreated;
	}

	public void setProviderId(int providerId){
		this.providerId = providerId;
	}

	public int getProviderId(){
		return providerId;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getCurrency(){
		return currency;
	}

	public void setRetailPrice(Object retailPrice){
		this.retailPrice = retailPrice;
	}

	public Object getRetailPrice(){
		return retailPrice;
	}

	public void setMonetizationType(String monetizationType){
		this.monetizationType = monetizationType;
	}

	public String getMonetizationType(){
		return monetizationType;
	}


	public Offer() {
	}

	public Offer(Urls urls, String presentationType, String dateCreated, int providerId, String currency, Object retailPrice, String monetizationType) {
		this.urls = urls;
		this.presentationType = presentationType;
		this.dateCreated = dateCreated;
		this.providerId = providerId;
		this.currency = currency;
		this.retailPrice = retailPrice;
		this.monetizationType = monetizationType;
	}
}