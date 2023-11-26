package com.example.gymhiro.viewPager2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymhiro.R;
import com.example.gymhiro.classes.Exercise;
import com.example.gymhiro.classes.Serie;

import java.util.ArrayList;

public class NewTrainingViewPager2Adapter extends RecyclerView.Adapter<NewTrainingViewPager2Adapter.ViewHolder> {

    Context context;
    public ArrayList<Exercise> exercises;
    int image = R.drawable.excercise_back_23;

    public NewTrainingViewPager2Adapter(Context context, ArrayList<Exercise> exercises) {
        this.context = context;
        this.exercises = exercises;
    }


    @NonNull
    @Override
    public NewTrainingViewPager2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.training_exercises_view_pager2,parent,false);
        return new NewTrainingViewPager2Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewTrainingViewPager2Adapter.ViewHolder holder, int position) {

        Exercise exercise = exercises.get(position);

        holder.exerciseName.setText(exercise.getNameOfExercise());
        holder.exerciseImage.setImageResource(image);

//        ustawienie recyclerview dla Serii
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        SeriesRVAdapter seriesRVAdapter = new SeriesRVAdapter(context, exercise.getListOfSeriesInExercise());
        holder.recyclerView.setAdapter(seriesRVAdapter);



        holder.addSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                exercises.get(position).addSerie(new Serie(10,0));
                Exercise exercise = exercises.get(position);


                SeriesRVAdapter seriesRVAdapter = new SeriesRVAdapter(context, exercise.getListOfSeriesInExercise());
                holder.recyclerView.setAdapter(seriesRVAdapter);

                Log.d("viewHolder", exercise.getNameOfExercise() + " " + exercise.getListOfSeriesInExercise().size() );
            }
        });

        holder.deleteSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (exercises.get(position).getListOfSeriesInExercise().size() > 1) {
                    exercises.get(position).removeLastSerie();
                    Exercise exercise = exercises.get(position);

                    SeriesRVAdapter seriesRVAdapter = new SeriesRVAdapter(context, exercise.getListOfSeriesInExercise());
                    holder.recyclerView.setAdapter(seriesRVAdapter);

                    Log.d("viewHolder", exercise.getNameOfExercise() + " " + exercise.getListOfSeriesInExercise().size());
                }
            }
        });

//


    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView exerciseName;
        ImageView exerciseImage;
        RecyclerView recyclerView;
        Button deleteSet;
        Button addSet;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.viewPager2ExerciseName);
            exerciseImage = itemView.findViewById(R.id.viewPager2ExerciseImage);
            recyclerView = itemView.findViewById(R.id.viewPager2SeriesRV);
            deleteSet = itemView.findViewById(R.id.removeSet);
            addSet = itemView. findViewById(R.id.addSet);




        }


    }
}
