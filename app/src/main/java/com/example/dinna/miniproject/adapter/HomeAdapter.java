package com.example.dinna.miniproject.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dinna.miniproject.R;
import com.example.dinna.miniproject.model.HomeData;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter {
    private ArrayList<HomeData> imageList;
    private Context context;
    private HomeViewHolder vh;

    public HomeAdapter(Context context, ArrayList<HomeData> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_adapter, parent, false);
        // set the view's size, margins, paddings and layout parameters
        vh = new HomeViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder = vh;
        Context context = vh.image.getContext();
        int id = context.getResources().getIdentifier(imageList.get(position).getThumbnail(), "drawable", context.getPackageName());
        vh.image.setImageResource(id);
    }


    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder{
        ImageView image;

        public HomeViewHolder(View v) {
            super(v);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
