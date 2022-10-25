package com.mathieu_mercier.shwitter.controller;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mathieu_mercier.shwitter.R;
import com.mathieu_mercier.shwitter.api.UserRequestListener;
import com.mathieu_mercier.shwitter.databinding.ActivityLogin2Binding;
import com.mathieu_mercier.shwitter.model.UserService;
import com.mathieu_mercier.shwitter.controller.bottom_nav.homeActivity;

public class login2Activity extends AppCompatActivity {

    public ActivityLogin2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        binding = ActivityLogin2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent = new Intent(login2Activity.this, SignupActivity.class );
                startActivity(signupIntent);
            }
        });
        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                String username = binding.LoginUsername.getText().toString();
                String password = binding.LoginPassword.getText().toString();


                UserService.getInstance().logIn(username, password, new UserRequestListener() {
                    @Override
                    public void onResponse(boolean success) {
                        if(success) {
                            loggedIn();
                        }
                        else
                        {
                            Toast.makeText(login2Activity.this, "invalid credential", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, login2Activity.this);
            }
        });

        if(UserService.getInstance().getCurrentUser() != null)
        {
            loggedIn();
        }


    }
    private void loggedIn()
    {
        Intent LogedInIntent = new Intent(login2Activity.this, homeActivity.class);
        startActivity(LogedInIntent);

        finishAffinity();
    }
}