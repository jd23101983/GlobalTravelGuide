
package com.deshaies.globaltravelguide.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lang {

    @SerializedName("de")
    @Expose
    private De de;
    @SerializedName("en")
    @Expose
    private En en;

    public De getDe() {
        return de;
    }

    public void setDe(De de) {
        this.de = de;
    }

    public En getEn() {
        return en;
    }

    public void setEn(En en) {
        this.en = en;
    }

}
