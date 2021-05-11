package com.example.crimemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class signupPage extends AppCompatActivity {

    EditText uname,fullname,aadhar,phoneno,pass;
    FirebaseDatabase database;
    DatabaseReference myrefRegister;
    DatabaseReference myrefAadharData;
    // for checking aadhaar exists or not
    int count;
    // for checking aadhaar registered or not
    int countregistered;

    final int SEND_SMS_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        count = 0;
        countregistered = 0;
        pass = (EditText)findViewById(R.id.signuppassword);
        uname = (EditText)findViewById(R.id.signupusername);
        fullname = (EditText)findViewById(R.id.signupfullname);
        aadhar = (EditText)findViewById(R.id.aadhaarnumber);
        phoneno = (EditText)findViewById(R.id.mobilenumber);
        database = FirebaseDatabase.getInstance();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
//        if(checkPermission(Manifest.permission.SEND_SMS)){
//
//        }else{
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.SEND_SMS},SEND_SMS_REQUEST_CODE);
//        }
    }

    public void register(View view) {
        String username = uname.getText().toString();
        String full = fullname.getText().toString();
        String aadharnumber = aadhar.getText().toString();
        String phonenumber = phoneno.getText().toString();
        String password = pass.getText().toString();
        myrefAadharData = database.getReference().child("aadhaarnumbers");
        myrefRegister = database.getReference().child("users");
        if(!username.isEmpty() && !full.isEmpty()  && !aadharnumber.isEmpty() && !phonenumber.isEmpty()){
                        // checking aadhar exists in data base or not
                        myrefAadharData.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.child(aadharnumber).exists()){
                                    count=1;
                                    String check = snapshot.child(aadharnumber).child("registered").getValue().toString();
                                    if(check.equals("0"))
                                        countregistered = 0;
                                    else
                                        countregistered = 1;
//                                    phoneNumber = snapshot.child(aadharnumber).child("mobilenumber").getValue().toString();
                                    myrefRegister.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.child(username).exists()){
                                                    Toast.makeText(signupPage.this, "Already Exists With this Username", Toast.LENGTH_LONG).show();
                                                    uname.setText("");
                                                    fullname.setText("");
                                                    aadhar.setText("");
                                                    phoneno.setText("");
                                                }
                                                else{
                                                    if(countregistered==0){
                                                        myrefRegister.child(username).child("username").setValue(username);
                                                        myrefRegister.child(username).child("fullname").setValue(full);
                                                        myrefRegister.child(username).child("aadhaarnumber").setValue(aadharnumber);
                                                        myrefRegister.child(username).child("phonenumber").setValue(phonenumber);
                                                        myrefRegister.child(username).child("password").setValue(password);


                                                        Toast.makeText(signupPage.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                                                        uname.setText("");
                                                        fullname.setText("");
                                                        aadhar.setText("");
                                                        phoneno.setText("");
                                                        pass.setText("");
                                                        Intent after = new Intent(signupPage.this,SignInPage.class);
                                                        startActivity(after);
                                                        myrefAadharData.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                myrefAadharData.child(aadharnumber).child("registered").setValue("1");
                                                                myrefAadharData.child(aadharnumber).child("username").setValue(username);
                                                            }
                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });
                                                    }
                                                    else{
                                                        Toast.makeText(signupPage.this, "Already Registered with this Aadhaar", Toast.LENGTH_LONG).show();
                                                        uname.setText("");
                                                        fullname.setText("");
                                                        aadhar.setText("");
                                                        phoneno.setText("");

                                                    }

                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                }
                                else{
                                    Toast.makeText(signupPage.this, "Aadhaar Not Found", Toast.LENGTH_LONG).show();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }
        else{
            Toast.makeText(this, "Enter Complete Details ", Toast.LENGTH_LONG).show();
        }


    }

    public void login(View view) {
        Intent signupi1 = new Intent(signupPage.this,SignInPage.class);
        startActivity(signupi1);
    }
}