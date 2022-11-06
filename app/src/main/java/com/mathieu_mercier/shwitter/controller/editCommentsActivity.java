package com.mathieu_mercier.shwitter.controller;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mathieu_mercier.shwitter.R;
import com.mathieu_mercier.shwitter.api.CommentFetchListener;
import com.mathieu_mercier.shwitter.controller.adaptor.CommentAdaptor;
import com.mathieu_mercier.shwitter.controller.bottom_nav.homePageActivity;
import com.mathieu_mercier.shwitter.databinding.ActivityEditCommentsBinding;
import com.mathieu_mercier.shwitter.controller.adaptor.onCommentClickListener;
import com.mathieu_mercier.shwitter.model.Comment;
import com.mathieu_mercier.shwitter.model.CommentService;
import com.mathieu_mercier.shwitter.model.UserService;

import java.util.ArrayList;

public class editCommentsActivity extends AppCompatActivity implements onCommentClickListener{
    private ActivityEditCommentsBinding binding;
    private CommentAdaptor commentAdaptor;
    private int getCurrentUserId() { return UserService.getInstance().getCurrentUser().getId();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityEditCommentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.commentRecyclerView.setHasFixedSize(true);
        binding.commentRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        binding.commentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        commentAdaptor = new CommentAdaptor(this);

        binding.commentRecyclerView.setAdapter(commentAdaptor);
        refresh();
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editPostIntent =new Intent(editCommentsActivity.this, homePageActivity.class);
                startActivity(editPostIntent);
            }
        });
        binding.buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editPostIntent =new Intent(editCommentsActivity.this,homePageActivity.class);
                startActivity(editPostIntent);
            }
        });
        setContentView(R.layout.activity_edit_comments);
    }

    private void refresh() {

        CommentService.getInstance().getPostComment(getCurrentUserId(), new CommentFetchListener() {
            @Override
            public void OnRespond(ArrayList<Comment> Comments) {

                if(Comments !=null)
                {
                    commentAdaptor.setComment(Comments);
                    commentAdaptor.notifyDataSetChanged();
                }else{
                    Toast.makeText(editCommentsActivity.this,"could not load Comments...",Toast.LENGTH_SHORT);
                }

            }
        },editCommentsActivity.this);
    }

    @Override
    public void onCommentsClicked(Comment comment) {
        binding.commentEditTextView.setText(comment.getContent());
    }
}
