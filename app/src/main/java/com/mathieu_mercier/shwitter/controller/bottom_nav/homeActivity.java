package com.mathieu_mercier.shwitter.controller.bottom_nav;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mathieu_mercier.shwitter.R;
import com.mathieu_mercier.shwitter.api.PostFetchListener;
import com.mathieu_mercier.shwitter.databinding.ActivityFriendRequestBinding;
import com.mathieu_mercier.shwitter.databinding.ActivityHomePageBinding;
import com.mathieu_mercier.shwitter.model.Post;
import com.mathieu_mercier.shwitter.model.PostService;
import com.mathieu_mercier.shwitter.model.UserService;

import java.util.ArrayList;

public class homeActivity extends BottomActivity {
    private ActivityHomePageBinding binding;
    private HomeAdapter homeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setContentView(R.layout.activity_home_page);
        // On assigne la vue avant d'appeler super, car la classe parent initialise la bottom nav
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        super.onCreate(savedInstanceState);


        /*binding.recyclerMyPost.setHasFixedSize(true);
        binding.recyclerMyPost.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        binding.recyclerMyPost.setLayoutManager(new LinearLayoutManager(this));

        refreshUserPost();*/
    }



    int getMenuItemId() {
        return R.id.home;
    }

    private void refreshUserPost(){
        PostService.getInstance().getPostById(UserService.getInstance().getCurrentUser().getId(), new PostFetchListener() {
            @Override
            public void OnRespond(ArrayList<Post> Posts) {

                homeAdapter = new HomeAdapter(Posts);
                binding.recyclerMyPost.setAdapter(homeAdapter);

            }
        } ,this);
    }


}
