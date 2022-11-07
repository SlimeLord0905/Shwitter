package com.mathieu_mercier.shwitter.controller.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mathieu_mercier.shwitter.controller.bottom_nav.homePageActivity;
import com.mathieu_mercier.shwitter.controller.editCommentsActivity;
import com.mathieu_mercier.shwitter.databinding.RecyclerViewCommentBinding;
import com.mathieu_mercier.shwitter.databinding.RecyclerViewPostBinding;
import com.mathieu_mercier.shwitter.model.Comment;

import java.util.ArrayList;

public class CommentAdaptor  extends RecyclerView.Adapter<CommentAdaptor.CommentViewHolder> {

    private final onCommentClickListener commentClickListener;
    ArrayList<Comment> comments;

    public CommentAdaptor(onCommentClickListener commentClickListener){
        this.comments=new ArrayList<>();
        this.commentClickListener=commentClickListener;

    }

    public void setComment(ArrayList<Comment> comments) {
        this.comments = comments;
    }


    public class CommentViewHolder extends RecyclerView.ViewHolder
    {
        private RecyclerViewCommentBinding binding;
        public CommentViewHolder(@NonNull RecyclerViewCommentBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(Comment comment1) {
            binding.comment.setText(comment1.getContent());
            // binding.usernameTextView.setText(comment1.getUserId());

            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    commentClickListener.onCommentsClicked(comment1);
                }
            });
        }
    }

    @NonNull
    @Override
    public CommentAdaptor.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewCommentBinding binding = RecyclerViewCommentBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new CommentAdaptor.CommentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdaptor.CommentViewHolder holder, int position) {

        Comment comment = comments.get(position);

        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
