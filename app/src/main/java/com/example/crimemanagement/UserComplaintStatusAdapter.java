package com.example.crimemanagement;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.crimemanagement.SignInPage.globalusername;

public class UserComplaintStatusAdapter extends RecyclerView.Adapter<UserComplaintStatusAdapter.ViewHolder> {

    Context context;
    FirebaseDatabase database;
    List<UserComplaintStatusModel> complaintStatusModelList;

    public UserComplaintStatusAdapter(Context context, List<UserComplaintStatusModel> complaintStatusModels) {
        this.context = context;
        this.complaintStatusModelList = complaintStatusModels;
        database = FirebaseDatabase.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.usereachcomplaint,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserComplaintStatusModel userComplaintStatusModel = complaintStatusModelList.get(position);

        holder.date.setText("Date : "+userComplaintStatusModel.getDate().substring(0,11));
        holder.id.setText("Id : "+userComplaintStatusModel.getComplaintid());
        holder.zone.setText("Zone : "+userComplaintStatusModel.getZone());
        holder.type.setText("Type : "+userComplaintStatusModel.getType());
        holder.status.setText("Status : "+userComplaintStatusModel.getStatus());
        String imageUrl = userComplaintStatusModel.getImageURL();
        Picasso.get().load(imageUrl).into(holder.imageView);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.getReference().child("users").child(globalusername).child("complaintslist")
                        .child(userComplaintStatusModel.getComplaintid()).removeValue();
                Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_LONG).show();
               Intent deleteafter = new Intent();
               deleteafter.setClass(context,UserLoggedIn.class);
               context.startActivity(deleteafter);
            }
        });
        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Confirmation Message");
                alert.setMessage("Do you to withdraw complaint ? ");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // deleting from user complaints list
                        database.getReference().child("users").child(globalusername).child("complaintslist")
                                .child(userComplaintStatusModel.getComplaintid()).removeValue();

                        //deleting from police database
                        database.getReference().child(userComplaintStatusModel.getZone())
                                .child(userComplaintStatusModel.getComplaintid()).child("status").setValue("withdraw");

                        Toast.makeText(context, "Withdrawl Successful", Toast.LENGTH_LONG).show();
                        Intent withdrawafter = new Intent();
                        withdrawafter.setClass(context,UserLoggedIn.class);
                        context.startActivity(withdrawafter);

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show();
                    }
                });
                alert.create().show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return complaintStatusModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView id,zone,date,type,status;
        Button button,button2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.statuspersonimage);
            id = itemView.findViewById(R.id.statuscomplaintid);
            zone = itemView.findViewById(R.id.statuszone);
            date = itemView.findViewById(R.id.statusdate);
            type = itemView.findViewById(R.id.statustype);
            status = itemView.findViewById(R.id.statuscomplaint);
            button = itemView.findViewById(R.id.deletecomplaintbutton);
            button2 = itemView.findViewById(R.id.withdrawcomplaintbutton);
        }
    }
}
