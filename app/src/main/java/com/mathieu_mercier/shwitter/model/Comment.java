package com.mathieu_mercier.shwitter.model;

import org.json.JSONException;
import org.json.JSONObject;


import java.sql.Timestamp;
import java.util.Date;

public class Comment {

    private int id;
    private int post_id;
    private String content;
    private Date created_at;
    private Date updated_at;

    public Comment(JSONObject commentJson) throws JSONException {

        this.id = commentJson.getInt("id");
        this.post_id = commentJson.getInt("post_id");
        this.content = commentJson.getString("username");
        Timestamp stamp = new Timestamp(commentJson.getLong("created_at"));
        this.created_at = new Date(stamp.getTime());
        stamp = new Timestamp(commentJson.getLong("updated_at"));
        this.created_at = new Date(stamp.getTime());

    }

    public int getId() {
        return id;
    }
    public int getPostId() {
        return post_id;
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

    public Comment(int id, int post_id , String content, Date created_at, Date updated_at) {

        this.id = id;
        this.post_id = post_id;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", post_id='" + post_id + '\'' +
                ", content='" + content + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + created_at + '\'' +
                '}';
    }
}
