package com.mathieu_mercier.shwitter.controller.bottom_nav;

import android.os.Bundle;

import com.mathieu_mercier.shwitter.R;

public class FriendActivity extends BottomActivity {

    int getMenuItemId() {
        return R.id.friend;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_friend);
        // On assigne la vue avant d'appeler super, car la classe parent initialise la bottom nav
        super.onCreate(savedInstanceState);





    }
}