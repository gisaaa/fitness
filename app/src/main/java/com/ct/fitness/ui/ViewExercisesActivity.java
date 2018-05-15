package com.ct.fitness.ui;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ct.fitness.R;
import com.ct.fitness.database.ExerciseSettings;
import com.ct.fitness.utils.Common;

public class ViewExercisesActivity extends AppCompatActivity {
int imageId;
String name;
TextView title,timer;
ImageView details;
Button start;
boolean isRunning;
private ExerciseSettings exerciseSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercises);
        exerciseSettings=new ExerciseSettings(this);

        init();
        timer.setText("");
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isRunning){


                    int timeLimit=0;

                    if (exerciseSettings.getSettingMode()==0){
                        timeLimit= Common.TIME_LIMI_EASY;
                    }else if (exerciseSettings.getSettingMode()==1){
                        timeLimit= Common.TIME_LIMI_MEDIUM;
                    }else if (exerciseSettings.getSettingMode()==2){
                        timeLimit=Common.TIME_LIMI_HARD;
                    }


                    start.setText("STOP");
                    new CountDownTimer(timeLimit, 1000) {
                        @Override
                        public void onTick(long l) {
                            timer.setText(""+l/1000);
                        }

                        @Override
                        public void onFinish() {
                            Toast.makeText(ViewExercisesActivity.this, "Done", Toast.LENGTH_SHORT).show();
                        }
                    }.start();
                }else{
                    Toast.makeText(ViewExercisesActivity.this, "Done", Toast.LENGTH_SHORT).show();
finish();

                }
                isRunning=!isRunning;

            }
        });
        if (getIntent()!=null){
            imageId=getIntent().getIntExtra("image_id",-1);
            name=getIntent().getStringExtra("name");

            title.setText(name);
            details.setImageResource(imageId);

        }
    }

    private void init() {
        timer=findViewById(R.id.timer);
        title=findViewById(R.id.detailname);
        details=findViewById(R.id.detail_image);
        start=findViewById(R.id.btnStart);

    }
}
