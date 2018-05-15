package com.ct.fitness.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.ct.fitness.R;
import com.ct.fitness.interfaces.OnItemClickListener;
import com.ct.fitness.models.Exercise;
import com.ct.fitness.ui.ViewExercisesActivity;

import java.util.List;

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
     ImageView imageView;
     TextView textView;
     OnItemClickListener onItemClickListener;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image);
        textView = itemView.findViewById(R.id.name);
        itemView.setOnClickListener(this);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View view) {
        onItemClickListener.onClick(view, getAdapterPosition());

    }

}





public  class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{
private List<Exercise> exerciseList;
private Context context;

        public RecyclerViewAdapter(List<Exercise> exerciseList, Context context) {
            this.exerciseList = exerciseList;
            this.context = context;
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
            View view=layoutInflater.inflate(R.layout.single_exercise,parent,false);

            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
          holder.textView.setText(exerciseList.get(position).getImageName());
          Glide.with(context).load(exerciseList.get(position).getImageId()).into(holder.imageView);


            holder.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onClick(View v, int position) {
                  Intent i=new Intent(context, ViewExercisesActivity.class);
                  i.putExtra("image_id",exerciseList.get(position).getImageId());
                  i.putExtra("name",exerciseList.get(position).getImageName());
                  i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                  context.startActivity(i);

                    }
            });
        }

        @Override
        public int getItemCount() {
            return exerciseList.size();
        }
    }
