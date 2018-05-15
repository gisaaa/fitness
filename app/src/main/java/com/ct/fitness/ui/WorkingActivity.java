package com.ct.fitness.ui;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ct.fitness.R;
import com.ct.fitness.fragments.FitnessFragment;


public class WorkingActivity extends AppCompatActivity {
    private static final String TAG = "WorkingActivity";
    private String key_switch;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    Toolbar mtoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working);
      ImageView imageView=findViewById(R.id.back);
        TextView current;
      imageView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              finish();
          }
      });
      current=findViewById(R.id.name);
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();

        try{
            key_switch=getIntent().getStringExtra("key");
            switch (key_switch){
                case "about":
                    current.setText("Fitness");
                    Fragment fragment=new FitnessFragment();
                    fragmentTransaction.replace(R.id.main,fragment);
                    fragmentTransaction.commit();
                    Log.d(TAG, "onCreate: About");

                    break;


            }
        }catch (NullPointerException ex){
            Log.d(TAG, "onCreate: NullPointerException "+ex.getMessage());
            ex.printStackTrace();
        }

    }
}
