package e.rezeda.chat;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class MessageEvent {

    public static int  EVENT_CHAT_LISTS = 1;

    @SerializedName("event")
    private String type;

    public final String params;

    public MessageEvent(String params) {

        this.params = params;
    }

    public String getType() {
        return type;
    }

    //public <T> T getParams(Class<T> type) {
        //return new Gson().fromJson(params.toString(), type);
    //}


}