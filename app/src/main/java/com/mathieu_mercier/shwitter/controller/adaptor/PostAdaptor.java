package com.mathieu_mercier.shwitter.controller.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mathieu_mercier.shwitter.controller.bottom_nav.homePageActivity;
import com.mathieu_mercier.shwitter.databinding.RecyclerViewPostBinding;
import com.mathieu_mercier.shwitter.model.Post;

import java.util.ArrayList;


public class PostAdaptor extends RecyclerView.Adapter<PostAdaptor.PostViewHolder> {

    private final onPostClickListener PostClickListener;
    private ArrayList<Post> post;
    public PostAdaptor( onPostClickListener postClickListener){
        this.PostClickListener = postClickListener;
        this.post=new ArrayList<>();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewPostBinding  binding = RecyclerViewPostBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new PostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post1 = post.get(position);
        holder.bind(post1);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setPost(ArrayList<Post> posts) {
        this.post=posts;
    }


    public class PostViewHolder extends RecyclerView.ViewHolder
    {
        private RecyclerViewPostBinding   binding;
        public PostViewHolder(@NonNull RecyclerViewPostBinding binding) {

            super(binding.getRoot());
        }

        public void bind(Post post1) {

            binding.texteEditView.setText(post1.getContent());
            binding.titreEditView.setText(post1.getTitle());
            //binding.usernameRwPost.setText(getUserById());

            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PostClickListener.onPostClicked(post1);
                }
            });
        }
    }
}
