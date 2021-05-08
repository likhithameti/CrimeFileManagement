package com.example.crimemanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

import static com.example.crimemanagement.SignInPage.globalusername;
import static com.example.crimemanagement.UserLoggedIn.username;

public class UserCheckComplaintStatus extends AppCompatActivity {
    FirebaseDatabase database;
    FirebaseStorage firebaseStorage;
    DatabaseReference mrefnew;
    RecyclerView recyclerView;
    UserComplaintStatusAdapter userComplaintStatusAdapter;
    List<UserComplaintStatusModel> userComplaintStatusModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_check_complaint_status);
        database = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
//        Toast.makeText(this, username, Toast.LENGTH_LONG).show();

        // when we call from file complaint class username may not initialized so use global username
        mrefnew= database.getReference("users").child(globalusername).child("complaintslist");
        recyclerView = findViewById(R.id.userRecyclerStatus);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userComplaintStatusModels = new ArrayList<>();
        userComplaintStatusAdapter = new UserComplaintStatusAdapter(UserCheckComplaintStatus.this,userComplaintStatusModels);
        recyclerView.setAdapter(userComplaintStatusAdapter);
        mrefnew.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                UserComplaintStatusModel userComplaintStatusModel = snapshot.getValue(UserComplaintStatusModel.class);
                userComplaintStatusModels.add(userComplaintStatusModel);
                userComplaintStatusAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}