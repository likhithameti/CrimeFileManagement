package com.example.crimemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class signupPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
    }

    public void register(View view) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show();
    }

    public void login(View view) {
        Intent signupi1 = new Intent(signupPage.this,SignInPage.class);
        startActivity(signupi1);
    }
}