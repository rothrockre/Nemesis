package com.example.nemesis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddWorkout extends AppCompatActivity {

    EditText workoutName;
    EditText sets;
    EditText reps;
    EditText muscleGroup;
    TextView workoutAdded;
    Button addworkoutbtn;
    DatabaseHelper dbhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        //Initializing the views and buttons
        workoutName =(EditText)findViewById(R.id.workoutNamePT);
        reps =(EditText)findViewById(R.id.repsPT);
        sets =(EditText)findViewById(R.id.setsPT);
        muscleGroup =(EditText)findViewById(R.id.muscleGroupPT);
        workoutAdded =(TextView) findViewById(R.id.workoutaddedTV);
        addworkoutbtn =(Button)findViewById(R.id.addWorkoutBtn);
        dbhelper = new DatabaseHelper(this);

        //Calling the methods
        printDatabase();
     }


    public void addButtonClicked(View view) {
        Workouts workout = new Workouts(workoutName.getText().toString(), muscleGroup.getText().toString(),
                Integer.parseInt(sets.getText().toString()), Integer.parseInt(reps.getText().toString()));
        dbhelper.addWorkout(workout);

        workoutAdded.setText("Workout Added");
        printDatabase();
    }

    public void printDatabase() {
        String dbString = dbhelper.databaseToString();
        workoutAdded.setText(dbString);
        workoutName.setText("");
        reps.setText("");
        sets.setText("");
        muscleGroup.setText("");
    }
}
