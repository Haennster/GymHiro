package com.example.gymhiro.classes;

import java.util.ArrayList;

public class Exercise {

    int _ID;
    String nameOfExercise;
    String muscleGroupCategory;

    ArrayList<Serie> listOfSeriesInExercise ;


    public Exercise( String nameOfExercise, String muscleGroupCategory) {
        this.nameOfExercise = nameOfExercise;
        this.muscleGroupCategory = muscleGroupCategory;
        this.listOfSeriesInExercise = new ArrayList<>();
        this.listOfSeriesInExercise.add(new Serie(10, 0));
    }
    public Exercise(int _ID, String nameOfExercise, String muscleGroupCategory) {
        this._ID=_ID;
        this.nameOfExercise = nameOfExercise;
        this.muscleGroupCategory = muscleGroupCategory;
        this.listOfSeriesInExercise = new ArrayList<>();
        this.listOfSeriesInExercise.add(new Serie(10, 0));
    }

    public String getNameOfExercise() {
        return nameOfExercise;
    }

    public void setNameOfExercise(String nameOfExercise) {
        this.nameOfExercise = nameOfExercise;
    }

    public String getMuscleGroupCategory() {
        return muscleGroupCategory;
    }

    public void setMuscleGroupCategory(String muscleGroupCategory) {
        this.muscleGroupCategory = muscleGroupCategory;
    }

    public ArrayList<Serie> getListOfSeriesInExercise() {
        return listOfSeriesInExercise;
    }

    public void setListOfSeriesInExercise(ArrayList<Serie> listOfSeriesInExercise) {
        this.listOfSeriesInExercise = listOfSeriesInExercise;
    }

    public void removeLastSerie(){
        listOfSeriesInExercise.remove(listOfSeriesInExercise.size() - 1);
    }
    public void addSerie(Serie serie){
        this.listOfSeriesInExercise.add(serie);
    }

    public String numberOfSeries(){
        return ""+listOfSeriesInExercise.size()+"";
    }

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }


}
