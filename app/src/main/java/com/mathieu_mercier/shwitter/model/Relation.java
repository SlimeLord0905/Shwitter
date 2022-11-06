package com.mathieu_mercier.shwitter.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;

public class Relation implements Parcelable {
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

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected Relation(Parcel in) {
        id = in.readInt();
        target_id= in.readInt();
        user_id = in.readInt();
        accepted = in.readBoolean();
    }

    public static final Creator<Relation> CREATOR = new Creator<Relation>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public Relation createFromParcel(Parcel in) {
            return new Relation(in);
        }

        @Override
        public Relation[] newArray(int size) {return new Relation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(target_id);
        parcel.writeInt(user_id);
        parcel.writeBoolean(accepted);
    }
}
