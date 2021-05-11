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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserReadNews extends AppCompatActivity {

    private RecyclerView topheadlines;
    private List<UserReadNewsModel> userReadNewsModelList;
    private UserReadNewsAdapter userReadNewsAdapter;

    FirebaseDatabase database;
    DatabaseReference getnews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_read_news);
        database = FirebaseDatabase.getInstance();
        getnews = database.getReference().child("news");
        topheadlines = (RecyclerView)findViewById(R.id.userreadnewsrecyler);
        userReadNewsModelList = new ArrayList<>();

        userReadNewsAdapter = new UserReadNewsAdapter(userReadNewsModelList,this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        topheadlines.setLayoutManager(linearLayoutManager);
        topheadlines.setAdapter(userReadNewsAdapter);


        getnews.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                UserReadNewsModel userReadNewsModel = snapshot.getValue(UserReadNewsModel.class);
                userReadNewsModelList.add(userReadNewsModel);
                userReadNewsAdapter.notifyDataSetChanged();
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