package com.example.crimemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ViewProfile extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5;
    FirebaseDatabase database;
    DatabaseReference myrefProfile;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent profile = getIntent();
        username = profile.getStringExtra("profileusername");
        setContentView(R.layout.activity_view_profile);
        t1 = (TextView)findViewById(R.id.profilename);
        t2 = (TextView)findViewById(R.id.profileusername);
        t3 = (TextView)findViewById(R.id.profileaadhaar);
        t4 = (TextView)findViewById(R.id.profilepassword);
        t5 = (TextView)findViewById(R.id.profilephonenumber);
        database = FirebaseDatabase.getInstance();
        myrefProfile =database.getReference().child("users");

        myrefProfile.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String fullname = snapshot.child(username).child("fullname").getValue().toString();
                String usernaam = snapshot.child(username).child("username").getValue().toString();
                String password = snapshot.child(username).child("password").getValue().toString();
                String mobile = snapshot.child(username).child("phonenumber").getValue().toString();
                String aadhaar = snapshot.child(username).child("aadhaarnumber").getValue().toString();

                t1.setText("Name : "+fullname);
                t2.setText("UserName : "+usernaam);
                t3.setText("Aadhaar : "+aadhaar);
                t4.setText("Password : "+password);
                t5.setText("Mobile : "+mobile);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void updateprofile(View view) {
        Intent updateprofilepage = new Intent(ViewProfile.this,UpdateProfile.class);
        updateprofilepage.putExtra("updateusername",username);
        startActivity(updateprofilepage);
    }

}