package com.mathieu_mercier.shwitter;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.mathieu_mercier.shwitter.api.UserSelectFetchListener;
import com.mathieu_mercier.shwitter.controller.bottom_nav.FriendAdapter;
import com.mathieu_mercier.shwitter.databinding.RecyclerViewFriendBinding;
import com.mathieu_mercier.shwitter.databinding.RecyclerViewMpBinding;
import com.mathieu_mercier.shwitter.model.Message;
import com.mathieu_mercier.shwitter.model.Relation;
import com.mathieu_mercier.shwitter.model.User;
import com.mathieu_mercier.shwitter.model.UserService;

import java.util.ArrayList;

public class MpAdapter extends RecyclerView.Adapter<MpAdapter.MpViewHolder>{
    private ArrayList<Message> messages;
    private ViewGroup parent;


    public MpAdapter(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public MpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        RecyclerViewMpBinding binding = RecyclerViewMpBinding.inflate(LayoutInflater.from(parent.getContext()), parent , false);
        return new MpAdapter.MpViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MpViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MpViewHolder extends RecyclerView.ViewHolder {
        RecyclerViewMpBinding binding;


        public MpViewHolder(@NonNull RecyclerViewMpBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
        @RequiresApi(api = Build.VERSION_CODES.O)
        public void bind(Message message) {
            Log.d("MP" , "bind:"+message.toString());

            int userid = message.getUserId();
            UserService.getInstance().getUserById(userid, new UserSelectFetchListener() {
                @Override
                public void onResponse(ArrayList<User> Users) {
                    binding.username.setText(Users.get(0).getUsername() + ":");
                }
            }, parent.getContext());
            binding.content.setText(message.getContent() );
            binding.timeOfMp.setText("");
        }
    }
}
