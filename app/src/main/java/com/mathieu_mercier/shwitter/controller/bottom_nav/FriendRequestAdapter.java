package com.mathieu_mercier.shwitter.controller.bottom_nav;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.mathieu_mercier.shwitter.api.UserSelectFetchListener;
import com.mathieu_mercier.shwitter.databinding.RecyclerViewFriendRequestBinding;
import com.mathieu_mercier.shwitter.model.Relation;
import com.mathieu_mercier.shwitter.model.User;
import com.mathieu_mercier.shwitter.model.UserService;

import java.util.ArrayList;

public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestAdapter.FriendRequestViewHolder> {

    private final ArrayList<Relation> friendrequests;
    private ViewGroup parent;

    public FriendRequestAdapter(ArrayList<Relation> friendRequests) {
        this.friendrequests = friendRequests;
    }

    public class FriendRequestViewHolder extends RecyclerView.ViewHolder{


        private final RecyclerViewFriendRequestBinding binding;

        public FriendRequestViewHolder(@NonNull RecyclerViewFriendRequestBinding binding) {
            super(binding.getRoot());
            
            this.binding = binding;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void bind(Relation relation) {
            if(relation.getUserId() == UserService.getInstance().getCurrentUser().getId()) {
                UserService.getInstance().getUserById(relation.getTargetId(), new UserSelectFetchListener() {
                    @Override
                    public void onResponse(ArrayList<User> Users) {
                        binding.usernameText.setText(Users.get(0).getUsername());
                    }
                }, parent.getContext() );
            }
        }
    }

    @NonNull
    @Override
    public FriendRequestAdapter.FriendRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        RecyclerViewFriendRequestBinding binding = RecyclerViewFriendRequestBinding.inflate(LayoutInflater.from(parent.getContext()), parent , false);
        return new FriendRequestAdapter.FriendRequestViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull FriendRequestAdapter.FriendRequestViewHolder holder, int position) {
        Relation relation = friendrequests.get(position);

        holder.bind(relation);
    }


    @Override
    public int getItemCount() {
        return friendrequests.size();
    }
}

