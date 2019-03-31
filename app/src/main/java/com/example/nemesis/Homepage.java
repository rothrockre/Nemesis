package com.example.nemesis;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.*;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Homepage extends AppCompatActivity {

    String news_url;
    TextView news1;
    TextView news2;
    TextView news3;
    TextView news4;
    TextView news5;

    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        ///////////////////////////////////////////////
        news1 = findViewById(R.id.news1TV);
        news2 = findViewById(R.id.news2TV);
        news3 = findViewById(R.id.news3TV);
        news4 = findViewById(R.id.news4TV);
        news5 = findViewById(R.id.news5TV);

        news_url = "https://newsapi.org/v2/everything?q=fox-sports&apiKey=0b23857f53cb4d00aee3ad9e141222cd";
        JSONObject


        new Homepage.AsyncHttpTask().execute(news_url);
        ////////////////////////////////////////////////

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


    public class AsyncHttpTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                String response = streamToString(urlConnection.getInputStream());
                parseResult(response);
                return result;


            }catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }
    }

    String streamToString(InputStream stream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String data;
        String result ="";

        while ((data = bufferedReader.readLine()) != null) {

            result += data;
        }

        if(null != stream) {
            stream.close();
        }

        return result;
    }

    private void parseResult(String result) {
        JSONObject response = null;
        try {
            response = new JSONObject(result);
            JSONArray articles = response.optJSONArray("articles");

            for(int i = 0; i < articles.length(); i++) {
                JSONObject article = articles.getJSONObject(i);
                String title = article.optString("title");
                Log.i("Titles", title);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray articles = response.optJSONArray("articles");

    }
}
