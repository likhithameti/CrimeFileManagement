package com.example.crimemanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class UserComplaintStatusAdapter extends RecyclerView.Adapter<UserComplaintStatusAdapter.ViewHolder> {

    Context context;
    List<UserComplaintStatusModel> complaintStatusModelList;

    public UserComplaintStatusAdapter(Context context, List<UserComplaintStatusModel> complaintStatusModels) {
        this.context = context;
        this.complaintStatusModelList = complaintStatusModels;
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
        System.out.println("Satus "+userComplaintStatusModel.getStatus());
        Picasso.get().load(imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return complaintStatusModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView id,zone,date,type,status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.statuspersonimage);
            id = itemView.findViewById(R.id.statuscomplaintid);
            zone = itemView.findViewById(R.id.statuszone);
            date = itemView.findViewById(R.id.statusdate);
            type = itemView.findViewById(R.id.statustype);
            status = itemView.findViewById(R.id.statuscomplaint);
        }
    }
}
