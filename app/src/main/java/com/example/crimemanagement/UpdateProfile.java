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

public class UpdateProfile extends AppCompatActivity {
    String profilename;
    EditText e1,e2,e3;
    FirebaseDatabase database;
    DatabaseReference myrefUpdateProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        Intent update = getIntent();
        profilename = update.getStringExtra("updateusername");
        e1=(EditText)findViewById(R.id.updateprofilefullname);
        e2=(EditText)findViewById(R.id.updateprofilepassword);
        e3=(EditText)findViewById(R.id.updateprofilephonenumber);
        database = FirebaseDatabase.getInstance();
    }

    public void updateprofiledatabase(View view) {
        String newname = e1.getText().toString();
        String newpass = e2.getText().toString();
        String newphone = e3.getText().toString();
        myrefUpdateProfile = database.getReference().child("users");
        if(!newname.isEmpty() && !newpass.isEmpty() && !newphone.isEmpty()){
            myrefUpdateProfile.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    myrefUpdateProfile.child(profilename).child("password").setValue(newpass);
                    myrefUpdateProfile.child(profilename).child("fullname").setValue(newname);
                    myrefUpdateProfile.child(profilename).child("phonenumber").setValue(newphone);

                    Toast.makeText(UpdateProfile.this, "Profile Updated", Toast.LENGTH_LONG).show();

                    e1.setText("");
                    e2.setText("");
                    e3.setText("");

                    Intent afterthis = new Intent(UpdateProfile.this,SignInPage.class);
                    startActivity(afterthis);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else{
            Toast.makeText(this, "Fill All Fields", Toast.LENGTH_LONG).show();
        }
    }
}