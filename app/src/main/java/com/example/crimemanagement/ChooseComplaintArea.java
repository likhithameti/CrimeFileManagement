package com.example.crimemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseComplaintArea extends AppCompatActivity {
    public static String complaintAreaForStoringInDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_complaint_area);
    }

    public void location4(View view) {
        complaintAreaForStoringInDatabase = "centralzone";
        Intent choosen5 = new Intent(ChooseComplaintArea.this,FileComplaint.class);
        startActivity(choosen5);
    }
    public void location3(View view) {
        complaintAreaForStoringInDatabase = "southzone";
        Intent choosen4 = new Intent(ChooseComplaintArea.this,FileComplaint.class);
        startActivity(choosen4);
    }

    public void location2(View view) {
        complaintAreaForStoringInDatabase = "westzone";
        Intent choosen1 = new Intent(ChooseComplaintArea.this,FileComplaint.class);
        startActivity(choosen1);
    }

    public void location1(View view) {
        complaintAreaForStoringInDatabase = "eastzone";
        Intent choosen2 = new Intent(ChooseComplaintArea.this,FileComplaint.class);
        startActivity(choosen2);
    }

    public void zoneinformation(View view) {
        Intent zone = new Intent(ChooseComplaintArea.this,ZoneInformation.class);
        startActivity(zone);
    }

    public void location5(View view) {
        complaintAreaForStoringInDatabase = "northzone";
        Intent choosen3 = new Intent(ChooseComplaintArea.this,FileComplaint.class);
        startActivity(choosen3);
    }
}