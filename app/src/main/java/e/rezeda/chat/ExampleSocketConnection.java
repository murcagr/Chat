package e.rezeda.chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.Queue;
import java.util.prefs.Preferences;


public class ExampleSocketConnection implements ClientWebSocket.MessageListener {

    private ClientWebSocket clientWebSocket;
    private Context context;
    public Gson gson = new Gson();
    private Handler socketConnectionHandler;
    private Queue<String> messages;

    private Runnable checkConnectionRunnable = () -> {
        if (!clientWebSocket.getConnection().isOpen()) {
            openConnection();
        }
        Log.i("Websocket", "Socket connected la la");
        startCheckConnection();
    };

    private Runnable checkMessageQueueRunnable = () -> {
        if (clientWebSocket.getConnection().isOpen()) {
            if(!messages.isEmpty()){
                Log.i("Websocket", "Message Sended");
                while(!messages.isEmpty()) {
                    sendText(messages.poll());
                }
            }
            else {
                Log.i("Websocket", "Message not sended");
            }
        }
        startCheckMessageQueue();
    };

    public void addMessageToQueue(String message){
        messages.add(message);
    }



    private void startCheckConnection() {

        socketConnectionHandler.postDelayed(checkConnectionRunnable, 5000);
    }

    private void stopCheckConnection() {
        socketConnectionHandler.removeCallbacks(checkConnectionRunnable);
    }


    private void startCheckMessageQueue() {

        socketConnectionHandler.postDelayed(checkMessageQueueRunnable, 30);
    }

    private void stopCheckMessageQueue() {
        socketConnectionHandler.removeCallbacks(checkConnectionRunnable);
    }


    public void sendText(String message) {
        clientWebSocket.send(message);
    }



    public ExampleSocketConnection(Context context) {
        messages = new LinkedList();
        this.context = context;
        socketConnectionHandler = new Handler();
    }

    public void openConnection() {

        String SOCKET_URL = WsConfig.URL_WEBSOCKET;
        if (clientWebSocket != null) clientWebSocket.close();
        try {
            clientWebSocket = new ClientWebSocket(this,
                    WsConfig.URL_WEBSOCKET);
            clientWebSocket.connect();
            Log.i("Websocket", "Socket connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
        initScreenStateListener();
        startCheckConnection();
        startCheckMessageQueue();
    }

    public void closeConnection() {
        if (clientWebSocket != null) {
            clientWebSocket.close();
            clientWebSocket = null;
        }
        releaseScreenStateListener();
        stopCheckConnection();
        stopCheckMessageQueue();
    }




    @Override
    public void onSocketMessage(String message) {
            //JSONObject jsonObject = new JSONObject(message);
            //JSONObject a = jsonObject.getJSONObject("type");
        message = "{\"type\":\"getMessagesForChatListResult\",\"data\":[{\"id\": 21,\"text\":\"asdasd\",\"is_read\":0,\"from_who\":\"Robert Plant\"},{\"id\":43,\"text\":\"Hello\",\"is_read\":0,\"from_who\":\"Jimmy Page\"}]}";

        com.example.MessageEvent json = gson.fromJson(message, com.example.MessageEvent.class);

        EventBus.getDefault().post(json);
            int k = 0;

        //EventBus.getDefault().post(gson.fromJson(message,RealTimeEvent.class));
        //EventBus.getDefault().post(new MessageEvent(message));
    }

    /**
     * Screen state listener for socket live cycle
     */
    private void initScreenStateListener() {
        context.registerReceiver(screenStateReceiver, new IntentFilter(Intent.ACTION_SCREEN_ON));
        context.registerReceiver(screenStateReceiver, new IntentFilter(Intent.ACTION_SCREEN_OFF));
    }

    private void releaseScreenStateListener() {
        try {
            context.unregisterReceiver(screenStateReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private BroadcastReceiver screenStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Log.i("Websocket", "Screen ON");
                openConnection();
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Log.i("Websocket", "Screen OFF");
                closeConnection();
            }
        }
    };

    public boolean isConnected() {
        return clientWebSocket != null &&
                clientWebSocket.getConnection() != null &&
                clientWebSocket.getConnection().isOpen();
    }

    public ClientWebSocket getClientWebSocket() {
        return clientWebSocket;
    }
}