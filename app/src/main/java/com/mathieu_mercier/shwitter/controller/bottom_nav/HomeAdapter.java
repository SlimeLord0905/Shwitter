package com.mathieu_mercier.shwitter.controller.bottom_nav;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mathieu_mercier.shwitter.model.Post;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private final ArrayList<Post> posts;

    public HomeAdapter(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder{


        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return posts.size();
    }
}

