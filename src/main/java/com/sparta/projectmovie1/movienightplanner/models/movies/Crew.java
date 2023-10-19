package com.sparta.projectmovie1.movienightplanner.models.movies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Crew{

	@JsonIgnore
	@JsonProperty("adult")
	private boolean adult;

	@JsonIgnore
	@JsonProperty("popularity")
	protected Object popularity;

	@JsonProperty("gender")
	private int gender;

	@JsonProperty("credit_id")
	private String creditId;

	@JsonProperty("known_for_department")
	private String knownForDepartment;

	@JsonProperty("original_name")
	private String originalName;

	@JsonProperty("name")
	private String name;

	@JsonProperty("profile_path")
	private String profilePath;

	@JsonProperty("id")
	private int id;

	@JsonProperty("department")
	private String department;

	@JsonProperty("job")
	private String job;

	public void setGender(int gender){
		this.gender = gender;
	}

	public int getGender(){
		return gender;
	}

	public void setCreditId(String creditId){
		this.creditId = creditId;
	}

	public String getCreditId(){
		return creditId;
	}

	public void setKnownForDepartment(String knownForDepartment){
		this.knownForDepartment = knownForDepartment;
	}

	public String getKnownForDepartment(){
		return knownForDepartment;
	}

	public void setOriginalName(String originalName){
		this.originalName = originalName;
	}

	public String getOriginalName(){
		return originalName;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setProfilePath(String profilePath){
		this.profilePath = profilePath;
	}

	public String getProfilePath(){
		return profilePath;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDepartment(String department){
		this.department = department;
	}

	public String getDepartment(){
		return department;
	}

	public void setJob(String job){
		this.job = job;
	}

	public String getJob(){
		return job;
	}
}