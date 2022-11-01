package com.mathieu_mercier.shwitter.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;

public class Relation {
    private int id;
    private int target_id;
    private int user_id;
    private boolean accepted;


    public int getId() {
        return id;
    }
    public int getTargetId() {
        return target_id;
    }
    public int getUserId() {
        return user_id;
    }
    public boolean getAccepted(){return accepted; }


    public Relation(JSONObject relationJson) throws JSONException {

        this.id = relationJson.getInt("id");
        this.target_id = relationJson.getInt("target_id");
        this.user_id = relationJson.getInt("user_id");
        int tryaccepted = relationJson.getInt("accepted");
        if(tryaccepted == 0)
        {
            this.accepted = false;
        }
        else
        {
            this.accepted = true ;
        }


    }


    public Relation(int id, int target_id, int user_id , boolean accepted) {

        this.id = id;
        this.target_id = target_id;
        this.user_id = user_id;
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", relation_id='" + target_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", accepted='" + accepted + '\'' +
                '}';
    }
}
