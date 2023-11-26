package com.example.gymhiro.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymhiro.R;
import com.example.gymhiro.classes.Exercise;
import com.example.gymhiro.database.DatabaseHelper;
import com.example.gymhiro.exercisesDetails.ExerciseDetailsInterface;
import com.example.gymhiro.exercisesDetails.ExercisesDetailsRVAdapter;

import java.util.ArrayList;

public class ExercisesDetails extends AppCompatActivity implements ExerciseDetailsInterface {

    ArrayList<Exercise> exercises = new ArrayList<>();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_details);

        DatabaseHelper myDB = new DatabaseHelper(this);
        textView = findViewById(R.id.exerciseName);
        RecyclerView recyclerView = findViewById(R.id.exercisesRV);


        String muscleGroup = getIntent().getStringExtra("MUSCLE_GROUP");
        setUpExercises(myDB, muscleGroup);
        textView.setText(muscleGroup);


        ExercisesDetailsRVAdapter adapter = new ExercisesDetailsRVAdapter(this, exercises, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void setUpExercises(DatabaseHelper myDB, String muscleGroup){
        exercises = myDB.getExercisesByCategory(muscleGroup);

        if(exercises == null){
            exercises = new ArrayList<>();
            Toast.makeText(this, "Nie ma jeszcze żadnych ćwiczeń w tej sekcji. \n Przepraszamy.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(ExercisesDetails.this, ExerciseInformation.class);
        intent.putExtra("EXERCISE_NAME", exercises.get(position).get_ID());

        startActivity(intent);
    }
}