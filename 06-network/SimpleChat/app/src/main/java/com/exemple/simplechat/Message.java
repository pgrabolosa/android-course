package com.exemple.simplechat;

import org.json.JSONException;
import org.json.JSONObject;

public class Message {
    int id;
    String uri;

    String from;
    String body;
    String timestamp;

    public Message(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getInt("id");
        this.uri = jsonObject.getString("uri");
        this.from = jsonObject.getString("from");
        this.body = jsonObject.getString("body");
        this.timestamp = jsonObject.getString("timestamp");
    }
}
