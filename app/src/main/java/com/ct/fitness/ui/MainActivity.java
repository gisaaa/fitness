package com.ct.fitness.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ct.fitness.R;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    CardView card4;
    private Context mContext;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //-----------------INITILIZING WIDGETS ------------------------//
        initWidgets();

        //-----------------SET HANDLERS TO WIDDGETS-------------------//
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, WorkingActivity.class);
                MainActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                i.putExtra("key", "about");
                startActivity(i);

            }
        });


    }

    private void initWidgets() {
        mContext = MainActivity.this;
        card4 = findViewById(R.id.cd);
        mToolbar = findViewById(R.id.appbar);
    }


    public void next(View view) {
        Log.d(TAG, "next: ");
        Intent i = new Intent(mContext, WorkingActivity.class);
        MainActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        i.putExtra("key", "about");
        startActivity(i);
    }
}
