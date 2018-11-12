package com.example;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import e.rezeda.chat.ChatListMessages;

public class MessageEvent {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private List<ChatListMessages> data = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public  List<ChatListMessages> getData() {
        return data;
    }

    public void setData( List<ChatListMessages> data) {
        this.data = data;
    }

}