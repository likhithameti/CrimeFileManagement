package com.example.crimemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginPage extends AppCompatActivity {

    EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_page);
        e1 = (EditText)findViewById(R.id.adminloginusername);
        e2 = (EditText)findViewById(R.id.adminloginpassword);
    }

    public void adminsignin(View view) {
        String unameadmin = e1.getText().toString();
        String passadmin = e2.getText().toString();
        if(!unameadmin.isEmpty() && !passadmin.isEmpty()){
            if(unameadmin.equals("admin") && passadmin.equals("admin")){
                Intent admin = new Intent(AdminLoginPage.this,AdminDashboard.class);
                startActivity(admin);
                e1.setText("");
                e2.setText("");
            }
            else{
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this, "Enter Complete Details", Toast.LENGTH_LONG).show();
        }
    }
}