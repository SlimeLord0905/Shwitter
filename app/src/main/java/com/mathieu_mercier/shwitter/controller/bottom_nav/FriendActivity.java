package com.mathieu_mercier.shwitter.controller.bottom_nav;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mathieu_mercier.shwitter.MpActivity;
import com.mathieu_mercier.shwitter.R;
import com.mathieu_mercier.shwitter.api.OnFriendClickListener;
import com.mathieu_mercier.shwitter.api.RelationFetchListener;
import com.mathieu_mercier.shwitter.databinding.ActivityFriendBinding;
import com.mathieu_mercier.shwitter.databinding.ActivityFriendRequestBinding;
import com.mathieu_mercier.shwitter.model.Relation;
import com.mathieu_mercier.shwitter.model.RelationService;
import com.mathieu_mercier.shwitter.model.UserService;

import java.util.ArrayList;

public class FriendActivity extends BottomActivity implements OnFriendClickListener {
    private ActivityFriendBinding binding;
    private FriendAdapter friendAdapter;
    public static String KEY_EXTRA_MESSAGE = "shwitter.mathieu_mercier,com";


    int getMenuItemId() {
        return R.id.friend;
    }
    FriendActivity parent = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityFriendBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_friend);
        // On assigne la vue avant d'appeler super, car la classe parent initialise la bottom nav
        super.onCreate(savedInstanceState);



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


                    friendAdapter = new FriendAdapter(FriendList, parent);
                    binding.recyclerFriend.setAdapter(friendAdapter);


                }
            }
        }, this );
    }

    @Override
    public void onFriendClicked(Relation relation) {
       Intent messageListingIntent = new Intent(FriendActivity.this, MpActivity.class);
        messageListingIntent.putExtra(KEY_EXTRA_MESSAGE, relation);
        startActivity(messageListingIntent);
    }
}