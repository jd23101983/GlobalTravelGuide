
package com.deshaies.globaltravelguide.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("api_status")
    @Expose
    private ApiStatus apiStatus;
    @SerializedName("data")
    @Expose
    private Data data;

    public ApiStatus getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(ApiStatus apiStatus) {
        this.apiStatus = apiStatus;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
