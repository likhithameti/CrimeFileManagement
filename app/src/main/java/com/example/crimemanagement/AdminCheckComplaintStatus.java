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

import static com.example.crimemanagement.ChooseComplaintAreaAdmin.complaintAreaForGettingFromDatabase;

public class AdminCheckComplaintStatus extends AppCompatActivity {
    FirebaseDatabase database;
    FirebaseStorage firebaseStorage;
    DatabaseReference mrefadmin;
    RecyclerView recyclerView;

    AdminComplaintStatusAdapter adminComplaintStatusAdapter;
    List<AdminComplaintStatusModel> adminComplaintStatusModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_check_complaint_status);
        database = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        // reference will be received from previous page : zones page
        mrefadmin = database.getReference(complaintAreaForGettingFromDatabase);
        recyclerView = findViewById(R.id.adminRecyclerStatus);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adminComplaintStatusModels = new ArrayList<>();
        adminComplaintStatusAdapter = new AdminComplaintStatusAdapter(AdminCheckComplaintStatus.this,adminComplaintStatusModels);
        recyclerView.setAdapter(adminComplaintStatusAdapter);

        mrefadmin.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                AdminComplaintStatusModel adminComplaintStatusModel = snapshot.getValue(AdminComplaintStatusModel.class);
                adminComplaintStatusModels.add(adminComplaintStatusModel);
                adminComplaintStatusAdapter.notifyDataSetChanged();
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