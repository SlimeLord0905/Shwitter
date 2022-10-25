package com.mathieu_mercier.shwitter.api;

import com.mathieu_mercier.shwitter.model.Comment;

import java.util.ArrayList;

public interface CommentFetchListener {
        void OnRespond(ArrayList<Comment> Comments);


}
