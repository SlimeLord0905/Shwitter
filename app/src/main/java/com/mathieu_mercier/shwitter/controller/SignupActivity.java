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
import com.mathieu_mercier.shwitter.databinding.ActivitySignupBinding;
import com.mathieu_mercier.shwitter.model.UserService;

public class SignupActivity extends AppCompatActivity {

    public ActivitySignupBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                String password = binding.SignUpPassword.getText().toString();
                String passwordConfirmation = binding.SignUpPasswordConfirmation.getText().toString();

                if(password.compareTo((passwordConfirmation)) == 0)
                {
                    String username = binding.SignUpUsername.getText().toString();



                    UserService.getInstance().signUp(username, password, new UserRequestListener() {
                        @Override
                        public void onResponse(boolean success) {
                            if(success) {

                                Intent signedUp = new Intent(SignupActivity.this, login2Activity.class);
                                SignupActivity.this.startActivity(signedUp);

                                finishAffinity();
                            }
                            else
                            {
                                Toast.makeText(SignupActivity.this, "Invalide Info", Toast.LENGTH_SHORT).show();

                            }
                        }
                    },SignupActivity.this);

                }
                else
                {
                    Toast.makeText(SignupActivity.this, "Password confirmation doesn't match", Toast.LENGTH_SHORT).show();
                }




            }
        });
    }
}