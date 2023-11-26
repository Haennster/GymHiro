package com.example.gymhiro.database;

import android.provider.BaseColumns;

public class ExercisesContract {

    public static final String SQL_CREATE_EXERCISES = "CREATE TABLE " + ExercisesContract.Exercises.TABLE_NAME + "(" +
            ExercisesContract.Exercises._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            Exercises.COLUMN_NAME_EXERCISE_NAME + " TEXT,"+
            Exercises.COLUMN_NAME_CATEGORY + " INTEGER,"+
            "FOREIGN KEY("+Exercises.COLUMN_NAME_CATEGORY+") REFERENCES "+
            CategoriesContract.Categories.TABLE_NAME+"("+ CategoriesContract.Categories._ID +"))";


    public static final String SQL_DELETE_EXERCISES = "DROP TABLE IF EXISTS "
            + ExercisesContract.Exercises.TABLE_NAME;

    private ExercisesContract() {
    }

    public static class Exercises implements BaseColumns{
        public static final String TABLE_NAME = "exercises";
        public static final String COLUMN_NAME_EXERCISE_NAME = "exerciseName";
        public static final String COLUMN_NAME_CATEGORY = "categoryId";
    }
}
