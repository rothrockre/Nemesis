package com.example.nemesis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyDB {
    private SQLiteDatabase db;

    public MyDB(Context context) {
        MyDBHelper helper = new MyDBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(Workouts workout) {
        ContentValues values = new ContentValues();
        values.put(WorkoutsEntry.WORKOUTNAME_COLUMN, workout.getName());
        values.put(WorkoutsEntry.MUSCLEGROUP_COLUMN, workout.getMuscleGroup());
        values.put(WorkoutsEntry.SETS_COLUMN, workout.getSets());
        values.put(WorkoutsEntry.REPS_COLUMN, workout.getReps());

        db.insert(WorkoutsEntry.WORKOUT_TABLE_NAME, null, values);
    }

    //used to retrieve a Workout product by their Name
    public Workouts getOne(String workoutName) {
        Workouts result = null;

        Cursor cursor = db.query(
                WorkoutsEntry.WORKOUT_TABLE_NAME,
                new String[] {
                        WorkoutsEntry.WORKOUTNAME_COLUMN,
                        WorkoutsEntry.MUSCLEGROUP_COLUMN,
                        WorkoutsEntry.SETS_COLUMN,
                        WorkoutsEntry.REPS_COLUMN
                },
                WorkoutsEntry.WORKOUTNAME_COLUMN + " =?",
                new String[]{workoutName},
                null,
                null,
                null
        );

        if (cursor.getCount() != 0) {
            while(cursor.moveToNext()) {
                String name = cursor.getString(0);
                String muscle = cursor.getString(1);
                int set = cursor.getInt(2);
                int reps = cursor.getInt(3);

                result = new Workouts(name, muscle, set, reps);
            }
        }
        else {
            return null;
        }

        cursor.close();

        return result;

    }

    public List<Workouts> get() {
        Cursor cursor = db.query(
                WorkoutsEntry.WORKOUT_TABLE_NAME,
                new String[]{
                        WorkoutsEntry.WORKOUTNAME_COLUMN,
                        WorkoutsEntry.MUSCLEGROUP_COLUMN,
                        WorkoutsEntry.SETS_COLUMN,
                        WorkoutsEntry.REPS_COLUMN
                },
                null,
                null,
                null,
                null,
                null
        );
        List<Workouts> result = new ArrayList<>();

        //check if there is some result
        if (cursor.getCount() > 0) {
            //While there is data in cursor
            while(cursor.moveToNext()) {
                String name = cursor.getString(0);
                String muscle = cursor.getString(1);
                int set = cursor.getInt(2);
                int reps = cursor.getInt(3);

                Workouts workout = new Workouts();
                workout.setName(name);
                workout.setMuscleGroup(muscle);
                workout.setSets(set);
                workout.setReps(reps);

                //add the workout to the list
                result.add(workout);

            }
        }
        cursor.close();
        return result;
    }
}
