package e.rezeda.chat.Services;

import android.app.Service;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import e.rezeda.chat.WsConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class ServiceWeb extends Service{

    final String LOG_TAG = "myLogs";

    OkHttpClient client;
    Request request = new Request.Builder().url(WsConfig.URL_WEBSOCKET).build();
    EchoWebSocketListener listener = new EchoWebSocketListener();
    WebSocket ws = client.newWebSocket(request, listener);


    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand");
        sendMessage();
        return super.onStartCommand(intent, flags, startId);
    }

    private void sendMessage() {
        ws.send("{\"type\": \"getMessagesForChatList\"}");
    }


}