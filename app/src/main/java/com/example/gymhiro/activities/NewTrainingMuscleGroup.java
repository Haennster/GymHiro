package com.example.gymhiro.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gymhiro.R;
import com.example.gymhiro.classes.MuscleGroupModel;
import com.example.gymhiro.database.DatabaseHelper;
import com.example.gymhiro.muscleGroup.MuscleGroupInterface;
import com.example.gymhiro.muscleGroup.MuscleGroupRVAdapter;

import java.util.ArrayList;

public class NewTrainingMuscleGroup extends AppCompatActivity implements MuscleGroupInterface {

    RecyclerView recyclerView;
    ArrayList<MuscleGroupModel> muscleGroupModel = new ArrayList<>();
    Cursor cursor;
    public int categoriesLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_training_muscle_group);

        recyclerView = findViewById(R.id.muscleGroup_Recycler2);
        setUpModelGroups();
        MuscleGroupRVAdapter adapter = new MuscleGroupRVAdapter(this, muscleGroupModel, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    private void setUpModelGroups(){
        DatabaseHelper myDB = new DatabaseHelper(this);
        cursor = myDB.allCategories();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Nie ma jeszcze żadnych ćwiczeń w tej sekcji. \n Przepraszamy.", Toast.LENGTH_SHORT).show();
        }
        else{
            categoriesLength = cursor.getCount();
            while (cursor.moveToNext()) {
                muscleGroupModel.add(new MuscleGroupModel(cursor.getInt(0), cursor.getString(1)));
            }
        }

    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(this, NewTrainingExercises.class);
        intent.putExtra("MUSCLE_GROUP", muscleGroupModel.get(position).getName());

        startActivity(intent);

    }
}