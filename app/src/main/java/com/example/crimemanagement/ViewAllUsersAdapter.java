package com.example.crimemanagement;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewAllUsersAdapter extends RecyclerView.Adapter<ViewAllUsersAdapter.ViewHolder> {

    Context context;
    List<ViewAllUsersClass> viewAllUsersClasses;



    public ViewAllUsersAdapter(Context context, List<ViewAllUsersClass> viewAllUsersClasses) {
        this.context = context;
        this.viewAllUsersClasses = viewAllUsersClasses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eachuserinformation,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllUsersAdapter.ViewHolder holder, int position) {
        ViewAllUsersClass viewAllUsersClass = viewAllUsersClasses.get(position);
        holder.personaadhaarnumber.setText("Aadhaar : "+viewAllUsersClass.getAadhaarnumber());
        holder.personusername.setText("Username : "+viewAllUsersClass.getUsername());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("User Information");

                // Set Alert Title
                builder.setTitle("User Information");
                builder.setMessage("Aadhaar : "+viewAllUsersClass.getAadhaarnumber()+"\n"+"Username : "+viewAllUsersClass.getUsername()
                +"\n"+"Phone Number : "+viewAllUsersClass.getPhonenumber()+"\n" + "Full Name : "+viewAllUsersClass.getFullname());

                // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                builder.setCancelable(false);


                // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setNegativeButton("Close", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // If user click no then dialog box is canceled.
                    dialog.cancel();
                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();
                // Show the Alert Dialog box
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return viewAllUsersClasses.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView personusername,personaadhaarnumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            personusername = itemView.findViewById(R.id.personusername);
            personaadhaarnumber= itemView.findViewById(R.id.personaadhaarnumber);
        }

    }

}
