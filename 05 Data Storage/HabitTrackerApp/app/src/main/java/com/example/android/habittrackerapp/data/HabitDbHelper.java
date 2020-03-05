package com.example.android.habittrackerapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittrackerapp.data.HabitContract.HabitEntry;

public class HabitDbHelper extends SQLiteOpenHelper {

    private static int VERSION_NUMBER = 1;
    private static String DB_NAME = "habit.db";

    public HabitDbHelper(Context context) {
        super(context, DB_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                +HabitEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                +HabitEntry.COLUMN_HABIT_PRIORITY + " INTEGER DEFAULT "+ HabitEntry.PRIORITY_MEDIUM + ", "
                +HabitEntry.COLUMN_HABIT_STARTDATE +" TEXT NOT NULL);";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}
