package e.rezeda.chat;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class MessageEvent {

    public static int  EVENT_CHAT_LISTS = 1;

    @SerializedName("event")
    private int event;

    public final String message;

    public MessageEvent(String message) {

        this.message = message;
    }

    public int getType() {
        return event;
    }

    //public <T> T getParams(Class<T> type) {
        //return new Gson().fromJson(params.toString(), type);
    //}


}