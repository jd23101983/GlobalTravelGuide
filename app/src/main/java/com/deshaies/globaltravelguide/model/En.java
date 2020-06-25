
package com.deshaies.globaltravelguide.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class En {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("continent")
    @Expose
    private String continent;
    @SerializedName("continent_info")
    @Expose
    private String continentInfo;
    @SerializedName("url_details")
    @Expose
    private String urlDetails;
    @SerializedName("advice")
    @Expose
    private String advice;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getContinentInfo() {
        return continentInfo;
    }

    public void setContinentInfo(String continentInfo) {
        this.continentInfo = continentInfo;
    }

    public String getUrlDetails() {
        return urlDetails;
    }

    public void setUrlDetails(String urlDetails) {
        this.urlDetails = urlDetails;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

}
