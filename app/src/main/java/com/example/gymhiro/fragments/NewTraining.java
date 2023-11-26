package com.example.gymhiro.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gymhiro.R;
import com.example.gymhiro.activities.NewTrainingMuscleGroup;
import com.example.gymhiro.classes.Exercise;
import com.example.gymhiro.classes.Training;
import com.example.gymhiro.classes.Utilities;
import com.example.gymhiro.viewPager2.NewTrainingViewPager2Adapter;

import java.util.ArrayList;
import java.util.Date;


public class NewTraining extends Fragment {


    Button newTraining;
    Button finishTraining;
    ViewPager2 viewPager2;
    ArrayList<Exercise> exercisesInCurrentTraining;
    SharedPreferences pref;
    SharedPreferences.Editor ed;
    Utilities utils = new Utilities();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_new_training, container, false);

        return  v;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        newTraining = v.findViewById(R.id.newTraining);
        finishTraining = v.findViewById(R.id.finishTrainingBtn);
        viewPager2 = v.findViewById(R.id.viewPager2newTraining);
        pref = getActivity().getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        ed = pref.edit();
        setUpExercisesInWorkout();

        NewTrainingViewPager2Adapter viewPager2Adapter = new NewTrainingViewPager2Adapter(getActivity(), exercisesInCurrentTraining);

        if (exercisesInCurrentTraining.size() > 0) {
            viewPager2.setAdapter(viewPager2Adapter);
            viewPager2Adapter.notifyDataSetChanged();
            viewPager2.setClipToPadding(false);
            viewPager2.setClipChildren(false);
            viewPager2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            viewPager2.setOffscreenPageLimit(2);
            viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        }

        newTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Exercise> exercises = viewPager2Adapter.exercises;
                if(!exercises.isEmpty()){
                ed.putString("active_training", utils.createJsonFromExercisesArrayList(exercises));
                ed.apply();
                }
                Intent intent = new Intent(getActivity(), NewTrainingMuscleGroup.class);
                startActivity(intent);
            }
        });

        finishTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Exercise> exercises = viewPager2Adapter.exercises;
                ArrayList<Training> trainingHistory;
                if(exercises.isEmpty())
                {
                    ed.putBoolean("new_training_active", false);
                    ed.putString("active_training", "");
                    ed.apply();
                    replaceFragment(new TrainingFragment());
                }else{
                    if(!pref.getString("history", "").equals("")){
                        trainingHistory = utils.getTrainingArrayListFromJSON(pref.getString("history", ""));
                    }
                    else{
                        trainingHistory = new ArrayList<>();
                    }
                    trainingHistory.add(new Training(new Date(), exercises));
                    ed.putString("history", utils.createJsonFromTrainingArrayList(trainingHistory));
                    ed.putString("last_training", utils.createJsonFromExercisesArrayList(exercises));
                    ed.putString("active_training", "");
                    ed.putBoolean("new_training_active", false);
                    ed.apply();
                    replaceFragment(new TrainingFragment());
                }



            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(pref.getBoolean("new_training_active",false))
        {
            ed.putString("active_training",utils.createJsonFromExercisesArrayList(exercisesInCurrentTraining));
        }

    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.main_fragment, fragment);
        fragmentTransaction.commit();

    }

    public void setUpExercisesInWorkout(){
        if(pref.getString("active_training","").isEmpty()){
            exercisesInCurrentTraining = new ArrayList<>();
        }else {
            exercisesInCurrentTraining = utils.getExerciseArrayListFromJSON(pref.getString("active_training",""));
        }
    }

}