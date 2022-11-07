package com.mathieu_mercier.shwitter.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;

public class Message {
    private int id;
    private int relation_id;
    private int user_id;
    private String content;
    private Date created_at;
    private Date updated_at;

    public int getId() {
        return id;
    }
    public int getRelationId() {
        return relation_id;
    }
    public int getUserId() {
        return user_id;
    }
    public String getContent() {
        return content;
    }
    public Date getCreatedAt() {
        return created_at;
    }
    public Date getUpdatedAt() {
        return updated_at;
    }

    public Message(JSONObject messageJson) throws JSONException {

        this.id = messageJson.getInt("id");
        this.relation_id = messageJson.getInt("relation_id");
        this.user_id = messageJson.getInt("user_id");
        this.content = messageJson.getString("content");
        /*Timestamp stamp = new Timestamp(messageJson.getLong("created_at"));
        this.created_at = new Date(stamp.getTime());
        stamp = new Timestamp(messageJson.getLong("updated_at"));
        this.created_at = new Date(stamp.getTime());*/

    }



    public Message(int id, int relation_id, int user_id , String content, Date created_at, Date updated_at) {

        this.id = id;
        this.relation_id = relation_id;
        this.user_id = user_id;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", relation_id='" + relation_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", content='" + content + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + created_at + '\'' +
                '}';
    }
}
