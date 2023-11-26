package com.example.gymhiro.database;

import android.provider.BaseColumns;

public class CategoriesContract {
    public static final String SQL_CREATE_CATEGORIES = "CREATE TABLE " +
            Categories.TABLE_NAME + "(" + Categories._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Categories.COLUMN_NAME_CATEGORY_NAME + " TEXT)";
    public static final String SQL_DELETE_CATEGORIES = "DROP TABLE IF EXISTS "
            + Categories.TABLE_NAME;

    private CategoriesContract() {
    }

    public static class Categories implements BaseColumns{
        public static final String TABLE_NAME = "categories";
        public static final String COLUMN_NAME_CATEGORY_NAME = "name";
    }
}
