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
import com.mathieu_mercier.shwitter.controller.editCommentsActivity;
import com.mathieu_mercier.shwitter.databinding.ActivityHomePageBinding;
import com.mathieu_mercier.shwitter.model.Post;
import com.mathieu_mercier.shwitter.model.PostService;
import com.mathieu_mercier.shwitter.model.Relation;
import com.mathieu_mercier.shwitter.model.UserService;
import com.mathieu_mercier.shwitter.controller.editPostActivity;

import java.util.ArrayList;

public class homePageActivity extends BottomActivity  implements  onPostClickListener  {
    private ActivityHomePageBinding binding;
    public static String KEY_TO_POST_COMMENTS= "com.mathieu_mercier.edit-comment-extra";
    private PostAdaptor postAdaptor;

    homePageActivity parent =this;
    private int getCurrentUserId() { return UserService.getInstance().getCurrentUser().getId();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        super.onCreate(savedInstanceState);



        binding.recyclerPost.setHasFixedSize(true);
        binding.recyclerPost.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        binding.recyclerPost.setLayoutManager(new LinearLayoutManager(this));
        postAdaptor = new PostAdaptor(parent);
        binding.recyclerPost.setAdapter(postAdaptor);

        refreshPost();
        binding.fabAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editPostIntent =new Intent(homePageActivity.this,editPostActivity.class);
                startActivity(editPostIntent);
                finishAffinity();
            }
        });


    }

    @Override
    int getMenuItemId() {
        return R.id.home;
    }

    private void refreshPost() {
        PostService.getInstance().getPostById(getCurrentUserId(), new PostFetchListener() {
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
        Intent editPostIntent =new Intent(homePageActivity.this, editCommentsActivity.class);
       // editPostIntent.putExtra(KEY_TO_POST_COMMENTS,post);
        startActivity(editPostIntent);
        finishAffinity();

    }
}
