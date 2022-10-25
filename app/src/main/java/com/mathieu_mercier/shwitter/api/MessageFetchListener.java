package com.mathieu_mercier.shwitter.api;

import com.mathieu_mercier.shwitter.model.Comment;
import com.mathieu_mercier.shwitter.model.Message;

import java.util.ArrayList;

public interface MessageFetchListener {
    void OnRespond(ArrayList<Message> Messages);
}
