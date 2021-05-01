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

public class MostWantedList extends AppCompatActivity {
    FirebaseDatabase database;
    FirebaseStorage firebaseStorage;
    DatabaseReference mref;
    RecyclerView  recyclerView;
    MostWantedAdapter mostWantedAdapter;
    List<MostWantedModel> mostWantedModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_wanted_list);
        database = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        mref = database.getReference("mostwantedlist");
        recyclerView = findViewById(R.id.adminrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mostWantedModels = new ArrayList<>();
        mostWantedAdapter = new MostWantedAdapter(MostWantedList.this,mostWantedModels);
        recyclerView.setAdapter(mostWantedAdapter);
        mref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MostWantedModel mostWantedModel = snapshot.getValue(MostWantedModel.class);
                mostWantedModels.add(mostWantedModel);
                mostWantedAdapter.notifyDataSetChanged();
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