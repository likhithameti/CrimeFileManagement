package com.example.crimemanagement;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SignInPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);
    }

    public void signin(View view) {
        Intent i1 = new Intent(SignInPage.this,UserLoggedIn.class);
        startActivity(i1);
    }

    public void signup(View view) {
        Intent signini1 = new Intent(SignInPage.this,signupPage.class);
        startActivity(signini1);

    }

}