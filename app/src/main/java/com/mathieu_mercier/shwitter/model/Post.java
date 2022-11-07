package com.mathieu_mercier.shwitter.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;

public class Post {
    private int id;
    private int user_id;
    private String title;
    private String content;
    private Date created_at;
    private Date updated_at;
    private String image;

    public int getId() {
        return id;
    }
    public int getUserId() {
        return user_id;
    }
    public String getTitle() {
        return title;
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
    public String getImage() {
        return image;
    }


    public Post(JSONObject postJson) throws JSONException {

        this.id = postJson.getInt("id");
        this.user_id = postJson.getInt("user_id");
        this.title = postJson.getString("title");
        this.content = postJson.getString("content");
        Timestamp stamp = new Timestamp(postJson.getLong("created_at"));
        this.created_at = new Date(stamp.getTime());
        stamp = new Timestamp(postJson.getLong("updated_at"));
        this.created_at = new Date(stamp.getTime());
        this.image = postJson.getString("image");
    }

    public Post(int id, int relation_id, int user_id , String content, Date created_at, Date updated_at) {

        this.id = id;
        this.user_id = user_id;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", content='" + content + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + created_at + '\'' +
                '}';
    }
}
