package com.mathieu_mercier.shwitter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mathieu_mercier.shwitter.api.MessageFetchListener;
import com.mathieu_mercier.shwitter.controller.bottom_nav.FriendActivity;
import com.mathieu_mercier.shwitter.controller.bottom_nav.FriendAdapter;
import com.mathieu_mercier.shwitter.controller.login2Activity;
import com.mathieu_mercier.shwitter.databinding.ActivityFriendBinding;
import com.mathieu_mercier.shwitter.databinding.ActivityMpBinding;

import com.mathieu_mercier.shwitter.model.Message;
import com.mathieu_mercier.shwitter.model.MessageService;
import com.mathieu_mercier.shwitter.model.Relation;

import java.util.ArrayList;

public class MpActivity extends AppCompatActivity {
    private ActivityMpBinding binding;
    private MpAdapter mpAdapter;
    MpActivity parent = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            Relation relation = extras.getParcelable(FriendActivity.KEY_EXTRA_MESSAGE);
            Log.d("MP" , "onCreate:"+relation.toString());

            binding.recyclerMp.setHasFixedSize(true);
            binding.recyclerMp.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
            binding.recyclerMp.setLayoutManager(new LinearLayoutManager(this));

            refreshMpList(relation);
        }else{
            Toast.makeText(MpActivity.this, "no relation received", Toast.LENGTH_SHORT).show();
        }
    }

    private void refreshMpList(Relation relation) {
        int id = relation.getId();
        MessageService.getInstance().getRelationMessage(id, new MessageFetchListener() {
            @Override
            public void OnRespond(ArrayList<Message> Messages) {

                    mpAdapter = new MpAdapter(Messages);
                    binding.recyclerMp.setAdapter(mpAdapter);


            }
        },this );





    }
}