package com.mathieu_mercier.shwitter.controller.bottom_nav;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mathieu_mercier.shwitter.R;
import com.mathieu_mercier.shwitter.api.RelationFetchListener;
import com.mathieu_mercier.shwitter.databinding.ActivityFriendBinding;
import com.mathieu_mercier.shwitter.databinding.ActivityFriendRequestBinding;
import com.mathieu_mercier.shwitter.model.Relation;
import com.mathieu_mercier.shwitter.model.RelationService;
import com.mathieu_mercier.shwitter.model.UserService;

import java.util.ArrayList;

public class FriendActivity extends BottomActivity {
    private ActivityFriendBinding binding;
    private FriendAdapter friendAdapter;

    int getMenuItemId() {
        return R.id.friend;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_friend);
        // On assigne la vue avant d'appeler super, car la classe parent initialise la bottom nav
        super.onCreate(savedInstanceState);

        binding = ActivityFriendBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerFriend.setHasFixedSize(true);
        binding.recyclerFriend.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        binding.recyclerFriend.setLayoutManager(new LinearLayoutManager(this));

        refreshFriendList();

    }

    private void refreshFriendList()
    {
        RelationService.getInstance().getUserRelation(UserService.getInstance().getCurrentUser().getId(), new RelationFetchListener() {
            @Override
            public void OnRespond(ArrayList<Relation> Relations) {
                ArrayList<Relation> FriendList = new ArrayList<Relation>();
                for( Relation relation : Relations)
                {
                    if(relation.getAccepted() == true)
                    {
                        FriendList.add(relation);
                    }


                    friendAdapter = new FriendAdapter(FriendList);
                    binding.recyclerFriend.setAdapter(friendAdapter);


                }
            }
        }, this );
    }
}