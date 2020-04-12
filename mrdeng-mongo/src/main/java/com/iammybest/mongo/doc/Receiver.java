package com.iammybest.mongo.doc;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MrDeng on 2017/3/23.
 */
public class Receiver {
    private String name;

    public Receiver(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("name", this.name);
        } catch (JSONException e) {
        }
        return json;

    }
}
