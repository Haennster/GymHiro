package com.example.gymhiro.database;

import android.provider.BaseColumns;

public class UserContract {
    public static final String SQL_CREATE_USERGENERAL = "CREATE TABLE " + UserGeneral.TABLE_NAME + "(" +
            UserGeneral._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            UserGeneral.COLUMN_NAME_DATE + " TEXT,"+
            UserGeneral.COLUMN_NAME_WEIGHT + " REAL,"+
            UserGeneral.COLUMN_NAME_HEIGHT + " INTEGER)";

    public static final String SQL_DELETE_USERGENERAL = "DROP TABLE IF EXISTS "
            + UserContract.UserGeneral.TABLE_NAME;

    public static final String SQL_CREATE_USERMEASUREMENT = "CREATE TABLE " + UserMeasurements.TABLE_NAME + "(" +
            UserMeasurements._ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            UserMeasurements.COLUMN_NAME_DATE + " TEXT," +
            UserMeasurements.COLUMN_NAME_L_BICEPS + " REAL,"+
            UserMeasurements.COLUMN_NAME_R_BICEPS + " REAL,"+
            UserMeasurements.COLUMN_NAME_CHEST + " REAL,"+
            UserMeasurements.COLUMN_NAME_WAIST + " REAL,"+
            UserMeasurements.COLUMN_NAME_L_THIGH + " REAL,"+
            UserMeasurements.COLUMN_NAME_R_THIGH + " REAL,"+
            UserMeasurements.COLUMN_NAME_L_CALF + " REAL,"+
            UserMeasurements.COLUMN_NAME_R_CALF + " REAL)";

    public static final String SQL_DELETE_USERMEASUREMENT = "DROP TABLE IF EXISTS "
            + UserContract.UserMeasurements.TABLE_NAME;

    private UserContract() {
    }
    public static class UserGeneral implements BaseColumns{
        public static final String TABLE_NAME = "userGeneral";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_WEIGHT = "weight";
        public static final String COLUMN_NAME_HEIGHT = "height";
    }
    public static class UserMeasurements implements BaseColumns{
        public static final String TABLE_NAME = "userMeasurements";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_L_BICEPS = "lBiceps";
        public static final String COLUMN_NAME_R_BICEPS = "rBiceps";
        public static final String COLUMN_NAME_CHEST = "chest";
        public static final String COLUMN_NAME_WAIST = "waist";
        public static final String COLUMN_NAME_L_THIGH = "lThigh";
        public static final String COLUMN_NAME_R_THIGH = "rThigh";
        public static final String COLUMN_NAME_L_CALF = "lCalf";
        public static final String COLUMN_NAME_R_CALF = "rCalf";

    }
}
