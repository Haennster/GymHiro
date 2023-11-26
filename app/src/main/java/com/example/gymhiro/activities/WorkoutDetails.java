package com.example.gymhiro.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.gymhiro.R;
import com.example.gymhiro.classes.Serie;
import com.example.gymhiro.classes.Training;
import com.example.gymhiro.classes.Utilities;
import com.google.gson.Gson;

public class WorkoutDetails extends AppCompatActivity {

    TextView workoutDate;
    TextView numberOfExercises;
    TextView numberOfSets;
    TextView numberOfRepetitions;
    TextView cumulativeWeight;
    Training workout;
    Utilities utilities = new Utilities();
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details);
        workout = gson.fromJson(getIntent().getStringExtra("WORKOUT"), Training.class);

        int weight = 0;
        for(int i = 0; i < workout.getListOfExercisesInTraining().size(); i++){
            for (int j = 0; j<workout.getListOfExercisesInTraining().get(i).getListOfSeriesInExercise().size(); j++){
                weight += workout.getListOfExercisesInTraining().get(i).getListOfSeriesInExercise().get(j).getNumberOfRepetitions()
                        * workout.getListOfExercisesInTraining().get(i).getListOfSeriesInExercise().get(j).getWeight();
            }
        }


        workoutDate = findViewById(R.id.workoutDetailsDateLabel);
        numberOfExercises = findViewById(R.id.workoutDetailsNoExercisesLabel);
        numberOfSets = findViewById(R.id.workoutDetailsNoSeriesLabel);
        numberOfRepetitions = findViewById(R.id.workoutDetailsNoRepeats);
        cumulativeWeight = findViewById(R.id.workoutDetailsWeightLabel);

        workoutDate.setText(utilities.dateIn_Y_M_D_Format(workout.getDate()));

        numberOfExercises.setText(String.valueOf(workout.getListOfExercisesInTraining().size()));

        numberOfSets.setText(String.valueOf(workout.getListOfExercisesInTraining().stream()
                    .mapToInt(p -> Integer.parseInt(p.numberOfSeries())).sum()));

        numberOfRepetitions.setText(String.valueOf(workout.getListOfExercisesInTraining().stream()
                        .mapToInt(exercise ->exercise.getListOfSeriesInExercise().stream()
                        .mapToInt(Serie::getNumberOfRepetitions).sum()).sum()));

        cumulativeWeight.setText(String.valueOf(weight));


    }
}