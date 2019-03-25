package com.example.nemesis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
public class AddWorkoutPage extends AppCompatActivity {
    Context context=this;
    private EditText et_workout_name;
    private EditText et_muscle_group;
    private EditText et_sets;
    private EditText et_reps;
    private String workoutName;
    private String muscleGroup;
    private int sets;
    private int reps;
    String receieveOk;

    WorkoutDataBaseAdapter WorkoutDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout_page);
        // get Instance of Database Adapter
        WorkoutDataBaseAdapter=new WorkoutDataBaseAdapter(getApplicationContext());
        WorkoutDataBaseAdapter=WorkoutDataBaseAdapter.open();
        et_workout_name = (EditText) findViewById(R.id.workoutnameTV);
        et_muscle_group = (EditText) findViewById(R.id.musclegroupTV);
        et_sets = (EditText) findViewById(R.id.repsTV);
        et_reps = (EditText) findViewById(R.id.setsTV);
    }
    public void OK(View view)
    {
        workoutName = et_workout_name.getText().toString();
        muscleGroup = et_muscle_group.getText().toString();
        sets = Integer.parseInt(et_sets.getText().toString());
        reps = Integer.parseInt(et_reps.getText().toString());
        if((workoutName.equals(""))||(muscleGroup.equals("")))
        {
            //Display Message
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("ALERT!");
            alertDialog.setMessage("All fields must be filled");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialog.show();
        }
        else
        {
            // Save the Data in Database
            receieveOk=WorkoutDataBaseAdapter.insertEntry(workoutName,muscleGroup,reps, sets);

            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("SUCCESSFUL!");
            alertDialog.setMessage("Thanks bruh " + receieveOk);
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(AddWorkoutPage.this, Workout.class);
                    startActivity(intent);
                }
            });
            alertDialog.show();
            finish();
        }
    }
    @Override
    protected void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
        WorkoutDataBaseAdapter.close();
    }
}