package com.example.tareamvc.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "employees.db";
    public static final String EMPLOYEES_TABLE = "employees";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + EMPLOYEES_TABLE + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "lastNames TEXT NOT NULL," +
                "age TEXT NOT NULL," +
                "address TEXT NOT NULL," +
                "position TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+ EMPLOYEES_TABLE);
        onCreate(db);
    }
}
