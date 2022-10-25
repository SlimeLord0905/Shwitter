package com.mathieu_mercier.shwitter.model;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mathieu_mercier.shwitter.api.MarthaQueue;
import com.mathieu_mercier.shwitter.api.MarthaRequest;
import com.mathieu_mercier.shwitter.api.MessageFetchListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MessageService {
    private static MessageService instance = null;

    private MessageService() {
    }

    public static MessageService getInstance() {
        if (instance == null) {
            instance = new MessageService();
        }

        return instance;
    }


    public void getRelationMessage(int relation_id, MessageFetchListener Listener, Context context) {
        JSONObject fetchParams = new JSONObject();
        try {
            fetchParams.put("relation_id", relation_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MarthaRequest fetchRequest = new MarthaRequest("select-relation-message", fetchParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    ArrayList<Message> messages = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {
                        messages.add(new Message(data.getJSONObject(i)));
                    }

                    Listener.OnRespond((messages));
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
