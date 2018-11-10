package e.rezeda.chat;

import android.app.Application;
import android.util.Log;


public class SocketConnection extends Application {

    private static ExampleSocketConnection exampleSocketConnection;

    @Override
    public void onCreate() {
        super.onCreate();

        exampleSocketConnection = new ExampleSocketConnection(this);
        BackgroundManager.get(this).registerListener(appActivityListener);
        //exampleSocketConnection.addMessageToQueue("{\"type\": \"getMessagesForChatList\", \"username\" : \"admin\"}");
    }


    public void closeSocketConnection() {
        exampleSocketConnection.closeConnection();
    }

    public void openSocketConnection() {
        exampleSocketConnection.openConnection();
    }

    public ExampleSocketConnection getExampleSocketConnection() {
        return exampleSocketConnection;
    }

    public boolean isSocketConnected() {
        return exampleSocketConnection.isConnected();
    }


    public void sendMessage(String message){
        exampleSocketConnection.addMessageToQueue(message);
    }

    public void reconnect() {
        exampleSocketConnection.openConnection();
    }
    private BackgroundManager.Listener appActivityListener = new BackgroundManager.Listener() {
        public void onBecameForeground() {
            openSocketConnection();
            Log.i("Websocket", "Became Foreground");
        }

        public void onBecameBackground() {
            closeSocketConnection();
            Log.i("Websocket", "Became Background");
        }
    };
}
