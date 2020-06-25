package com.deshaies.globaltravelguide.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.deshaies.globaltravelguide.R;
import com.deshaies.globaltravelguide.model.Result;
import com.deshaies.globaltravelguide.util.DebugLogger;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;

public class HomeFragment extends Fragment {

    private Context context;

    private HomeViewModel homeViewModel;

    GlobalTravelViewModel globalTravelViewModel;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    // TODO
    TravelAdvisoryOneFragment travelAdvisoryOneFragment;

    private ArrayAdapter<String> countryAdapter;

    @BindView(R.id.earth_image)
    ImageView earthImageView;

    @BindView(R.id.continent_spinner)
    Spinner continentSpinner;

    @BindView(R.id.country_spinner)
    Spinner countrySpinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        globalTravelViewModel =
                ViewModelProviders.of(this).get(GlobalTravelViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);

        context = requireActivity().getApplicationContext();

        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //earthImageView = view.findViewById(R.id.earth_image);
        //continentSpinner = view.findViewById(R.id.continent_spinner);
        //countrySpinner = view.findViewById(R.id.country_spinner);

        Glide.with(view).load(R.drawable.earth).into(earthImageView);

        ArrayAdapter<String> continentAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, globalTravelViewModel.getContinentArray());
        continentSpinner.setAdapter(continentAdapter);
        continentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                countryAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        globalTravelViewModel.getCountriesByContinentArray(continentSpinner.getSelectedItem().toString()));
                countrySpinner.setAdapter(countryAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        // TODO
        travelAdvisoryOneFragment = new TravelAdvisoryOneFragment();

    }


    @OnClick(R.id.get_country_data_button)
    public void getCountryData() {
        compositeDisposable.add(globalTravelViewModel.getGlobalTravelData(countrySpinner.getSelectedItem().toString())
                .subscribe(globalTravelResult -> {
                    displayInformationRx(globalTravelResult);
                }, throwable -> {
                    DebugLogger.logError(throwable);
                }));
    }


    public void displayInformationRx(Result globalTravelResult) {

        travelAdvisoryOneFragment.setTravelAdvisoryResult(globalTravelResult);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.travel_advisory_frame, travelAdvisoryOneFragment)
                .commit();

        String countryCode = globalTravelResult.getData().getCode().getCountry();
        Log.d("TAG_X", "result: name: " + countryCode);
        Log.d("TAG_X", "request: item: " + globalTravelResult.getData().getSituation().getRating());
        Log.d("TAG_X", "request: item: " + globalTravelResult.getData().getLang().getEn().getAdvice());
    }

    /*
    public void returnFromAdvisory() {
        getActivity().getSupportFragmentManager().popBackStack();
                //.beginTransaction()
                //.remove(travelAdvisoryOneFragment)
                //.commit();
    }
    */

}
