package com.example.gymhiro.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gymhiro.activities.ExercisesDetails;
import com.example.gymhiro.database.DatabaseHelper;
import com.example.gymhiro.muscleGroup.MuscleGroupInterface;
import com.example.gymhiro.classes.MuscleGroupModel;
import com.example.gymhiro.muscleGroup.MuscleGroupRVAdapter;
import com.example.gymhiro.R;

import java.util.ArrayList;


public class ExerciseFragment extends Fragment implements MuscleGroupInterface {


    RecyclerView recyclerView;
    ArrayList<MuscleGroupModel> muscleGroupModel = new ArrayList<>();
    Cursor cursor;
    public int categoriesLength;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    private void setUpModelGroups(){
        DatabaseHelper myDB = new DatabaseHelper(getActivity());
        cursor = myDB.allCategories();
        if (cursor.getCount() == 0){
            Toast.makeText(getActivity(), "Nie ma jeszcze żadnych ćwiczeń w tej sekcji. \n Przepraszamy.", Toast.LENGTH_SHORT).show();
        }
        else{
            categoriesLength = cursor.getCount();
            while (cursor.moveToNext()) {
                muscleGroupModel.add(new MuscleGroupModel(cursor.getInt(0), cursor.getString(1)));
            }
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.muscleGroup_Recycler);
        setUpModelGroups();
        MuscleGroupRVAdapter adapter = new MuscleGroupRVAdapter(getActivity(), muscleGroupModel, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(getActivity(), ExercisesDetails.class);
        intent.putExtra("MUSCLE_GROUP", muscleGroupModel.get(position).getName());

        startActivity(intent);
    }
}