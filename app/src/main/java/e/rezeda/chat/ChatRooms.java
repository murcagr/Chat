package e.rezeda.chat;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import e.rezeda.chat.SocketConnection;
import e.rezeda.chat.Models.Message;

public class ChatRooms {

    String status;
    List<ChatRoom> breedsList = new ArrayList<>();
    MutableLiveData<List<ChatRoom>> chatRooms = new MutableLiveData<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addChatRoom(ChatRoom bd) {
        breedsList.add(bd);
    }

    public MutableLiveData<List<ChatRoom>> getChatRooms() {
        return chatRooms;
    }




    //TODO Make load from server

    @Subscribe
    public void fetchList() {
        //if (messageEvent.getType() == MessageEvent.EVENT_CHAT_LISTS) {
//
  //      }
        ChatRoom chatRoom1 = new ChatRoom();
        List<ChatRoom> kokoko = new ArrayList<>();

        SocketConnection.getInstance().sendMessage("{\"type\": \"getMessagesForChatList\", \"username\": \"admin\"}");
        chatRooms.setValue(kokoko);
    }

    public void askForUpdateChatRooms(){
        Log.i("ChatRooms", "askForUpdateChatRooms");
        SocketConnection.getInstance().sendMessage("{\"type\": \"getMessagesForChatList\", \"username\": \"admin\"}");
    }





}
