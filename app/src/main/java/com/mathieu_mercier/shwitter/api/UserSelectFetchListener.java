package com.mathieu_mercier.shwitter.api;

import com.mathieu_mercier.shwitter.model.User;

import java.util.ArrayList;

public interface UserSelectFetchListener {
    void onResponse(ArrayList<User> Users);
}
