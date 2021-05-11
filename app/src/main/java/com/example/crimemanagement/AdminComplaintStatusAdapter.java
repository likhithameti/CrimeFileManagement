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

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.crimemanagement.ChooseComplaintAreaAdmin.complaintAreaForGettingFromDatabase;
import static com.example.crimemanagement.SignInPage.globalusername;

public class AdminComplaintStatusAdapter extends RecyclerView.Adapter<AdminComplaintStatusAdapter.ViewHolder>{
    Context context;
    FirebaseDatabase database;
    List<AdminComplaintStatusModel> adminComplaintStatusModelList;

    public AdminComplaintStatusAdapter(Context context, List<AdminComplaintStatusModel> adminComplaintStatusModels) {
        this.context = context;
        this.adminComplaintStatusModelList = adminComplaintStatusModels;
        database = FirebaseDatabase.getInstance();
    }


    @NonNull
    @Override
    public AdminComplaintStatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.admineachcomplaint,parent,false);
        return new AdminComplaintStatusAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminComplaintStatusAdapter.ViewHolder holder, int position) {
        AdminComplaintStatusModel adminComplaintStatusModel = adminComplaintStatusModelList.get(position);

        holder.name.setText("User : "+adminComplaintStatusModel.getUsernaam());
        holder.aadhaar.setText("Aadhaar : "+adminComplaintStatusModel.getAadhaar());
        holder.date.setText("Date : "+adminComplaintStatusModel.getDate().substring(0,11));
        holder.type.setText("Type : "+adminComplaintStatusModel.getCrimetype());
        holder.desc.setText("Description : "+adminComplaintStatusModel.getCrimedescription());
        holder.crimeloc.setText("Location : "+adminComplaintStatusModel.getCrimeloc());
        holder.status.setText("Status : "+adminComplaintStatusModel.getStatus());
        holder.id.setText("Id : "+adminComplaintStatusModel.getId());

        String imageURL = adminComplaintStatusModel.getImageURL();
        Picasso.get().load(imageURL).into(holder.imageView);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Confirmation Message");
                alert.setMessage("Do you to Accept complaint ? ");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // deleting from user complaints list
                        database.getReference(complaintAreaForGettingFromDatabase)
                                .child(adminComplaintStatusModel.getId())
                                .child("status").setValue("Verified");

                        //deleting from police database
                        database.getReference().child("users")
                                .child(adminComplaintStatusModel.getUsernaam())
                                .child("complaintslist")
                                .child(adminComplaintStatusModel.getId())
                                .child("status")
                                .setValue("Verified");

                        Toast.makeText(context, "Verification Done", Toast.LENGTH_LONG).show();
                        Intent verifafter = new Intent();
                        verifafter.setClass(context,AdminDashboard.class);
                        context.startActivity(verifafter);

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

        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Confirmation Message");
                alert.setMessage("Do you to Reject complaint ? ");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // deleting from user complaints list
                        database.getReference(complaintAreaForGettingFromDatabase)
                                .child(adminComplaintStatusModel.getId())
                                .child("status").setValue("Rejected");

                        //deleting from police database
                        database.getReference().child("users")
                                .child(adminComplaintStatusModel.getUsernaam()) // for changing user side
                                .child("complaintslist")
                                .child(adminComplaintStatusModel.getId())
                                .child("status")
                                .setValue("Rejected");

                        Toast.makeText(context, "Rejection Done", Toast.LENGTH_LONG).show();
                        Intent rejectafter = new Intent();
                        rejectafter.setClass(context,AdminDashboard.class);
                        context.startActivity(rejectafter);

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

        holder.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.getReference(complaintAreaForGettingFromDatabase)
                        .child(adminComplaintStatusModel.getId())
                        .removeValue();
                Toast.makeText(context, "Deletion Done", Toast.LENGTH_LONG).show();
                Intent deleter = new Intent();
                deleter.setClass(context,AdminDashboard.class);
                context.startActivity(deleter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return adminComplaintStatusModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,aadhaar,date,type,desc,crimeloc,status,id;
        Button button,button2,button3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.adminimagecomplaint);
            name = itemView.findViewById(R.id.unamecomplaint);
            aadhaar = itemView.findViewById(R.id.aadhaarcomplaint);
            date = itemView.findViewById(R.id.compdate);
            type = itemView.findViewById(R.id.gettype);
            desc = itemView.findViewById(R.id.crimedesc);
            crimeloc = itemView.findViewById(R.id.crimelocation);
            status = itemView.findViewById(R.id.compstatus);
            id=itemView.findViewById(R.id.compid);
            button = itemView.findViewById(R.id.verifycomplaintButton);
            button2 = itemView.findViewById(R.id.rejectcomplaintButton);
            button3 = itemView.findViewById(R.id.admindeletecomplaintButton);
        }
    }
}
