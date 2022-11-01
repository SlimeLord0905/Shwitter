package com.mathieu_mercier.shwitter.controller.bottom_nav;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.mathieu_mercier.shwitter.api.UserSelectFetchListener;
import com.mathieu_mercier.shwitter.databinding.RecyclerViewFriendBinding;
import com.mathieu_mercier.shwitter.model.Relation;
import com.mathieu_mercier.shwitter.model.User;
import com.mathieu_mercier.shwitter.model.UserService;

import java.util.ArrayList;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {

    private final ArrayList<Relation> friends;
    private ViewGroup parent;

    public FriendAdapter(ArrayList<Relation> friendList) {
        this.friends = friendList;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        RecyclerViewFriendBinding binding = RecyclerViewFriendBinding.inflate(LayoutInflater.from(parent.getContext()), parent , false);
        return new FriendViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {

        Relation relation = friends.get(position);
        
        holder.bind(relation);


    }

    public class FriendViewHolder extends RecyclerView.ViewHolder{
        RecyclerViewFriendBinding binding;

        public FriendViewHolder(@NonNull RecyclerViewFriendBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void bind(Relation relation) {
            if(relation.getUserId() == UserService.getInstance().getCurrentUser().getId()) {
                UserService.getInstance().getUserById(relation.getTargetId(), new UserSelectFetchListener() {
                    @Override
                    public void onResponse(ArrayList<User> Users) {
                        binding.usernametextofficial.setText(Users.get(0).getUsername());
                    }
                }, parent.getContext() );
            }
        }
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }
}
