package com.sparta.projectmovie1.movienightplanner.models;

public class Provider {

    Integer provider_id;

    String provider_name;

    String logo_path;

    public Integer getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(Integer provider_id) {
        this.provider_id = provider_id;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "provider_id=" + provider_id +
                ", provider_name='" + provider_name + '\'' +
                ", logo_path='" + logo_path + '\'' +
                '}';
    }
}
