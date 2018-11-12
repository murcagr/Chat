package e.rezeda.chat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class RealTimeEvent {
    @SerializedName("event")
    private int event;

    @SerializedName("params")
    private JsonObject params;

    public int getType() {
        return event;
    }

    //public String getUserId() {
        //return userId;
    //}

    public <T> T getParams(Class<T> type) {
        return new Gson().fromJson(params.toString(), type);
    }
}
