package com.example.nemesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.*;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Workout extends AppCompatActivity {

    private Button addWorkoutBtn;
    private DrawerLayout drawerLayout;


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

        addWorkoutBtn = (Button)findViewById(R.id.addworkoutbtn);

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



        addWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openAddWorkout();
            }
        });
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

    public void openAddWorkout() {
        Intent intent = new Intent(this, AddWorkout.class);
        startActivity(intent);
    }

}