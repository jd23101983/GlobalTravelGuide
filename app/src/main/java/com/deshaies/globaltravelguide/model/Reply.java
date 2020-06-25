
package com.deshaies.globaltravelguide.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reply {

    @SerializedName("cache")
    @Expose
    private String cache;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("count")
    @Expose
    private Long count;

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
