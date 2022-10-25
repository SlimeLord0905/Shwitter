package com.mathieu_mercier.shwitter.model;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mathieu_mercier.shwitter.api.MarthaQueue;
import com.mathieu_mercier.shwitter.api.MarthaRequest;
import com.mathieu_mercier.shwitter.api.MessageFetchListener;
import com.mathieu_mercier.shwitter.api.PostFetchListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PostService {
    private static PostService instance = null;

    private PostService() {
    }

    public static PostService getInstance() {
        if (instance == null) {
            instance = new PostService();
        }

        return instance;
    }


    public void getAll(PostFetchListener Listener, Context context) {
        JSONObject fetchParams = new JSONObject();

        MarthaRequest fetchRequest = new MarthaRequest("select-all-post", fetchParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    ArrayList<Post> posts = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {
                        posts.add(new Post(data.getJSONObject(i)));
                    }

                    Listener.OnRespond((posts));
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

    public void getPostById(int user_id, PostFetchListener Listener, Context context) {
        JSONObject fetchParams = new JSONObject();
        try {
            fetchParams.put("user_id", user_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MarthaRequest fetchRequest = new MarthaRequest("select-user-post", fetchParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    ArrayList<Post> posts = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {
                        posts.add(new Post(data.getJSONObject(i)));
                    }

                    Listener.OnRespond((posts));
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
