package com.example.nemesis;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.*;
import android.support.v4.widget.DrawerLayout;
import android.text.InputType;
import android.view.MenuItem;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Workout extends AppCompatActivity {

    static final String DATABASE_NAME = "WORKOUTDB";
    static final int DATABASE_VERSION = 1;
    private DrawerLayout drawerLayout;
    //WorkoutDataBaseHelper dbHelper;
    //SQLiteDatabase db;
    WorkoutDataBaseAdapter db;
    TextView tv1;
    TextView tv_chest;
    TextView tv_bicep;
    TextView tv_tricep;
    TextView tv_shoulder;
    TextView tv_back;
    TextView tv_forearms;
    TextView tv_legs;
    ListView lv_workouts_show;
    private MyDB sqlite;
    private ArrayAdapter<String> adapter;
    private int bicepCount;
    private int tricepCount;
    private int chestCount;
    private int legCount;
    private int shoulderCount;
    private int forearmCount;
    private int backCount;
    private String bicepString = "Biceps";
    private String tricepString = "Triceps";
    private String chestString = "Chest";
    private String legString = "Legs";
    private String shoulderString = "Shoulders";
    private String forearmString = "Forearms";
    private String backString = "Back";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        ////////////////////Workout Stuff//////
        tv1 = findViewById(R.id.tv1);
        lv_workouts_show = findViewById(R.id.lv_workouts);
        tv_chest = findViewById(R.id.tv_chest);
        tv_back = findViewById(R.id.tv_back);
        tv_bicep = findViewById(R.id.tv_biceps);
        tv_tricep = findViewById(R.id.tv_triceps);
        tv_shoulder = findViewById(R.id.tv_shoulder);
        tv_forearms = findViewById(R.id.tv_forearms);
        tv_legs = findViewById(R.id.tv_legs);


        sqlite = new MyDB(this);


        List<Workouts> workoutData = sqlite.get();

        List<String> data = new ArrayList<>();

        for(Workouts w : workoutData) {
            String temp = getWorkoutAsString(w);
            data.add(temp);

        }

        for(Workouts w : workoutData) {
            String x = w.getMuscleGroup();


            switch (x.toLowerCase()) {
                case ("chest"):
                    chestCount++;
                    break;
                case ("legs"):
                    legCount++;
                    break;
                case ("shoulders"):
                    shoulderCount++;
                    break;
                case ("biceps"):
                    bicepCount++;
                    break;
                case ("triceps"):
                    tricepCount++;
                    break;
                case ("forearms"):
                    forearmCount++;
                    break;
                case ("back"):
                    backCount++;
                    break;
                default:
                    break;
            }

            tv_chest.setText("Chest\n" + chestCount);
            tv_legs.setText("Legs\n" + legCount);
            tv_forearms.setText("Forearms\n" + forearmCount);
            tv_shoulder.setText("Shoulders\n" + shoulderCount);
            tv_back.setText("Back\n" + backCount);
            tv_tricep.setText("Triceps\n" + tricepCount);
            tv_bicep.setText("Biceps\n" + bicepCount);
            tv1.setText(muscleRecommendation());
        }

            tv_chest.setText("Chest\n" + chestCount);
            tv_legs.setText("Legs\n" + legCount);
            tv_forearms.setText("Forearms\n" + forearmCount);
            tv_shoulder.setText("Shoulders\n" + shoulderCount);
            tv_back.setText("Back\n" + backCount);
            tv_tricep.setText("Triceps\n" + tricepCount);
            tv_bicep.setText("Biceps\n" + bicepCount);
            tv1.setText(muscleRecommendation());


        //if no data in database show a toast
        if(data.size() == 0) {
            new android.support.v7.app.AlertDialog.Builder(this)
                    .setMessage("There is no data in the Database, click on the Add Workout to add one.")
                    .setPositiveButton("OK",null)
                    .show();
        }

        //set the data in adapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1 ,data);
        lv_workouts_show.setAdapter(adapter);




        tv_chest.setText("Chest\n" + chestCount);
        tv_legs.setText("Legs\n" + legCount);
        tv_forearms.setText("Forearms\n" + forearmCount);
        tv_shoulder.setText("Shoulders\n" + shoulderCount);
        tv_back.setText("Back\n" + backCount);
        tv_tricep.setText("Triceps\n" + tricepCount);
        tv_bicep.setText("Biceps\n" + bicepCount);



        ////////////////////Workout Stuff//////

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        switch (menuItem.getItemId()) {

                            case R.id.nav_homepage:
                                Intent homepageIntent = new Intent(getApplicationContext(), Homepage.class);
                                startActivity(homepageIntent);
                                drawerLayout.closeDrawers();
                                break;
                            case R.id.nav_profile:
                                Intent profileIntent = new Intent(getApplicationContext(), Profile.class);
                                startActivity(profileIntent);
                                drawerLayout.closeDrawers();
                                break;
                            case R.id.nav_workout:
                                Intent workoutIntent = new Intent(getApplicationContext(), Workout.class);
                                startActivity(workoutIntent);
                                drawerLayout.closeDrawers();
                                break;
                            case R.id.nav_cardio:
                                Intent cardioIntent = new Intent(getApplicationContext(), Cardio.class);
                                startActivity(cardioIntent);
                                drawerLayout.closeDrawers();
                                break;

                        }
                        return true;
                    }
                });

        drawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private String getWorkoutAsString(Workouts w) {
        return String.format("%s\nMusclegroup: %s, Sets: %d, Reps: %d",
        w.getName(), w.getMuscleGroup(), w.getSets(), w.getReps());
    }

    public void addWorkout(View view) {


        showDialogInput();


    }

    private void showDialogInput() {

        final EditText edtName = new EditText(this);
        edtName.setHint("Workout Name");
        edtName.setPadding(10,32,10,32);

//        final EditText edtMuscle = new EditText(this);
//        edtMuscle.setHint("Muscle Group");
//        edtMuscle.setPadding(10,32,10,32);

        final Spinner edtMuscle = new Spinner(this);
        edtMuscle.setPadding(10,32,10,32);


        final EditText edtSets = new EditText(this);
        edtSets.setHint("Sets Performed");
        edtSets.setInputType(InputType.TYPE_CLASS_NUMBER);
        edtSets.setPadding(10,32,10,32);

        final EditText edtReps = new EditText(this);
        edtReps.setHint("Reps Performed");
        edtReps.setInputType(InputType.TYPE_CLASS_NUMBER);
        edtReps.setPadding(10,32,10,32);

        String[] muscles = {chestString, bicepString, tricepString, backString, shoulderString, forearmString, legString};


//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, muscles);
//set the spinners adapter to the previously created one.
        edtMuscle.setAdapter(adapter1);

        LinearLayout view = new LinearLayout(this);
        view.setOrientation(LinearLayout.VERTICAL);
        view.addView(edtName);
        view.addView(edtMuscle);
        view.addView(edtSets);
        view.addView(edtReps);

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Workout")
                .setView(view)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if no workout name or info do nothing
                        if(edtName.getText().toString().trim().isEmpty()
                            || edtMuscle.getSelectedItem().toString().trim().isEmpty()) {
                            return;
                        }

                        //create workout entity to save in database
                        Workouts w = new Workouts();
                        w.setName(edtName.getText().toString());
                        w.setMuscleGroup(edtMuscle.getSelectedItem().toString());
                        w.setSets(Integer.parseInt(edtSets.getText().toString()));
                        w.setReps(Integer.parseInt(edtReps.getText().toString()));

                        String x = edtMuscle.getSelectedItem().toString();


                        switch (x.toLowerCase()) {
                            case ("chest"):
                                chestCount++;
                                break;
                            case ("legs"):
                                legCount++;
                                break;
                            case ("shoulders"):
                                shoulderCount++;
                                break;
                            case ("biceps"):
                                bicepCount++;
                                break;
                            case ("triceps"):
                                tricepCount++;
                                break;
                            case ("forearms"):
                                forearmCount++;
                                break;
                            case ("back"):
                                backCount++;
                                break;
                            default:
                                break;
                        }
                        tv_chest.setText("Chest\n" + chestCount);
                        tv_legs.setText("Legs\n" + legCount);
                        tv_forearms.setText("Forearms\n" + forearmCount);
                        tv_shoulder.setText("Shoulders\n" + shoulderCount);
                        tv_back.setText("Back\n" + backCount);
                        tv_tricep.setText("Triceps\n" + tricepCount);
                        tv_bicep.setText("Biceps\n" + bicepCount);
                        tv1.setText(muscleRecommendation());


                        //save into database
                        sqlite.insert(w);

                        //Format workout as string

                        String workoutAsString = getWorkoutAsString(w);

                        adapter.add(workoutAsString);

                        Toast.makeText(Workout.this, "ADDED", Toast.LENGTH_SHORT).show();

                    }
                });
        builder.create().show();
    }

    public String muscleRecommendation() {


        int temp = (Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(bicepCount, tricepCount),chestCount), legCount),shoulderCount), forearmCount), backCount));
        if (temp == bicepCount) {
            return "Why not do a Bicep Workout? Would hate for your muscles to become uneven";
        }
        if(temp == tricepCount) {
            return "Why not do a Tricep Workout? Would hate for your muscles to become uneven!";
        }
        if(temp == chestCount) {
            return "Why not do a Chest Workout? Would hate for your muscle to become uneven!";
        }
        if(temp == legCount) {
            return "Why not do a Leg Workout? Would hate for your legs to get small!";
        }
        if(temp == shoulderCount) {
            return "Why not do a Shoulder Workout? Would hate for your muscles to become uneven!";
        }
        if(temp == forearmCount) {
            return "Why not do a forearm Workout? Would hate for your muscles to become uneven!";
        }
        if(temp == backCount) {
            return "Why not do a Back Workout? Would hate for your muscles to become uneven!";
        }
        return "You are doing a good job keeping your workouts even";
    }



}