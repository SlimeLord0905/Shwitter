package com.mathieu_mercier.shwitter.api;

import com.mathieu_mercier.shwitter.model.Message;
import com.mathieu_mercier.shwitter.model.Post;

import java.util.ArrayList;

public interface PostFetchListener {
    void OnRespond(ArrayList<Post> Posts);

}
