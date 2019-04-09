package com.example.nemesis;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.renderscript.ScriptGroup;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


public class Homepage extends AppCompatActivity {

    private static String NEWS_URL = "https://newsapi.org/v2/everything?q=fox-sports&apiKey=0b23857f53cb4d00aee3ad9e141222cd";
    Button[] news;
    JsonObject jsonObject;
    private String jsonString = "";

    String[][] headlinearray;
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

        //////////////////////////////////////////////
        news = new Button[5];
        news[0] = findViewById(R.id.news1TV);
        news[1] = findViewById(R.id.news2TV);
        news[2] = findViewById(R.id.news3TV);
        news[3] = findViewById(R.id.news4TV);
        news[4] = findViewById(R.id.news5TV);

//        mQueue = Volley.newRequestQueue(this);
        //jsonParse();

        String test = "test";
        //json String gets set here
        headlinearray = new String[2][5];
        //new Homepage.AsyncHttpTask().execute(NEWS_URL);

        String myJson = openConnectionToNewsAPI_Get();
        //jsonString = openConnectionToNewsAPI_Get();
        //Log.i("json", jsonString);

        jsonObject = new JsonParser().parse(myJson).getAsJsonObject();
        //headlinearray[2] is for picture link.

        headlinearray[0] = parseJsonForHeadlines();
        headlinearray[1] = parseJsonForHeadlineLinks();
        //headlinearray[2] = parsejsonforpicture();
        for (int i = 0; i < 5; i++) {
            news[i].setText(headlinearray[0][i]);
        }




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






    private String[] parseJsonForHeadlines() {
        String[] headlineArrays = new String[5];

        JsonArray jsonArray = jsonObject.getAsJsonArray("articles");
        JsonElement jsonElement;
        JsonObject jsonArrayObject;

        for (int i = 0; i < 5; i++) {
            jsonElement = jsonArray.get(i);
            jsonArrayObject = (JsonObject) jsonElement;
            headlineArrays[i] = jsonArrayObject.get("title").toString();
        }
        return headlineArrays;

    }

    private String[] parseJsonForHeadlineLinks() {
        String[] headlineLinksArray = new String[5];
        JsonArray jsonArray = jsonObject.getAsJsonArray("articles");
        JsonElement jsonElement;
        JsonObject jsonArrayObject;
        String linkpretrim;

        for (int i = 0; i < 5; i++) {
            jsonElement = jsonArray.get(i);
            jsonArrayObject = (JsonObject) jsonElement;
            linkpretrim = jsonArrayObject.get("url").toString();
            headlineLinksArray[i] = linkpretrim.replaceAll("^\"|\"$", "");
        }
        return headlineLinksArray;


    }



    private String openConnectionToNewsAPI_Get() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HttpURLConnection connection = null;
        InputStream is = null;

        try {
            URL url = new URL("https://newsapi.org/v2/everything?q=fox-sports&apiKey=0b23857f53cb4d00aee3ad9e141222cd");

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                is = connection.getErrorStream();
            }
            else {
                is = connection.getInputStream();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();



            is.close();
            connection.disconnect();

            if (line.contains("rateLimited")) {
                return "Currently Rate Limited by NewsAPI";
            }
            return line;




        } catch(Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            }
            catch (Throwable t) {
            }
            try {
                connection.disconnect();
            } catch (Throwable t) {
            }
        }
        return "News Data unavailable to be retrieved";
    }

    public void goToUrl0 (View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(headlinearray[1][0]));
        startActivity(browserIntent);
    }

    public void goToUrl1 (View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(headlinearray[1][1]));
        startActivity(browserIntent);
    }

    public void goToUrl2 (View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(headlinearray[1][2]));
        startActivity(browserIntent);
    }

    public void goToUrl3 (View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(headlinearray[1][3]));
        startActivity(browserIntent);
    }

    public void browser4 (View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(headlinearray[1][4]));
        startActivity(browserIntent);
    }


}
