package com.example.nemesis;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABSE_VERSION = 1;
    private static final String DATABASE_NAME = "workout_manager";
    private static final String TABLE_WORKOUTS = "workouts";
    private static final String KEY_NAME = "name";
    private static final String KEY_MUSCLE = "muscleGroup";
    private static final String KEY_WORKOUTID = "id";
    private static final String KEY_REPS = "reps";
    private static final String KEY_SETS = "sets";

    Workouts myhelper;

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABSE_VERSION);
        myhelper = new Workouts(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WORKOUT_TABLE = "CREATE TABLE" + TABLE_WORKOUTS + "("
                + KEY_WORKOUTID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_MUSCLE + " TEXT," + KEY_REPS + " INTEGER,"
                + KEY_SETS + " INTEGER" + ")";
        db.execSQL(CREATE_WORKOUT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUTS);

        onCreate(db);
    }

    //ADD NEW ROW TO THE DATABASE.
    void addWorkout(Workouts workouts) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, workouts.getName());
        values.put(KEY_MUSCLE, workouts.getMuscleGroup());
        values.put(KEY_REPS, workouts.getReps());
        values.put(KEY_SETS, workouts.getSets());
        values.put(KEY_WORKOUTID, workouts.getId());

        db.insert(TABLE_WORKOUTS, null, values);
        db.close();
    }


    Workouts getWorkouts(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WORKOUTS, new String[] {KEY_WORKOUTID, KEY_NAME, KEY_MUSCLE, KEY_SETS, KEY_REPS},
                KEY_WORKOUTID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if(cursor != null) {
            cursor.moveToFirst();
        }

        Workouts workouts = new Workouts(cursor.getString(1), cursor.getString(2),
                Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));

        return workouts;
    }




//    public List<Workouts> getAllWorkouts() {
//        List<Workouts> workoutsList = new ArrayList<>();

//        String selectQuery = "SELECT *FROM" + TABLE_WORKOUTS;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);

//        if(cursor.moveToFirst()) {
//            do {
//                Workouts workout = new Workouts();
//                workout .setId(Integer.parseInt(cursor.getString));
//            }
//        }
//    }


    public int updateWorkouts(Workouts workouts) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, workouts.getName());
        values.put(KEY_MUSCLE, workouts.getMuscleGroup());
        values.put(KEY_SETS, workouts.getSets());
        values.put(KEY_REPS, workouts.getReps());

        return db.update(TABLE_WORKOUTS, values, KEY_WORKOUTID + "=?",
                new String[]{String.valueOf(workouts.getId())});
    }

    //DELETE WORKOUT FROM DATABASE
    public void deleteWorkout(Workouts workouts) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORKOUTS, KEY_WORKOUTID + "=?",
                new String[]{String.valueOf(workouts.getId())});
        db.close();
    }

    //PRINT THE NAME OF WORKOUT AS A STRING
    public String WorkoutName(int id) {     //(String Name)
        String dbName = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + KEY_NAME
                                + " FROM " + TABLE_WORKOUTS
                                + " WHERE " + KEY_WORKOUTID //KEY_NAME
                                + " = " + id;

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();
        db.close();
        return c.getString(c.getColumnIndex(KEY_NAME));
    }


    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT name FROM " + TABLE_WORKOUTS + "WHERE 1";

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while(!c.isAfterLast()) {
            if(c.getString(c.getColumnIndex("name")) != null) {
                dbString += c.getString(c.getColumnIndex("name"));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
    }

//close for main class
}



