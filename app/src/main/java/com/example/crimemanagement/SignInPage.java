package com.example.crimemanagement;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInPage extends AppCompatActivity {
    EditText loginuname;
    EditText loginpwd;
    FirebaseDatabase database;
    DatabaseReference myrefLogin;
    // to user username everywhere once signed In
    public static String globalusername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);
        loginpwd = (EditText)findViewById(R.id.loginpassword);
        loginuname = (EditText)findViewById(R.id.loginusername);
        database = FirebaseDatabase.getInstance();
    }

    public void signin(View view) {
        String username = loginuname.getText().toString();
        String password = loginpwd.getText().toString();
        myrefLogin = database.getReference().child("users");
        if(! username.isEmpty() && !password.isEmpty()){
            myrefLogin.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child(username).exists()){
                        String crctpassword = snapshot.child(username).child("password").getValue().toString();
                        if(password.equals(crctpassword)){
                            Intent i1 = new Intent(SignInPage.this,UserLoggedIn.class);
                            loginpwd.setText("");
                            loginuname.setText("");
                            globalusername = username;
                            startActivity(i1);
                        }
                        else{
                            Toast.makeText(SignInPage.this, "Invalid Credentails", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(SignInPage.this, "User Not Found", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else{
            Toast.makeText(this, "Enter Complete Details", Toast.LENGTH_LONG).show();
        }
    }
    public void signup(View view) {
        Intent signini1 = new Intent(SignInPage.this,signupPage.class);
        startActivity(signini1);

    }

    public void adminlogin(View view) {
            Intent a1 = new Intent(SignInPage.this,AdminLoginPage.class);
            startActivity(a1);
    }
}