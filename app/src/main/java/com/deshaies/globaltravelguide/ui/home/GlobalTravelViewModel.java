package com.deshaies.globaltravelguide.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.deshaies.globaltravelguide.R;
import com.deshaies.globaltravelguide.model.CountryContinent;
import com.deshaies.globaltravelguide.model.Result;
import com.deshaies.globaltravelguide.network.GlobalTravelRetrofitInstance;
import com.deshaies.globaltravelguide.util.DebugLogger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GlobalTravelViewModel extends AndroidViewModel {

    private Map<String, String> countriesMap;
    private List<String> countriesArray;
    private GlobalTravelRetrofitInstance globalTravelRetrofitInstance;
    private ArrayList<String> continentArray;
    private ArrayList<CountryContinent> countryContinentArray;

    public GlobalTravelViewModel(@NonNull Application application) throws IOException {
        super(application);

        setupContinentArray();
        processCountriesByContinent();
        setupCountryMapAndArray();
        globalTravelRetrofitInstance = new GlobalTravelRetrofitInstance();
    }

    public void processCountriesByContinent() throws IOException {
        InputStream inputStreamJSONInt = getApplication().getResources().openRawResource(R.raw.country_by_continent);
        Reader reader = new BufferedReader(new InputStreamReader(inputStreamJSONInt, "UTF-8"));
        Gson gson = new Gson();

        Type countryContinentListType = new TypeToken<ArrayList<CountryContinent>>(){}.getType();
        countryContinentArray = gson.fromJson(reader, countryContinentListType);

        for(CountryContinent cc : countryContinentArray) {
            DebugLogger.logDebug("*** Country Data: " + cc.getContinent() + " : " + cc.getCountry());
        }
    }

    public void setupContinentArray() {
        continentArray = new ArrayList<>(Arrays.asList("Africa","North America","South America","Europe","Asia","Antarctica","Oceania"));
    }

    public ArrayList<CountryContinent> getCountryContinentArray() {
        return countryContinentArray;
    }

    public List<String> getContinentArray() {
        return continentArray;
    }

    public List<String> getCountriesByContinentArray(String targetContinent) {

        List<String> countryArray = new ArrayList<>();

        for (int i = 0; i < countryContinentArray.size(); i++) {

            DebugLogger.logDebug("Comparing: " + targetContinent + " and " + countryContinentArray.get(i).getContinent());

            if (countryContinentArray.get(i).getContinent().equals(targetContinent))
                countryArray.add(countryContinentArray.get(i).getCountry());
        }

        return countryArray;
    }

    public void setupCountryMapAndArray() {
        countriesMap = new HashMap<>();
        countriesArray = new ArrayList<String>();
        for (String iso : Locale.getISOCountries()) {
            Locale locale = new Locale("", iso);
            countriesMap.put(locale.getDisplayCountry(), iso);
            countriesArray.add(locale.getDisplayCountry());
        }
    }

    public List<String> getCountriesArray() {
        return countriesArray;
    }

    public String getCountryCode(String countryName) { return countriesMap.get(countryName); }

    public Observable<Result> getGlobalTravelData(String countryNameCode) {
        return  globalTravelRetrofitInstance
                .getGlobalTravelData(countriesMap.get(countryNameCode))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
