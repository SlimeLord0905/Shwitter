package com.mathieu_mercier.shwitter.model;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.mathieu_mercier.shwitter.api.MarthaQueue;
import com.mathieu_mercier.shwitter.api.MarthaRequest;
import com.mathieu_mercier.shwitter.api.UserRequestListener;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mathieu_mercier.shwitter.api.UserSelectFetchListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserService {
    private static UserService instance = null;


    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    private UserService() {


        currentUser = null;

        // seeding data



    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }

        return instance;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void logIn(String username, String password, final UserRequestListener Listener, Context context) {

        JSONObject loginParams = new JSONObject();
        try {
            loginParams.put("username", username);
            loginParams.put("password",password);
        }catch(JSONException e)
        {
            e.printStackTrace();
        }


        MarthaRequest LoginRequest = new MarthaRequest("select-user-login", loginParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject userJson = response.getJSONArray("data").getJSONObject(0);


                    currentUser = new User(userJson);

                    Listener.onResponse(true);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Listener.onResponse(false);

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("SHWITTER", "onErrorResponse"+ error.toString());
                Listener.onResponse(false);
            }
        });

        MarthaQueue.getInstance(context).send(LoginRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void signUp(String username, String password, final UserRequestListener Listener , Context context) {


        if (username.isEmpty() || password.isEmpty() ) {
            Listener.onResponse(false);
        } else {

            JSONObject signupParams = new JSONObject();
            try {
                signupParams.put("username", username);
                signupParams.put("password",password);
            }catch(JSONException e)
            {
                e.printStackTrace();
            }


            MarthaRequest SignupRequest = new MarthaRequest("insert-user-signup", signupParams, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        int id = response.getInt("lastInsertId");


                        currentUser = new User(id, username);

                        Listener.onResponse(true);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Listener.onResponse(false);

                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("SHWITTER", "onErrorResponse"+ error.toString());
                    Listener.onResponse(false);
                }
            });

            MarthaQueue.getInstance(context).send(SignupRequest);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getUserById(int id, final UserSelectFetchListener Listener, Context context) {

        JSONObject Params = new JSONObject();
        try {
            Params.put("id", id);
        }catch(JSONException e)
        {
            e.printStackTrace();
        }


        MarthaRequest UserRequest = new MarthaRequest("select-user-by-id", Params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    ArrayList<User> users = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {
                        users.add(new User(data.getJSONObject(i)));
                    }

                    Listener.onResponse(users);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Listener.onResponse(new ArrayList<>());

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("SHWITTER", "onErrorResponse"+ error.toString());
                Listener.onResponse(new ArrayList<>(

                ));
            }
        });

        MarthaQueue.getInstance(context).send(UserRequest);
    }

    public void logOut() {
        currentUser = null;
    }
}
