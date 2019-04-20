package com.example.nemesis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.lang.UCharacter;


public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "sqlite_workout_database";
    //private static final int DB_VERSION = 2;
    private static final int DB_VERSION = 1;

    public MyDBHelper(Context ctx){

        super(ctx,DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+
                WorkoutsEntry.WORKOUT_TABLE_NAME +"(" +
                WorkoutsEntry.ID_COLUMN + " integer primary key autoincrement," +
                WorkoutsEntry.WORKOUTNAME_COLUMN + " text not null, " +
                WorkoutsEntry.MUSCLEGROUP_COLUMN + " text, " +
                WorkoutsEntry.SETS_COLUMN + " integer, " +
                WorkoutsEntry.REPS_COLUMN + " integer" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If upgrade is needed, just drop table
        db.execSQL("drop table "+ WorkoutsEntry.WORKOUT_TABLE_NAME+";");
        onCreate(db);

    }
}
