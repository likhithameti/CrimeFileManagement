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
}