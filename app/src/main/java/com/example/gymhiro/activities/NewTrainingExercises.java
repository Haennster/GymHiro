package com.example.gymhiro.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymhiro.R;
import com.example.gymhiro.classes.Exercise;
import com.example.gymhiro.classes.Utilities;
import com.example.gymhiro.database.DatabaseHelper;
import com.example.gymhiro.exercisesDetails.ExerciseDetailsInterface;
import com.example.gymhiro.exercisesDetails.ExercisesDetailsRVAdapter;

import java.util.ArrayList;

public class NewTrainingExercises extends AppCompatActivity implements ExerciseDetailsInterface {


    ArrayList<Exercise> exercises = new ArrayList<>();
    TextView textView;

    SharedPreferences pref;
    SharedPreferences.Editor ed;
    Utilities utils = new Utilities();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_training_exercises);
        pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        ed = pref.edit();

        DatabaseHelper myDB = new DatabaseHelper(this);
        textView = findViewById(R.id.exercisesName2);
        RecyclerView recyclerView = findViewById(R.id.exercisesRV2);


        String muscleGroup = getIntent().getStringExtra("MUSCLE_GROUP");
        setUpExercises(myDB, muscleGroup);
        textView.setText(muscleGroup);


        ExercisesDetailsRVAdapter adapter = new ExercisesDetailsRVAdapter(this, exercises, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpExercises(DatabaseHelper myDB, String muscleGroup){
        exercises = myDB.getExercisesByCategory(muscleGroup);

        if( exercises == null){
            exercises = new ArrayList<>();
            Toast.makeText(this, "Nie ma jeszcze żadnych ćwiczeń w tej sekcji. \n Przepraszamy.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onItemClick(int position) {
        DatabaseHelper myDB = new DatabaseHelper(this);
        ArrayList<Exercise> activeTraining;
        Exercise exercise = getExerciseWithID(myDB,exercises.get(position).get_ID());

        if(pref.getString("active_training","").equals("")){
             activeTraining = new ArrayList<>();
             activeTraining.add(exercise);
        }else{
            activeTraining = utils.getExerciseArrayListFromJSON(pref.getString("active_training",""));
            activeTraining.add(exercise);
        }

        ed.putString("active_training", utils.createJsonFromExercisesArrayList(activeTraining));
        ed.putBoolean("new_training_active", true);
        ed.apply();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragToLoad", 1);

        startActivity(intent);
    }

    private Exercise getExerciseWithID(DatabaseHelper myDB, int id){
        Exercise exercise;
        Cursor cursor = myDB.getExerciseById(id);
        if (cursor.getCount() == 0){
            return null;
        }
        else{

            cursor.moveToFirst();
            exercise = new Exercise(cursor.getInt(0), cursor.getString(1), cursor.getString(2) );

        }
        return exercise;
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.main_fragment, fragment);
        fragmentTransaction.commit();

    }
}