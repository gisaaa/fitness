package com.ct.fitness.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.ct.fitness.R;
import com.ct.fitness.database.ExerciseSettings;

import java.util.Calendar;
import java.util.Date;

public class TrainingSettingsActivity extends AppCompatActivity {
    private static final String TAG = "TrainingSettingsActivit";
    Button saveSettings;
    RadioGroup mRadioGroup;
    RadioButton btnEasy,btnHard,btnMedium;
    ExerciseSettings exerciseSettings;
    TimePicker timePicker;
    ToggleButton switchAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_settings);

        initializeViews();

        saveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveWorkOutMode();
                saveAlarm(switchAlarm.isChecked());
                Toast.makeText(TrainingSettingsActivity.this, "saved", Toast.LENGTH_SHORT).show();
          finish();   }
        });
    }

    private void saveAlarm(boolean checked) {
        if (checked){
            AlarmManager alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent intent;
            PendingIntent pendingIntent;

            intent=new Intent(TrainingSettingsActivity.this,AlarmNotificationReciever.class);
            pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);


           // set time to alrm

            Calendar calendar=Calendar.getInstance();
            Date today=calendar.getTime();
            calendar.set(today.getYear(),today.getMonth(),today.getDay(),timePicker.getHour(),timePicker.getMinute());
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
            Log.d(TAG, "saveAlarm: Alarm wwill rig at "+timePicker.getHour()+": "+timePicker.getMinute());

        }else{
            //cancel the alatm
            AlarmManager alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);

            Intent  intent=new Intent(TrainingSettingsActivity.this,AlarmNotificationReciever.class);
        PendingIntent    pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);

alarmManager.cancel(pendingIntent);
        }
    }

    private void saveWorkOutMode() {
        int selectedId=mRadioGroup.getCheckedRadioButtonId();

        if (selectedId==btnEasy.getId()){
            exerciseSettings.setSettingsMode(0);

        }
        if (selectedId==btnMedium.getId()){
            exerciseSettings.setSettingsMode(1);

        }
        if (selectedId==btnHard.getId()){
            exerciseSettings.setSettingsMode(2);

        }
    }

    private void initializeViews() {
        saveSettings=findViewById(R.id.brnSave);
        mRadioGroup=findViewById(R.id.rdgroup);
        btnEasy=findViewById(R.id.rdEasy);
        btnHard=findViewById(R.id.rdHard);
        btnMedium=findViewById(R.id.rdMedium);

        switchAlarm=findViewById(R.id.swithAlarm);
        timePicker=findViewById(R.id.timePicker);
        exerciseSettings=new ExerciseSettings(this);
        int mode=exerciseSettings.getSettingMode();
        setRadioButton(mode);


    }

    private void setRadioButton(int mode) {
        if (mode==0){
            mRadioGroup.check(R.id.rdEasy);
        }else if (mode==1){
            mRadioGroup.check(R.id.rdMedium);

        }else{
            mRadioGroup.check(R.id.rdHard);
        }

    }
}
