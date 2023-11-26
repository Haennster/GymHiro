package com.example.gymhiro.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.gymhiro.classes.Exercise;
import com.example.gymhiro.classes.UserBodyMeasurements;
import com.example.gymhiro.classes.UserGeneralData;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "GymHiro.db";
    private static final int DATABASE_VERSION = 6;



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CategoriesContract.SQL_CREATE_CATEGORIES);
        db.execSQL(UserContract.SQL_CREATE_USERGENERAL);
        db.execSQL(UserContract.SQL_CREATE_USERMEASUREMENT);
        db.execSQL(ExercisesContract.SQL_CREATE_EXERCISES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(CategoriesContract.SQL_DELETE_CATEGORIES);
        db.execSQL(UserContract.SQL_DELETE_USERGENERAL);
        db.execSQL(UserContract.SQL_DELETE_USERMEASUREMENT);
        db.execSQL(ExercisesContract.SQL_DELETE_EXERCISES);

        onCreate(db);
    }

    public void addCategory(String categoryName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CategoriesContract.Categories.COLUMN_NAME_CATEGORY_NAME, categoryName);
        db.insert(CategoriesContract.Categories.TABLE_NAME, null, cv);
        db.close();
    }
    public void addExercise(String exerciseName, int categoryId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ExercisesContract.Exercises.COLUMN_NAME_EXERCISE_NAME, exerciseName);
        cv.put(ExercisesContract.Exercises.COLUMN_NAME_CATEGORY, categoryId);

        db.insert(ExercisesContract.Exercises.TABLE_NAME, null, cv);
    }

    public Cursor allCategories(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " +CategoriesContract.Categories.TABLE_NAME;


        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    public ArrayList<Exercise> getExercisesByCategory(String category){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Exercise> exercises = new ArrayList<>();
        String query = "SELECT * FROM " + ExercisesContract.Exercises.TABLE_NAME + " JOIN " +
                CategoriesContract.Categories.TABLE_NAME + " ON " + ExercisesContract.Exercises.TABLE_NAME + "." +
                ExercisesContract.Exercises.COLUMN_NAME_CATEGORY + " = " +  CategoriesContract.Categories.TABLE_NAME + "." +
                CategoriesContract.Categories._ID + " WHERE " +
                CategoriesContract.Categories.COLUMN_NAME_CATEGORY_NAME + " IN(\"" + category + "\")";

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        if (cursor.moveToFirst()){
            do {
                exercises.add(new Exercise(cursor.getInt(0), cursor.getString(1), category));
            }while(cursor.moveToNext());
            return exercises;
        }
        return null;
    }

    public Cursor getExerciseById(int exerciseId){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT "
                + ExercisesContract.Exercises.TABLE_NAME + "." + ExercisesContract.Exercises._ID + ", "
                + ExercisesContract.Exercises.TABLE_NAME + "." + ExercisesContract.Exercises.COLUMN_NAME_EXERCISE_NAME+ ", "
                + CategoriesContract.Categories.TABLE_NAME + "." + CategoriesContract.Categories.COLUMN_NAME_CATEGORY_NAME +
                " FROM "
                + ExercisesContract.Exercises.TABLE_NAME +" join " + CategoriesContract.Categories.TABLE_NAME + " on "
                + ExercisesContract.Exercises.TABLE_NAME + "." + ExercisesContract.Exercises.COLUMN_NAME_CATEGORY + " = "
                + CategoriesContract.Categories.TABLE_NAME + "." + CategoriesContract.Categories._ID
                + " where " +ExercisesContract.Exercises.TABLE_NAME +"." + ExercisesContract.Exercises._ID + " = " + exerciseId;

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }
    public UserGeneralData getLastUserGeneral(){
        UserGeneralData userGeneralData;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + UserContract.UserGeneral.TABLE_NAME + " order by "
                + UserContract.UserGeneral.COLUMN_NAME_DATE + " limit 1";

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                userGeneralData = new UserGeneralData(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getInt(3));
            } while (cursor.moveToNext());
            cursor.close();
        return userGeneralData;
        }
        cursor.close();
        return null;
    }
    public ArrayList<UserGeneralData> getAllUserGeneral(){
        ArrayList<UserGeneralData> userGeneralData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + UserContract.UserGeneral.TABLE_NAME;

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                userGeneralData.add(new UserGeneralData(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getInt(3)));
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
            return userGeneralData;
        }
        cursor.close();
        db.close();
        return userGeneralData;
    }

    public void insertNewUserGeneral(UserGeneralData userGeneralData){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(UserContract.UserGeneral.COLUMN_NAME_DATE, userGeneralData.getDate());
        cv.put(UserContract.UserGeneral.COLUMN_NAME_WEIGHT, userGeneralData.getWeight());
        cv.put(UserContract.UserGeneral.COLUMN_NAME_HEIGHT, userGeneralData.getHeight());

        db.insert(UserContract.UserGeneral.TABLE_NAME, null, cv);
        db.close();
    }
    public void updateUserGeneral(UserGeneralData userGeneralData){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(UserContract.UserGeneral.COLUMN_NAME_DATE, userGeneralData.getDate());
        cv.put(UserContract.UserGeneral.COLUMN_NAME_WEIGHT, userGeneralData.getWeight());
        cv.put(UserContract.UserGeneral.COLUMN_NAME_HEIGHT, userGeneralData.getHeight());

        db.update(UserContract.UserGeneral.TABLE_NAME, cv,
                UserContract.UserGeneral._ID + "=" + userGeneralData.getId(),
                null);
        db.close();
    }

    public UserBodyMeasurements getLastUserBodyMeasurements(){
        UserBodyMeasurements userBodyMeasurements;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + UserContract.UserMeasurements.TABLE_NAME + " order by "
                + UserContract.UserMeasurements.COLUMN_NAME_DATE + " limit 1";

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                userBodyMeasurements = new UserBodyMeasurements(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getDouble(3),
                        cursor.getDouble(4),
                        cursor.getDouble(5),
                        cursor.getDouble(6),
                        cursor.getDouble(7),
                        cursor.getDouble(8),
                        cursor.getDouble(9));
            } while (cursor.moveToNext());
            cursor.close();
            return userBodyMeasurements;
        }
        cursor.close();
        db.close();
        return null;
    }
    public ArrayList<UserBodyMeasurements> getAllUserBodyMeasurements(){

        ArrayList<UserBodyMeasurements> userBodyMeasurements = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + UserContract.UserMeasurements.TABLE_NAME;

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                userBodyMeasurements.add(new UserBodyMeasurements(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getDouble(3),
                        cursor.getDouble(4),
                        cursor.getDouble(5),
                        cursor.getDouble(6),
                        cursor.getDouble(7),
                        cursor.getDouble(8),
                        cursor.getDouble(9)));
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
            return userBodyMeasurements;
        }
        cursor.close();
        db.close();
        return userBodyMeasurements;
    }
    public void insertNewBodyMeasurements(UserBodyMeasurements userBodyMeasurements){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(UserContract.UserMeasurements.COLUMN_NAME_DATE,userBodyMeasurements.getDate());
        cv.put(UserContract.UserMeasurements.COLUMN_NAME_L_BICEPS,userBodyMeasurements.getlBiceps());
        cv.put(UserContract.UserMeasurements.COLUMN_NAME_R_BICEPS,userBodyMeasurements.getrBiceps());
        cv.put(UserContract.UserMeasurements.COLUMN_NAME_CHEST,userBodyMeasurements.getChest());
        cv.put(UserContract.UserMeasurements.COLUMN_NAME_WAIST,userBodyMeasurements.getWaist());
        cv.put(UserContract.UserMeasurements.COLUMN_NAME_L_THIGH,userBodyMeasurements.getlThigh());
        cv.put(UserContract.UserMeasurements.COLUMN_NAME_R_THIGH,userBodyMeasurements.getrThigh());
        cv.put(UserContract.UserMeasurements.COLUMN_NAME_L_CALF,userBodyMeasurements.getlCalf());
        cv.put(UserContract.UserMeasurements.COLUMN_NAME_R_CALF,userBodyMeasurements.getrCalf());

        db.insert(UserContract.UserMeasurements.TABLE_NAME, null, cv);
        db.close();
    }
    public void updateUserBodyMeasures(UserBodyMeasurements userBodyMeasurements){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(UserContract.UserMeasurements.COLUMN_NAME_DATE,userBodyMeasurements.getDate());
        cv.put(UserContract.UserMeasurements.COLUMN_NAME_L_BICEPS,userBodyMeasurements.getlBiceps());
        cv.put(UserContract.UserMeasurements.COLUMN_NAME_R_BICEPS,userBodyMeasurements.getrBiceps());
        cv.put(UserContract.UserMeasurements.COLUMN_NAME_CHEST,userBodyMeasurements.getChest());
        cv.put(UserContract.UserMeasurements.COLUMN_NAME_WAIST,userBodyMeasurements.getWaist());
        cv.put(UserContract.UserMeasurements.COLUMN_NAME_L_THIGH,userBodyMeasurements.getlThigh());
        cv.put(UserContract.UserMeasurements.COLUMN_NAME_R_THIGH,userBodyMeasurements.getrThigh());
        cv.put(UserContract.UserMeasurements.COLUMN_NAME_L_CALF,userBodyMeasurements.getlCalf());
        cv.put(UserContract.UserMeasurements.COLUMN_NAME_R_CALF,userBodyMeasurements.getrCalf());

        db.update(UserContract.UserMeasurements.TABLE_NAME, cv,
                UserContract.UserMeasurements._ID + "=" + userBodyMeasurements.getId(),
                null);
        db.close();
    }
}
