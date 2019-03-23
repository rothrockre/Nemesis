package com.example.nemesis;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WorkoutDatabaseTable {
//    private static final String TAG = "WorkoutDatabase";
//
//    //The columns I will include in the table
//    public static final String COL_NAME;
//    public static final String COL_MUSCLEGROUP;
//
//    private static final String DATABASE_NAME;
//    private static final String FTS_VIRTUAL_TABLE = "FTS";
//    private static final int DATABASE_VERSION = 1;
//
//    private final DatabaseOpenHelper databaseOpenHelper;
//
//    //constructor for WorkoutDatabase
//    public WorkoutDatabaseTable(Context context) {
//        databaseOpenHelper = new DatabaseOpenHelper(context);
//    }
//
//    private static class DatabaseOpenHelper extends SQLiteOpenHelper {
//
//        private final Context helperContext;
//        private SQLiteDatabase mDatabase;
//
//        private static final String FTS_TABLE_CREATE =
//                "CREATE VIRTUAL TABLE " + FTS_VIRTUAL_TABLE +
//                        " USING fts3 (" +
//                        COL_NAME + ", " +
//                        COL_MUSCLEGROUP + ")";
//
//        DatabaseOpenHelper(Context context) {
//            super(context, DATABASE_NAME, null, DATABASE_VERSION);
//            helperContext = context;
//        }
//
//        @Override
//        public void onCreate(SQLiteDatabase db) {
//            mDatabase = db;
//            mDatabase.execSQL(FTS_TABLE_CREATE);
//            loadWorkouts();
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
//                    + newVersion + ", which will destroy all old data");
//            db.execSQL("DROP TABLE IF EXISTS " + FTS_VIRTUAL_TABLE);
//            onCreate(db);
//        }
//
//        private void loadDB() {
//            new Thread(new Runnable() {
//                public void run() {
//                    try {
//                        loadWorkouts();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }).start();
//        }
//
//        private void loadWorkouts() throws IOException {
//            final Resources resources = helperContext.getResources();
//            InputStream inputStream = resources.openRawResource(R.raw.workouts);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//
//            try {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    String[] strings = TextUtils.split(line, "-");
//                    if (strings.length < 2) continue;
//                    long id = addWorkout(strings[0].trim(), strings[1].trim());
//                    if (id < 0) {
//                        Log.e(TAG, "unable to add workout: " + strings[0].trim());
//                    }
//                }
//            }finally{
//                reader.close();
//            }
//        }
//
//
//
//        public long addWorkout(String workout, String muscle) {
//            ContentValues initialValues = new ContentValues();
//            initialValues.put(COL_NAME, workout);
//            initialValues.put(COL_MUSCLEGROUP, muscle);
//
//            return mDatabase.insert(FTS_VIRTUAL_TABLE, null, initialValues);
//        }
//    //close for subclass
//    }
//
//
//
//
//

//close for main class
}



