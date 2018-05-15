package com.ct.fitness.ui;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ct.fitness.R;
import com.ct.fitness.database.ExerciseSettings;
import com.ct.fitness.models.Exercise;
import com.ct.fitness.utils.Common;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DailyTrainingActivity extends AppCompatActivity {
    private Button start;
    private ImageView imageView;
    TextView getready, timer, ename, countdown;
    ProgressBar progressBar;
    LinearLayout linearLayout;

    ExerciseSettings exerciseSettings;

    int ex_id = 0, limit_time = 0;
    List<Exercise> exerciseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_training);

        initData();
        exerciseSettings = new ExerciseSettings(this);
        if (exerciseSettings.getSettingMode() == 0) {
            limit_time = Common.TIME_LIMI_EASY;
        } else if (exerciseSettings.getSettingMode() == 1) {
            limit_time = Common.TIME_LIMI_MEDIUM;

        } else {
            limit_time = Common.TIME_LIMI_HARD;
        }
        start = findViewById(R.id.btnStart);
        imageView = findViewById(R.id.detail_image);
        progressBar = findViewById(R.id.progressBar);
        countdown = findViewById(R.id.textcountdown);
        getready = findViewById(R.id.textgetreay);
        timer = findViewById(R.id.timer);
        linearLayout = findViewById(R.id.layoutgetready);
        ename = findViewById(R.id.title);

        //setdata
        progressBar.setMax(exerciseList.size());

        //setexercise information

        setExerciseInformation(ex_id);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (start.getText().toString().toLowerCase().equals("start")) {
                    showGetReady();
                    start.setText("Done");
                    Toast.makeText(DailyTrainingActivity.this, "Done", Toast.LENGTH_SHORT).show();
                } else if (start.getText().toString().toLowerCase().equals("done")) {
                    if (exerciseSettings.getSettingMode() == 0) {
                        exerciseEasyModecountDownTimer.start();
                    } else if (exerciseSettings.getSettingMode() == 1) {
                        exerciseMediumModecountDownTimer.start();
                    } else {
                        exerciseHardModecountDownTimer.start();
                    }

                    restCountDownTimer.cancel();

                    if (ex_id < exerciseList.size()) {
                        showRestingTime();
                        ex_id++;
                        progressBar.setProgress(ex_id);
                        timer.setText("");
                        imageView.setVisibility(View.INVISIBLE);
                        linearLayout.setVisibility(View.VISIBLE);
                        start.setText("Skip..");
                        start.setVisibility(View.VISIBLE);
                        timer.setVisibility(View.INVISIBLE);

                        getready.setText("REST TIME");

                    } else {
                        showFinished();
                    }

                } else {
                    if (exerciseSettings.getSettingMode() == 0) {
                        exerciseEasyModecountDownTimer.cancel();
                    } else if (exerciseSettings.getSettingMode() == 1) {
                        exerciseMediumModecountDownTimer.cancel();
                    } else if (exerciseSettings.getSettingMode() == 2){
                        exerciseHardModecountDownTimer.cancel();

                        if (ex_id<exerciseList.size()){
                            setExerciseInformation(ex_id);

                            restCountDownTimer.cancel();
                        }else {
                            showFinished();
                        }
                    }


                }
            }
        });

    }

    private void showRestingTime() {

        imageView.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        start.setText("Skip..");
        start.setVisibility(View.VISIBLE);
        timer.setVisibility(View.INVISIBLE);
        restCountDownTimer.start()
        ;
        getready.setText("REST TIME");
    }

    private void showGetReady() {
        imageView.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        start.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.VISIBLE);

        getready.setText("GET READY");
        new CountDownTimer(6000, 1000) {

            @Override
            public void onTick(long l) {
                countdown.setText(" " + (l - 1000) / 1000);

            }

            @Override
            public void onFinish() {
                showExercises();
            }
        }.start();
    }

    private void showExercises() {
        if (ex_id < exerciseList.size()) {
            imageView.setVisibility(View.VISIBLE);
            start.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.INVISIBLE);

            if (exerciseSettings.getSettingMode() == 0) {
                exerciseEasyModecountDownTimer.start();
            } else if (exerciseSettings.getSettingMode() == 1) {
                exerciseMediumModecountDownTimer.start();
            } else {
                exerciseHardModecountDownTimer.start();
            }


            Glide.with(this).load(exerciseList.get(ex_id).getImageId()).into(imageView);
            ename.setText(exerciseList.get(ex_id).getImageName());

        } else {
            showFinished();
        }
    }
    //RESTING TIME
    CountDownTimer restCountDownTimer = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long l) {
            countdown.setText(" " + (l - 1000) / 1000);
        }

        @Override
        public void onFinish() {
            setExerciseInformation(ex_id);
            showExercises();
        }
    };



    CountDownTimer exerciseEasyModecountDownTimer = new CountDownTimer(Common.TIME_LIMI_EASY, 1000) {
        @Override
        public void onTick(long l) {
            timer.setText(" " + (l - 1000) / 1000);
        }

        @Override
        public void onFinish() {
            if (ex_id < exerciseList.size()-1) {
                ex_id++;
                progressBar.setProgress(ex_id);
                timer.setText("");


                setExerciseInformation(ex_id);
                start.setText("START");
            }else {
                showFinished();
            }
        }
    };
    CountDownTimer exerciseMediumModecountDownTimer = new CountDownTimer(Common.TIME_LIMI_MEDIUM, 1000) {
        @Override
        public void onTick(long l) {
            timer.setText(" " + (l - 1000) / 1000);
        }

        @Override
        public void onFinish() {
            if (ex_id < exerciseList.size()-1) {
                ex_id++;
                progressBar.setProgress(ex_id);
                timer.setText("");


                setExerciseInformation(ex_id);
                start.setText("START");
            }else {
                showFinished();
            }
        }
    };
    CountDownTimer exerciseHardModecountDownTimer = new CountDownTimer(Common.TIME_LIMI_HARD, 1000) {
        @Override
        public void onTick(long l) {
            timer.setText(" " + (l - 1000) / 1000);
        }

        @Override
        public void onFinish() {
            if (ex_id < exerciseList.size()-1) {
                ex_id++;
                progressBar.setProgress(ex_id);
                timer.setText("");


                setExerciseInformation(ex_id);
                start.setText("START");
            }else {
                showFinished();
            }
        }
    };

//    CountDownTimer exerciseMediumModecountDownTimer = new CountDownTimer(Common.TIME_LIMI_MEDIUM, 1000) {
//        @Override
//        public void onTick(long l) {
//            timer.setText(" " + (l - 1000) / 1000);
//        }
//
//        @Override
//        public void onFinish() {
//            if (ex_id < exerciseList.size()) {
//                ex_id++;
//                progressBar.setProgress(ex_id);
//                timer.setText("");
//
//
//                setExerciseInformation(ex_id);
//                start.setText("START");
//            }
//        }
//    };
//
//    CountDownTimer exerciseHardModecountDownTimer = new CountDownTimer(Common.TIME_LIMI_HARD, 1000) {
//        @Override
//        public void onTick(long l) {
//            timer.setText(" " + (l - 1000) / 1000);
//        }
//
//        @Override
//        public void onFinish() {
//            if (ex_id < exerciseList.size()) {
//                ex_id++;
//                progressBar.setProgress(ex_id);
//                timer.setText("");
//
//
//                setExerciseInformation(ex_id);
//                start.setText("START");
//            }
//        }
//    };

    private void showFinished() {

        imageView.setVisibility(View.INVISIBLE);
        start.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        timer.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        getready.setText("FINISHED!!");
        timer.setText("Congarts,You are done today!!");
        timer.setTextSize(20);

        //SAVE WORKOUT DATE
        exerciseSettings.saveDay(String.valueOf(Calendar.getInstance().getTimeInMillis()));

    }

    private void setExerciseInformation(int ex_id) {
        Glide.with(this).load(exerciseList.get(ex_id).getImageId()).into(imageView);
        ename.setText(exerciseList.get(ex_id).getImageName());
        start.setText("START");
        imageView.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);
        start.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
    }


    private void initData() {
        exerciseList.add(new Exercise(R.drawable.easy_pose, "Easy Pose"));
        exerciseList.add(new Exercise(R.drawable.cobra_pose, "Cobra Pose"));
        exerciseList.add(new Exercise(R.drawable.downward_facing_dog, "Downward Facing Pose"));
        exerciseList.add(new Exercise(R.drawable.boat_pose, "Boat Pose"));
        exerciseList.add(new Exercise(R.drawable.half_pigeon, "Half Pigeon"));
        exerciseList.add(new Exercise(R.drawable.crescent_lunge, "Crisent Lung"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose, "Worrier Pose"));
        exerciseList.add(new Exercise(R.drawable.bow_pose, "Bow Pose"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose_2, "Worrier Pose 2"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose, "Worrier Pose"));
    }
}
