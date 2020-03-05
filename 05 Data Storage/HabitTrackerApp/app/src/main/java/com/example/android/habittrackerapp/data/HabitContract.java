package com.example.android.habittrackerapp.data;

public final class HabitContract {

    public static class HabitEntry{

        public static String ID = "id";
        public static String TABLE_NAME = "habits";
        public static String COLUMN_HABIT_NAME = "name";
        public static String COLUMN_HABIT_PRIORITY = "priority";
        public static String COLUMN_HABIT_STARTDATE = "date";

        public static int PRIORITY_HIGH = 3;
        public static int PRIORITY_MEDIUM = 2;
        public static int PRIORITY_LOW = 1;
    }
}
