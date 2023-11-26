package com.example.gymhiro.lastTraining;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymhiro.R;
import com.example.gymhiro.classes.Exercise;
import com.example.gymhiro.classes.Training;

import java.util.ArrayList;

public class LastTrainingRVAdapter extends RecyclerView.Adapter<LastTrainingRVAdapter.ViewHolder> {

    Context context;
    Training lastTraining;
    ArrayList<Exercise> exercisesInLastTraining;

    public LastTrainingRVAdapter(Context context, Training lastTraining) {
        this.context = context;
        this.lastTraining = lastTraining;
        this.exercisesInLastTraining = lastTraining.getListOfExercisesInTraining();
    }

    @NonNull
    @Override
    public LastTrainingRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.last_training_row, parent, false);

        return new LastTrainingRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LastTrainingRVAdapter.ViewHolder holder, int position) {

        holder.lastExerciseName.setText(exercisesInLastTraining.get(position).getNameOfExercise());
        holder.numberOfSeries.setText(exercisesInLastTraining.get(position).numberOfSeries());
    }

    @Override
    public int getItemCount() {
        if (exercisesInLastTraining != null){
            return exercisesInLastTraining.size();
        }else{
            return 0;
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{


        TextView lastTrainingDate;
        TextView lastExerciseName;
        TextView numberOfSeries;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lastTrainingDate = itemView.findViewById(R.id.lastTrainingDate);
            lastExerciseName = itemView.findViewById(R.id.lastExerciseName);
            numberOfSeries = itemView.findViewById(R.id.numberOfSeries);
        }
    }
}
