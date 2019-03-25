package com.example.nemesis;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class WorkoutDataBaseAdapter {
    static final String DATABASE_NAME = "WORKOUTDB";
    String ok="OK";
    static final int DATABASE_VERSION = 1;
    public  static String getPassword="";
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table WORKOUT( ID integer primary key autoincrement,WORKOUTNAME  text,MUSCLEGROUP  text,SETS integer,REPS integer); ";
    // Variable to hold the database instance
    public static SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private static WorkoutDataBaseHelper dbHelper;

    public WorkoutDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new WorkoutDataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Method to openthe Database
    public  WorkoutDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    // Method to close the Database
    public void close()
    {

        db.close();
    }

    // method returns an Instance of the Database
    public  SQLiteDatabase getDatabaseInstance()
    {

        return db;
    }
    // method to insert a record in Table
    public String insertEntry(String workout,String musclegroup,int reps,int sets)
    {
        try {
            ContentValues newValues = new ContentValues();
            // Assign values for each column.
            newValues.put("WORKOUTNAME", workout);
            newValues.put("MUSCLEGROUP", musclegroup);
            newValues.put("REPS", reps);
            newValues.put("SETS", sets);
            // Insert the row into your table
            db = dbHelper.getWritableDatabase();
            long result=db.insert("WORKOUT", null, newValues);
            System.out.print(result);
            Toast.makeText(context, "User Workout Saved", Toast.LENGTH_LONG).show();
        }catch(Exception ex) {
            System.out.println("Exceptions " +ex);
            Log.e("Note", "One row entered");
        }
        return ok;
    }
    // method to delete a Record of UserName
    public int deleteEntry(String workoutname)
    {
        String where="WORKOUTNAME=?";
        int numberOFEntriesDeleted= db.delete("WORKOUT", where, new String[]{workoutname}) ;
        Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    // method to get the password  of Workout Name
    public String getSinlgeEntry(String workoutname)
    {
        db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query("WORKOUT", null, "WORKOUTNAME=?", new String[]{workoutname}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
            return "NOT EXIST";
        cursor.moveToFirst();
        getPassword= cursor.getString(cursor.getColumnIndex("WorkoutName"));
        return getPassword;
    }
    // Method to Update an Existing
    public void  updateEntry(String workoutname,String sets)
    {
        //  create object of ContentValues
        ContentValues updatedValues = new ContentValues();
        // Assign values for each Column.
        updatedValues.put("WORKOUTNAME", workoutname);
        updatedValues.put("SETS", sets);
        String where="WORKOUTNAME = ?";
        db.update("WORKOUT",updatedValues, where, new String[]{workoutname});
    }

}