package com.mathieu_mercier.shwitter.controller;


import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mathieu_mercier.shwitter.R;
import com.mathieu_mercier.shwitter.databinding.ActivityEditPostBinding;
import com.mathieu_mercier.shwitter.databinding.ActivityHomePageBinding;
import com.mathieu_mercier.shwitter.controller.bottom_nav.homePageActivity;

public class editPostActivity extends AppCompatActivity {
    private ActivityEditPostBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editPostIntent =new Intent(editPostActivity.this,homePageActivity.class);
                startActivity(editPostIntent);
            }
        });
        binding.buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editPostIntent =new Intent(editPostActivity.this,homePageActivity.class);
                startActivity(editPostIntent);
            }
        });

        setContentView(R.layout.activity_edit_post);
    }
}