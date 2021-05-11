package com.example.crimemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import org.w3c.dom.Text;

import java.util.Date;

public class AdminAddNews extends AppCompatActivity {

    EditText t1,t2,t3;
    FirebaseDatabase database;
    DatabaseReference addnewsRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_news);
        t1 = (EditText) findViewById(R.id.adminnewstitle);
        t2 = (EditText) findViewById(R.id.adminnewsdesc);
        t3 = (EditText) findViewById(R.id.adminnewsdate);
        database = FirebaseDatabase.getInstance();
        addnewsRef = database.getReference().child("news");
    }

    public void Addnews(View view) {
        addnewsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                addnewsRef.child(new Date().toString()).child("title").setValue(t1.getText().toString());
                addnewsRef.child(new Date().toString()).child("desc").setValue(t2.getText().toString());
                addnewsRef.child(new Date().toString()).child("source").setValue(t3.getText().toString());

                Toast.makeText(AdminAddNews.this, "News added successfully", Toast.LENGTH_LONG).show();
                t1.setText("");
                t2.setText("");
                t3.setText("");
                Intent addnewsafter = new Intent(AdminAddNews.this,AdminDashboard.class);
                startActivity(addnewsafter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}