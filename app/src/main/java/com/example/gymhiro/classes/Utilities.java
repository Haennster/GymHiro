package com.example.gymhiro.classes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Utilities {


    public String createJsonFromExercisesArrayList(ArrayList<Exercise> list){
        return new Gson().toJson(list);
    }
    public ArrayList<Exercise> getExerciseArrayListFromJSON(String string){
        ArrayList<Exercise> exercises;

        exercises = new Gson().fromJson(string, new TypeToken<ArrayList<Exercise>>(){}.getType());

        return exercises;
    }

    public String createJsonFromTrainingArrayList(ArrayList<Training> list){
        return new Gson().toJson(list);
    }
    public ArrayList<Training> getTrainingArrayListFromJSON(String string){
        ArrayList<Training> trainings;

        trainings = new Gson().fromJson(string, new TypeToken<ArrayList<Training>>(){}.getType());

        return trainings;
    }

    public String dateIn_Y_M_D_Format(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return formatter.format(date);
    }

    public String dateIn_Y_M_D_H_M_S_Format(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return formatter.format(date);
    }

}
