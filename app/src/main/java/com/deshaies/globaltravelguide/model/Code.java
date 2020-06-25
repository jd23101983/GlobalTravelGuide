
package com.deshaies.globaltravelguide.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Code {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("continent")
    @Expose
    private String continent;

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

}
