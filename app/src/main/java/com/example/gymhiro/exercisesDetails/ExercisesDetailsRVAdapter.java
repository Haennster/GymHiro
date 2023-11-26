package com.example.gymhiro.exercisesDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymhiro.R;
import com.example.gymhiro.classes.Exercise;

import java.util.ArrayList;

public class ExercisesDetailsRVAdapter extends RecyclerView.Adapter<ExercisesDetailsRVAdapter.ViewHolder> {

    private final ExerciseDetailsInterface exerciseDetailsInterface;
    Context context;
    ArrayList<Exercise> exercisesList;

    public ExercisesDetailsRVAdapter(Context context, ArrayList<Exercise> exercisesList,
                                     ExerciseDetailsInterface exerciseDetailsInterface){
        this.context = context;
        this.exercisesList = exercisesList;
        this.exerciseDetailsInterface = exerciseDetailsInterface;
    }

    @NonNull
    @Override
    public ExercisesDetailsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.exercises_row, parent, false);
        return new ExercisesDetailsRVAdapter.ViewHolder(view, exerciseDetailsInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesDetailsRVAdapter.ViewHolder holder, int position) {

        holder.exerciseName.setText(exercisesList.get(position).getNameOfExercise());

    }

    @Override
    public int getItemCount() {
        return exercisesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView exerciseName;

        public ViewHolder(@NonNull View itemView, ExerciseDetailsInterface exerciseDetailsInterface) {
            super(itemView);

            exerciseName = itemView.findViewById(R.id.exercise_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (exerciseDetailsInterface != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            exerciseDetailsInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
