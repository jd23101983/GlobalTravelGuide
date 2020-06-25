package com.deshaies.globaltravelguide.network;

import com.deshaies.globaltravelguide.model.Result;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.deshaies.globaltravelguide.util.Constants.URL_POSTFIX;

public interface GlobalTravelService {

    @GET(URL_POSTFIX)
    Observable<Result> getCountryData(@Query("country") String countryCode);
}
