package com.mathieu_mercier.shwitter.controller.bottom_nav;

import android.os.Bundle;

import com.mathieu_mercier.shwitter.R;

public class FriendRequestActivity extends BottomActivity {

    int getMenuItemId() {
        return R.id.friendrequest;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_friend_request);
        // On assigne la vue avant d'appeler super, car la classe parent initialise la bottom nav
        super.onCreate(savedInstanceState);
    }
}