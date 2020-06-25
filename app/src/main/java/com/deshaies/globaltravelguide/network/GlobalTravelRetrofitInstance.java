package com.deshaies.globaltravelguide.network;

import com.deshaies.globaltravelguide.util.Constants;
import com.deshaies.globaltravelguide.model.Result;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GlobalTravelRetrofitInstance {

    private GlobalTravelService globalTravelService;
    private OkHttpClient client;

    public GlobalTravelRetrofitInstance() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        globalTravelService = createGlobalTravelService(getRetrofitInstance());
    }

    private Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private GlobalTravelService createGlobalTravelService(Retrofit retrofitInstance) {
        return retrofitInstance.create(GlobalTravelService.class);
    }

    public Observable<Result> getGlobalTravelData(String countryNameCode) {
        return globalTravelService.getCountryData(countryNameCode);
    }
}
