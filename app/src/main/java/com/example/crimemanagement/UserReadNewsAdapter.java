package com.example.crimemanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserReadNewsAdapter extends RecyclerView.Adapter<UserReadNewsAdapter.ViewHolder> {

    List<UserReadNewsModel> userReadNewsModelList;
    Context context;

    public UserReadNewsAdapter(List<UserReadNewsModel> userReadNewsModelList, Context context) {
        this.userReadNewsModelList = userReadNewsModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserReadNewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newsarticle,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserReadNewsAdapter.ViewHolder holder, int position) {

        holder.titletext.setText("Title : "+userReadNewsModelList.get(position).getTitle());
        holder.desctext.setText("Description : "+userReadNewsModelList.get(position).getDesc());
        holder.sourcetext.setText("Source : "+userReadNewsModelList.get(position).getSource());

    }

    @Override
    public int getItemCount() {
        return userReadNewsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titletext,desctext,sourcetext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titletext = itemView.findViewById(R.id.top_headline_title);
            desctext = itemView.findViewById(R.id.top_headline_desc);
            sourcetext = itemView.findViewById(R.id.top_headline_source);
        }
    }
}
