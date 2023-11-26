package com.example.gymhiro.classes;

import java.util.ArrayList;
import java.util.Date;

public class Training {

    Date date;
    ArrayList<Exercise> listOfExercisesInTraining;

    public Training(Date date, ArrayList<Exercise> exercise) {

        this.date = date;
        this.listOfExercisesInTraining = exercise ;
    }


    public ArrayList<Exercise> getListOfExercisesInTraining() {
        return listOfExercisesInTraining;
    }

    public void setListOfExercisesInTraining(ArrayList<Exercise> listOfExercisesInTraining) {
        this.listOfExercisesInTraining = listOfExercisesInTraining;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
