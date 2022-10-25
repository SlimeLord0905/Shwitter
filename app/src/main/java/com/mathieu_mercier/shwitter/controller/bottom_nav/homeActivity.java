package com.mathieu_mercier.shwitter.controller.bottom_nav;

import android.os.Bundle;

import com.mathieu_mercier.shwitter.R;

public class homeActivity extends BottomActivity {


    int getMenuItemId() {
        return R.id.home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home_page);
        // On assigne la vue avant d'appeler super, car la classe parent initialise la bottom nav
        super.onCreate(savedInstanceState);
    }
}
