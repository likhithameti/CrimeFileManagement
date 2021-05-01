package com.example.crimemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class UserLoggedIn extends AppCompatActivity {

    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent profile = getIntent();
        username = profile.getStringExtra("profilename");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_logged_in);
    }

    public void newcomplaint(View view) {
        Toast.makeText(this, "New Complaint Clicked", Toast.LENGTH_LONG).show();
    }

    public void signoutbutton(View view) {
        Intent logout = new Intent(UserLoggedIn.this,SignInPage.class);
        startActivity(logout);
    }

    public void viewprofile(View view) {
        Intent profilepage = new Intent(UserLoggedIn.this,ViewProfile.class);
        profilepage.putExtra("profileusername",username);
        startActivity(profilepage);
    }

    public void updateprofile(View view) {
        Intent updateprofilepage = new Intent(UserLoggedIn.this,UpdateProfile.class);
        updateprofilepage.putExtra("updateusername",username);
        startActivity(updateprofilepage);
    }
}