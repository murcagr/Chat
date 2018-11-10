package e.rezeda.chat.ViewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import e.rezeda.chat.Models.Message;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class ChatViewModel extends ViewModel {

    MutableLiveData<Message> messages;

    private OkHttpClient client;


    public MutableLiveData<Message> getMessages() {
        if (messages == null) {
            messages = new MutableLiveData<>();
            loadData();
        }
        return messages;
    }

    private void loadData() {
        Request request = new Request.Builder().url("ws://85.255.1.214:8083").build();
//        EchoWebSocketListener listener = new EchoWebSocketListener();
     //   WebSocket ws = client.newWebSocket(request, listener);
       // ws.send("{\"type\": \"getMessagesForChat\", \"from_who\" : \"lalala@gmail.com\", \"to_who\" : \"lala\"}");

       // client.dispatcher().executorService().shutdown();
    }

}
