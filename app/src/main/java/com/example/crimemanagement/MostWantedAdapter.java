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

public class MostWantedAdapter extends RecyclerView.Adapter<MostWantedAdapter.ViewHolder> {

    Context context;
    List<MostWantedModel> mostWantedModels;

    public MostWantedAdapter(Context context, List<MostWantedModel> mostWantedModels) {
        this.context = context;
        this.mostWantedModels = mostWantedModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eachperson,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MostWantedAdapter.ViewHolder holder, int position) {
            MostWantedModel mostWantedModel = mostWantedModels.get(position);
            holder.crime.setText("Crime : "+mostWantedModel.getCrime());
            holder.name.setText("Name : "+mostWantedModel.getImageName());
            String imageuri=mostWantedModel.getImageURL();
            System.out.println("Image Link is : "+imageuri);
            Picasso.get().load(imageuri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mostWantedModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,crime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.personimage);
            name = itemView.findViewById(R.id.personname);
            crime = itemView.findViewById(R.id.personcrime);
        }
    }
}
