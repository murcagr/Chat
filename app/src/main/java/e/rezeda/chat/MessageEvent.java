package com.example;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import e.rezeda.chat.JSONChatListMessages;

public class MessageEvent {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private List<JSONChatListMessages> data = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public  List<JSONChatListMessages> getData() {
        return data;
    }

    public void setData( List<JSONChatListMessages> data) {
        this.data = data;
    }

}