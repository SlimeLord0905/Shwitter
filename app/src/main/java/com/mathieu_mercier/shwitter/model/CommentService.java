package com.mathieu_mercier.shwitter.model;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mathieu_mercier.shwitter.api.CommentFetchListener;
import com.mathieu_mercier.shwitter.api.MarthaQueue;
import com.mathieu_mercier.shwitter.api.MarthaRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CommentService {
    private static CommentService instance = null;

    private CommentService() {
    }

    public static CommentService getInstance() {
        if (instance == null) {
            instance = new CommentService();
        }

        return instance;
    }


    public void getPostComment(int post_id, CommentFetchListener Listener, Context context) {
        JSONObject fetchParams = new JSONObject();
        try {
            fetchParams.put("post_id", post_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MarthaRequest fetchRequest = new MarthaRequest("select-post-comment", fetchParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    ArrayList<Comment> comments = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {
                        comments.add(new Comment(data.getJSONObject(i)));
                    }

                    Listener.OnRespond((comments));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Listener.OnRespond(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Listener.OnRespond(null);
            }
        });

        MarthaQueue.getInstance(context).send(fetchRequest);
    }


}
