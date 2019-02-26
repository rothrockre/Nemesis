package com.example.nemesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import android.app.Activity;

import android.view.View;

import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText loginUsername, loginPassword;

    private int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button)findViewById(R.id.loginButton);
        loginPassword = (EditText)findViewById(R.id.loginPassword);
        loginUsername = (EditText)findViewById(R.id.loginUsername);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginUsername.getText().toString().toLowerCase().equals("admin") &&
                        loginPassword.getText().toString().equals("admin")) {
                    openHomepage();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",
                            Toast.LENGTH_SHORT).show();

                    counter--;
                    if(counter == 0) {
                        loginButton.setEnabled(false);
                    }
                }
            }
        });

    }

    public void openHomepage() {
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }

}