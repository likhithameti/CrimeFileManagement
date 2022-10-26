package com.example.crimemanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class ViewAllUsers extends AppCompatActivity {
    FirebaseDatabase database;
    FirebaseStorage firebaseStorage;
    DatabaseReference usersRef;
    RecyclerView recyclerViewAllUsers;
    ViewAllUsersAdapter viewAllUsersAdapter;
    List<ViewAllUsersClass> viewAllUsersClasses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_users);
        database = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        usersRef = database.getReference("users");
        recyclerViewAllUsers = findViewById(R.id.viewAllUsersRecyclerView);
        recyclerViewAllUsers.setHasFixedSize(true);
        recyclerViewAllUsers.setLayoutManager(new LinearLayoutManager(this));
        viewAllUsersClasses = new ArrayList<>();
        viewAllUsersAdapter = new ViewAllUsersAdapter(ViewAllUsers.this,viewAllUsersClasses);
        recyclerViewAllUsers.setAdapter(viewAllUsersAdapter);

        usersRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ViewAllUsersClass viewAllUsersClass = snapshot.getValue(ViewAllUsersClass.class);
                viewAllUsersClasses.add(viewAllUsersClass);
                viewAllUsersAdapter.notifyDataSetChanged();
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