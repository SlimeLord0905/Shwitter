package com.mathieu_mercier.shwitter.model;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mathieu_mercier.shwitter.api.MarthaQueue;
import com.mathieu_mercier.shwitter.api.MarthaRequest;
import com.mathieu_mercier.shwitter.api.MessageFetchListener;
import com.mathieu_mercier.shwitter.api.RelationFetchListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RelationService {
    private static RelationService instance = null;

    private RelationService() {
    }

    public static RelationService getInstance() {
        if (instance == null) {
            instance = new RelationService();
        }

        return instance;
    }


    public void getUserRelation(int user_id, RelationFetchListener Listener, Context context) {
        JSONObject fetchParams = new JSONObject();
        try {
            fetchParams.put("user_id", user_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MarthaRequest fetchRequest = new MarthaRequest("select-user-relation", fetchParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    ArrayList<Relation> relations = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {
                        relations.add(new Relation(data.getJSONObject(i)));
                    }

                    Listener.OnRespond((relations));
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
