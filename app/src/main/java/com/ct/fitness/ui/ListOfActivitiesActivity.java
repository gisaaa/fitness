package com.ct.fitness.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ct.fitness.R;
import com.ct.fitness.adapters.RecyclerViewAdapter;
import com.ct.fitness.models.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ListOfActivitiesActivity extends AppCompatActivity {
List<Exercise> exerciseList=new ArrayList<>();
    private Context mcontext;
RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_activities);

        initData();


        RecyclerView recyclerView=findViewById(R.id.list_ex);
        adapter=new RecyclerViewAdapter(exerciseList,getBaseContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
        recyclerView.setAdapter(adapter);

    }

    private void initData() {
        exerciseList.add(new Exercise(R.drawable.easy_pose,"Easy Pose"));
        exerciseList.add(new Exercise(R.drawable.cobra_pose,"Cobra Pose"));
        exerciseList.add(new Exercise(R.drawable.downward_facing_dog,"Downward Facing Pose"));
        exerciseList.add(new Exercise(R.drawable.boat_pose,"Boat Pose"));
        exerciseList.add(new Exercise(R.drawable.half_pigeon,"Half Pigeon"));
        exerciseList.add(new Exercise(R.drawable.crescent_lunge,"Crisent Lung"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose,"Worrier Pose"));
        exerciseList.add(new Exercise(R.drawable.bow_pose,"Bow Pose"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose_2,"Worrier Pose 2"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose,"Worrier Pose"));
    }
}
