package com.mathieu_mercier.shwitter.controller.bottom_nav;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mathieu_mercier.shwitter.R;
import com.mathieu_mercier.shwitter.api.RelationFetchListener;
import com.mathieu_mercier.shwitter.databinding.ActivityFriendRequestBinding;
import com.mathieu_mercier.shwitter.model.Relation;
import com.mathieu_mercier.shwitter.model.RelationService;
import com.mathieu_mercier.shwitter.model.UserService;


import java.util.ArrayList;

public class FriendRequestActivity extends BottomActivity {
    private ActivityFriendRequestBinding binding;
    private FriendRequestAdapter friendAdapter;

    int getMenuItemId() {
        return R.id.friendrequest;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setContentView(R.layout.activity_friend_request);

        binding = ActivityFriendRequestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        super.onCreate(savedInstanceState);


        binding.recyclerFriendrequest.setHasFixedSize(true);
        binding.recyclerFriendrequest.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        binding.recyclerFriendrequest.setLayoutManager(new LinearLayoutManager(this));


        refreshFriendRequestList();

        // On assigne la vue avant d'appeler super, car la classe parent initialise la bottom nav

    }



    private void refreshFriendRequestList()
    {
        RelationService.getInstance().getUserRelation(UserService.getInstance().getCurrentUser().getId(), new RelationFetchListener() {
            @Override
            public void OnRespond(ArrayList<Relation> Relations) {
                ArrayList<Relation> FriendRequests = new ArrayList<Relation>();
                for( Relation relation : Relations)
                {
                    if(relation.getAccepted() == false && relation.getTargetId() == UserService.getInstance().getCurrentUser().getId())
                    {
                        FriendRequests.add(relation);
                    }
                    friendAdapter = new FriendRequestAdapter(FriendRequests);
                    binding.recyclerFriendrequest.setAdapter(friendAdapter);


                }
            }
        }, this );
    }



}