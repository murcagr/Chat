package e.rezeda.chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

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
            if(!messages.isEmpty()){
                sendText(messages.poll());
            }
        }ss
        startCheckConnection();
    };

    private Runnable checkMessageQueueRunnable = () -> {
        if (!clientWebSocket.getConnection().isOpen()) {
            if(!messages.isEmpty()){
                sendText(messages.poll());
            }
        }
        startCheckConnection();
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

        socketConnectionHandler.postDelayed(checkMessageQueueRunnable, 100);
    }

    private void stopCheckMessageQueue() {
        socketConnectionHandler.removeCallbacks(checkConnectionRunnable);
    }


    public void sendText(String message) {
        clientWebSocket.send("{\"type\": \"getMessagesForChatList\", \"username\" : \"admin\"}");
    }



    public ExampleSocketConnection(Context context) {
        messages = new LinkedList();
        this.context = context;
        socketConnectionHandler = new Handler();
    }

    public static void  init(Context context) {
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
        EventBus.getDefault().post(new MessageEvent(message));
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