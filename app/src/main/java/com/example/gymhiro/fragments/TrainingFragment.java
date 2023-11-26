package com.example.gymhiro.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gymhiro.R;
import com.example.gymhiro.classes.Exercise;
import com.example.gymhiro.classes.Training;
import com.example.gymhiro.classes.Utilities;
import com.example.gymhiro.lastTraining.LastTrainingRVAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;


public class TrainingFragment extends Fragment {

    RecyclerView recyclerView;
    Training lastTraining;
    TextView date;
    Button newTraining;
    Utilities utils = new Utilities();
    SharedPreferences pref;
    SharedPreferences.Editor ed;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        date = getActivity().findViewById(R.id.lastTrainingDate);
        newTraining = getActivity().findViewById(R.id.start_training);
        recyclerView = view.findViewById(R.id.lastTrainingRecycler);
        pref = getActivity().getSharedPreferences("ActivityPREF",Context.MODE_PRIVATE);
        ed = pref.edit();


        setUpLastTraining();
        if(lastTraining.getListOfExercisesInTraining() != null) {
            date.setText("Trening - " + utils.dateIn_Y_M_D_Format(lastTraining.getDate()));
        }
        else{
            date.setText("Nie odbyto jeszcze żadnego treningu");
        }
        LastTrainingRVAdapter adapter = new LastTrainingRVAdapter(getActivity(), lastTraining);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        newTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new NewTraining());
            }
        });
    }

    private void setUpLastTraining(){

        Gson gson = new Gson();
        ArrayList<Exercise> exercises ;

        exercises = utils.getExerciseArrayListFromJSON(pref.getString("last_training", null));

        lastTraining = new Training(new Date(), exercises);

    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.main_fragment, fragment);
        fragmentTransaction.commit();

    }
}