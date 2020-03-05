package com.example.android.habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.habittrackerapp.data.HabitContract.HabitEntry;
import com.example.android.habittrackerapp.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    HabitDbHelper habitDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        habitDbHelper = new HabitDbHelper(this);

        insertHabit();
        readFromCursor(getCursor());

    }

    private Cursor getCursor() {
        SQLiteDatabase db = habitDbHelper.getReadableDatabase();

        String[] projection = {HabitEntry.ID, HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_PRIORITY,  HabitEntry.COLUMN_HABIT_STARTDATE,};

        Cursor cursor = db.query(HabitEntry.TABLE_NAME, projection, null, null, null, null, null);

        return cursor;
    }

    private void readFromCursor(Cursor cursor){
        try {
            int idColumnIndex = cursor.getColumnIndex(HabitEntry.ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int priorityColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_PRIORITY);
            int startdateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_STARTDATE);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentPriority = cursor.getInt(priorityColumnIndex);
                String currentStartdate = cursor.getString(startdateColumnIndex);
                Log.d("CURRENT ROW",currentID+" "+currentName+" "+currentPriority+" "+currentStartdate);
            }
        }finally {
            cursor.close();
        }
    }

    private void insertHabit() {
        SQLiteDatabase db = habitDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "Go to bed before 24h");
        values.put(HabitEntry.COLUMN_HABIT_PRIORITY, HabitEntry.PRIORITY_LOW);
        values.put(HabitEntry.COLUMN_HABIT_STARTDATE, System.currentTimeMillis());
        db.insert(HabitEntry.TABLE_NAME, null, values);

    }
}
