package com.example.gymhiro.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.gymhiro.R;
import com.example.gymhiro.classes.Exercise;
import com.example.gymhiro.database.DatabaseHelper;

public class ExerciseInformation extends AppCompatActivity {

    TextView exerciseName;
    Exercise exercise;
    Button youtubeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_information);
        DatabaseHelper myDB = new DatabaseHelper(this);

        Intent intent= getIntent();
        int exerciseID = intent.getIntExtra("EXERCISE_NAME", 0);
        exerciseName = findViewById(R.id.ExerciseName);
        youtubeButton = findViewById(R.id.watchOnYoutube);


        findExercise(myDB, exerciseID);





        VideoView videoView = findViewById(R.id.videoView);
        String videoPath = "android.resource://" + getPackageName() +"/" + R.raw.leg_press;

        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);


        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEARCH);
                intent.setPackage("com.google.android.youtube");
                intent.putExtra("query",
                            "jak wykonać ćwiczenie+"+
                                    exerciseName.getText().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    void findExercise(DatabaseHelper myDB, int exerciseID){
        Cursor cursor = myDB.getExerciseById(exerciseID);


        if (cursor.getCount() == 0){
            Toast.makeText(this, "Nie ma jeszcze żadnych ćwiczeń w tej sekcji. \n Przepraszamy.", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()) {
                exercise = new Exercise(cursor.getInt(0), cursor.getString(1), cursor.getString(2));

            }
            exerciseName.setText(exercise.getNameOfExercise());
        }
    }
}
