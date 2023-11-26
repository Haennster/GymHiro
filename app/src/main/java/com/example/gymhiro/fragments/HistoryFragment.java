package com.example.gymhiro.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymhiro.R;
import com.example.gymhiro.activities.WorkoutDetails;
import com.example.gymhiro.classes.Training;
import com.example.gymhiro.classes.Utilities;
import com.example.gymhiro.historyRV.HistoryInterface;
import com.example.gymhiro.historyRV.HistoryRVAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;


public class HistoryFragment extends Fragment implements HistoryInterface {

    SharedPreferences pref;
    SharedPreferences.Editor ed;
    RecyclerView recyclerView;
    ArrayList<Training> workouts;
    HistoryRVAdapter historyRVAdapter;
    Utilities utilities = new Utilities();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history, container, false);


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pref = getActivity().getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        ed = pref.edit();
        recyclerView = getView().findViewById(R.id.historyRV);
        setUpWorkouts();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        historyRVAdapter = new HistoryRVAdapter(this, getContext(),workouts);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(historyRVAdapter);
    }

    public void setUpWorkouts(){

       workouts = utilities.getTrainingArrayListFromJSON(pref.getString("history", ""));
        Collections.reverse(workouts);

    }

    @Override
    public void onItemClick(int position) {
        Gson gson = new Gson();

        Intent intent = new Intent(getActivity(), WorkoutDetails.class);
        intent.putExtra("WORKOUT", gson.toJson(workouts.get(position)));

        startActivity(intent);

    }

    @Override
    public void onLongItemClick(int position) {
        workouts.remove(position);
        historyRVAdapter.notifyItemRemoved(position);
        ed.putString("history", utilities.createJsonFromTrainingArrayList(workouts));
        ed.apply();
    }
}