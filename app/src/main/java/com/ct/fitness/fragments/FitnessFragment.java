package com.ct.fitness.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.ct.fitness.R;
import com.ct.fitness.ui.CalenderActivity;
import com.ct.fitness.ui.DailyTrainingActivity;
import com.ct.fitness.ui.ListOfActivitiesActivity;
import com.ct.fitness.ui.TrainingSettingsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FitnessFragment extends Fragment {

    private static final String TAG = "FitnessFragment";
    private Toolbar mToolbar;
    Button exercises,calender,settings;
    ImageView playImage;
    public FitnessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_about, container, false);
        exercises=view.findViewById(R.id.btnEx);
        calender=view.findViewById(R.id.btnCalender);
        settings=view.findViewById(R.id.btnStart);
        playImage=view.findViewById(R.id.btnTraining);
calender.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(getContext(), CalenderActivity.class));
    }
});
        playImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DailyTrainingActivity.class));
            }
        });
        exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ListOfActivitiesActivity.class));
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), TrainingSettingsActivity.class));
            }
        });
        return view;
    }

}
