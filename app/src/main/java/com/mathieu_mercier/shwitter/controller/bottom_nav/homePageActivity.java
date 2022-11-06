package com.mathieu_mercier.shwitter.controller.bottom_nav;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mathieu_mercier.shwitter.R;
import com.mathieu_mercier.shwitter.api.PostFetchListener;
import com.mathieu_mercier.shwitter.controller.adaptor.PostAdaptor;
import com.mathieu_mercier.shwitter.controller.adaptor.onPostClickListener;
import com.mathieu_mercier.shwitter.databinding.ActivityHomePageBinding;
import com.mathieu_mercier.shwitter.model.Post;
import com.mathieu_mercier.shwitter.model.PostService;
import com.mathieu_mercier.shwitter.model.UserService;
import com.mathieu_mercier.shwitter.controller.editPostActivity;

import java.util.ArrayList;

public class homePageActivity extends AppCompatActivity implements onPostClickListener {
    private ActivityHomePageBinding binding;
    private PostAdaptor postAdaptor;


    private int getCurrentUserId() { return UserService.getInstance().getCurrentUser().getId();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerPost.setHasFixedSize(true);
        binding.recyclerPost.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        binding.recyclerPost.setLayoutManager(new LinearLayoutManager(this));

        postAdaptor = new PostAdaptor(this);
        binding.recyclerPost.setAdapter(postAdaptor);
        refresh();
        binding.fabAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editPostIntent =new Intent(homePageActivity.this,editPostActivity.class);
                startActivity(editPostIntent);
            }
        });

        setContentView(R.layout.activity_home_page);

    }

    private void refresh() {
        PostService.getInstance().getAll( new PostFetchListener() {
            @Override
            public void OnRespond(ArrayList<Post> Posts) {
                if(Posts!=null){
                    postAdaptor.setPost(Posts);
                    postAdaptor.notifyDataSetChanged();
                }else{
                    Toast.makeText(homePageActivity.this,"could not load Post...",Toast.LENGTH_SHORT);
                }
            }
        },homePageActivity.this);
    }

    @Override
    public void onPostClicked(Post post) {
        Intent editPostIntent =new Intent(homePageActivity.this,editPostActivity.class);
        startActivity(editPostIntent);

    }
}
