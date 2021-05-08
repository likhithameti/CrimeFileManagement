package com.example.crimemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import org.w3c.dom.Text;

public class AdminManageUsers extends AppCompatActivity {

    EditText e1;
    TextView t1,t2,t3;
    DatabaseReference getData;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_users);
        e1 = (EditText)findViewById(R.id.usernamegetdata);
        t1 = (TextView)findViewById(R.id.unamedisplay);
        t2 = (TextView)findViewById(R.id.aadhaardisplay);
        t3 = (TextView)findViewById(R.id.phonedisplay);
        database = FirebaseDatabase.getInstance();
    }

    public void get(View view) {
        getData = database.getReference().child("users");
        String uname = e1.getText().toString();
        if(!uname.isEmpty()){
            getData.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child(uname).exists()){
                        String fullname = snapshot.child(uname).child("fullname").getValue().toString();
                        String mobile = snapshot.child(uname).child("phonenumber").getValue().toString();
                        String aadhaar = snapshot.child(uname).child("aadhaarnumber").getValue().toString();
                        t1.setText(fullname);
                        t2.setText(aadhaar);
                        t3.setText(mobile);
                        e1.setText("");
                    }
                    else{
                        Toast.makeText(AdminManageUsers.this, "User Not Found", Toast.LENGTH_LONG).show();
                        t1.setText("");
                        t2.setText("");
                        t3.setText("");
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else{
            Toast.makeText(this, "Enter Username", Toast.LENGTH_LONG).show();
        }

    }
}