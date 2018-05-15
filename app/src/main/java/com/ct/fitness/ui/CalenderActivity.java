package com.ct.fitness.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ct.fitness.Custom.WorkOutDoneDecorator;
import com.ct.fitness.R;
import com.ct.fitness.database.ExerciseSettings;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class CalenderActivity extends AppCompatActivity {
MaterialCalendarView materialCalendarView;
HashSet<CalendarDay> calendarDays=new HashSet<>();

ExerciseSettings exerciseSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        exerciseSettings=new ExerciseSettings(this);
        materialCalendarView=findViewById(R.id.calender);


        List<String> workoutdays=exerciseSettings.getWorkoutDays();
        HashSet<CalendarDay>  convertedList=new HashSet<>();

        for(String v:workoutdays){
            convertedList.add(CalendarDay.from(new Date(Long.parseLong(v))));
            materialCalendarView.addDecorator(new WorkOutDoneDecorator(convertedList));

        }

    }
}
