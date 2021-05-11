package com.example.crimemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
    }

    public void addMostWanted(View view) {
        Intent most = new Intent(AdminDashboard.this,AdminAddMostWanted.class);
        startActivity(most);
    }

    public void adminviewmostwanted(View view) {
        Intent list = new Intent(AdminDashboard.this,MostWantedList.class);
        startActivity(list);
    }

    public void manageusersclick(View view) {
        Intent manage = new Intent(AdminDashboard.this,AdminManageUsers.class);
        startActivity(manage);
    }

    public void adminpendingcomplaints(View view) {
        Intent pend = new Intent(AdminDashboard.this,ChooseComplaintAreaAdmin.class);
        startActivity(pend);
    }

    public void adminlogout(View view) {
        Intent l = new Intent(AdminDashboard.this,AdminLoginPage.class);
        startActivity(l);
    }

    public void newsadd(View view) {
        Intent news = new Intent(AdminDashboard.this,AdminAddNews.class);
        startActivity(news);
    }
}