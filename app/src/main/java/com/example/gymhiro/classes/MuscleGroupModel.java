package com.example.gymhiro.classes;

public class MuscleGroupModel {
    int _ID;
    String name;



    public MuscleGroupModel(int id, String name) {
        this._ID = id;
        this.name = name;

    }

    public int get_ID() {
        return _ID;
    }
    public String getName() {
        return name;
    }



}
