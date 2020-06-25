package com.deshaies.globaltravelguide.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.deshaies.globaltravelguide.R;
import com.deshaies.globaltravelguide.model.Result;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TravelAdvisoryOneFragment extends Fragment {

    GlobalTravelViewModel globalTravelViewModel;
    Result travelAdvisoryResult;

    @BindView(R.id.country_flag_imageview)
    ImageView countryFlagImageView;

    @BindView(R.id.country_name_textview)
    TextView countryNameTextView;

    @BindView(R.id.advisory_score_textview)
    TextView advisoryScoreTextView;

    @BindView(R.id.advisory_message_textView)
    TextView advisoryMessageTextView;

    @BindView(R.id.advisory_updated_date_textView)
    TextView advisoryUpdatedDateTextView;

    public TravelAdvisoryOneFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_travel_advisory_one, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        globalTravelViewModel = ViewModelProviders.of(this).get(GlobalTravelViewModel.class);

        String countryCode = globalTravelViewModel.getCountryCode(travelAdvisoryResult.getData().getLang().getEn().getCountry());
        String url = "https://www.countryflags.io/" + countryCode + "/flat/64.png";

        Glide.with(this).load(url).into(countryFlagImageView);

        String riskRating = travelAdvisoryResult.getData().getSituation().getRating();
        advisoryScoreTextView.setTextColor(Color.RED);

        countryNameTextView.setText(travelAdvisoryResult.getData().getLang().getEn().getCountry());
        advisoryScoreTextView.setText(travelAdvisoryResult.getData().getSituation().getRating()+getText(R.string.five_pt_zero));
        advisoryScoreTextView.setTextColor(getRiskRatingColor(travelAdvisoryResult.getData().getSituation().getRating()));
        advisoryMessageTextView.setText(travelAdvisoryResult.getData().getLang().getEn().getAdvice());
        advisoryUpdatedDateTextView.setText(travelAdvisoryResult.getData().getSituation().getUpdated());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public int getRiskRatingColor (String riskRating) {
        double doubleRiskRating = new Double(riskRating);

        if (doubleRiskRating <= 2.5) return Color.GREEN;
        else if (doubleRiskRating <= 3.5) return Color.BLUE;
        else if (doubleRiskRating <= 4.5) return Color.YELLOW;
        else return Color.RED;
    }

    public void setTravelAdvisoryResult(Result travelAdvisoryResult) {
        this.travelAdvisoryResult = travelAdvisoryResult;
    }

    @OnClick(R.id.return_button)
    public void returnFromAdvisory() {
        getActivity().onBackPressed();
    }
}
