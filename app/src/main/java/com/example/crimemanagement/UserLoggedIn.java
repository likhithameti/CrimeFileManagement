package com.example.crimemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.auth.User;

import static com.example.crimemanagement.SignInPage.globalusername;

public class UserLoggedIn extends AppCompatActivity {

    public static String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        username = globalusername;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_logged_in);
    }

    public void newcomplaint(View view) {
        Intent newcomp = new Intent(UserLoggedIn.this,ChooseComplaintArea.class);
        startActivity(newcomp);
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

    public void mostwantedpage(View view) {
        Intent mostwanted = new Intent(UserLoggedIn.this,MostWantedList.class);
        startActivity(mostwanted);
    }

    public void complaintstatus(View view) {
        Intent statuscomp = new Intent(UserLoggedIn.this,UserCheckComplaintStatus.class);
        startActivity(statuscomp);
    }

    public void checklatestnews(View view) {
        Intent checklatnews = new Intent(UserLoggedIn.this,UserReadNews.class);
        startActivity(checklatnews);
    }
}