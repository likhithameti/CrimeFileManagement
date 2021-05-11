package com.example.crimemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseComplaintAreaAdmin extends AppCompatActivity {
    public static String complaintAreaForGettingFromDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_complaint_area_admin);
    }

    public void zoneinformationadmin(View view) {
        Intent zoneinfoadmin = new Intent(ChooseComplaintAreaAdmin.this,ZoneInformation.class);
        startActivity(zoneinfoadmin);
    }

    public void eastzoneclick(View view) {
        complaintAreaForGettingFromDatabase = "eastzone";
        Intent eastint = new Intent(ChooseComplaintAreaAdmin.this,AdminCheckComplaintStatus.class);
        startActivity(eastint);
    }

    public void northzoneclick(View view) {
        complaintAreaForGettingFromDatabase= "nothzone";
        Intent northint = new Intent(ChooseComplaintAreaAdmin.this,AdminCheckComplaintStatus.class);
        startActivity(northint);
    }

    public void southzoneclick(View view) {
        complaintAreaForGettingFromDatabase = "southzone";
        Intent southint = new Intent(ChooseComplaintAreaAdmin.this,AdminCheckComplaintStatus.class);
        startActivity(southint);
    }

    public void westzoneclick(View view) {
        complaintAreaForGettingFromDatabase = "westzone";
        Intent westint = new Intent(ChooseComplaintAreaAdmin.this,AdminCheckComplaintStatus.class);
        startActivity(westint);
    }

    public void centralzoneclick(View view) {
        complaintAreaForGettingFromDatabase = "centralzone";
        Intent centralint = new Intent(ChooseComplaintAreaAdmin.this,AdminCheckComplaintStatus.class);
        startActivity(centralint);
    }
}