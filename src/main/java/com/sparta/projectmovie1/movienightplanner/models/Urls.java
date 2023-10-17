package com.sparta.projectmovie1.movienightplanner.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Urls {

	@JsonProperty("standard_web")
	private String standardWeb;

	@JsonProperty("deeplink_android_tv")
	private String deeplinkAndroidTv;

	@JsonProperty("deeplink_tvos")
	private String deeplinkTvos;

	public String getDeeplinkTvos() {
		return deeplinkTvos;
	}

	public void setDeeplinkTvos(String deeplinkTvos) {
		this.deeplinkTvos = deeplinkTvos;
	}

	@JsonProperty("raw_web")
	private String rawWeb;


	public void setStandardWeb(String standardWeb){
		this.standardWeb = standardWeb;
	}

	public String getStandardWeb(){
		return standardWeb;
	}

	public void setDeeplinkAndroidTv(String deeplinkAndroidTv){
		this.deeplinkAndroidTv = deeplinkAndroidTv;
	}

	public String getDeeplinkAndroidTv(){
		return deeplinkAndroidTv;
	}

	public void setRawWeb(String rawWeb){
		this.rawWeb = rawWeb;
	}

	public String getRawWeb(){
		return rawWeb;
	}
}