package e.rezeda.chat;

import android.arch.lifecycle.MutableLiveData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import e.rezeda.chat.SocketConnection;
import e.rezeda.chat.Models.Message;

public class ChatRooms {

    String status;
    List<ChatRoom> chatRoomList = new ArrayList<>();
    MutableLiveData<List<ChatRoom>> chatRooms = new MutableLiveData<>();

    ChatRooms(){
        EventBus.getDefault().register(this);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addChatRoom(ChatRoom bd) {
        chatRoomList.add(bd);
    }

    public MutableLiveData<List<ChatRoom>> getChatRooms() {
        return chatRooms;
    }




    //TODO Make load from server

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void fetchList(com.example.MessageEvent messageEvent) {

        List<ChatRoom> kokoko = new ArrayList<>();
        if (messageEvent.getType().equals("getMessagesForChatListResult")) {
            List<ChatListMessages> data = messageEvent.getData();
            for(int i = 0; i < messageEvent.getData().size(); i++){
                ChatRoom chatRoom1 = new ChatRoom(data.get(i).getFromWho(),data.get(i).getText());
                kokoko.add(chatRoom1);
            }
        }

        //SocketConnection.getInstance().sendMessage("{\"type\": \"getMessagesForChatList\", \"username\": \"admin\"}");
        chatRooms.setValue(kokoko);
    }

    public void askForUpdateChatRooms(){
        SocketConnection.getInstance().sendMessage("{\"type\": \"getMessagesForChatList\", \"username\": \"admin\"}");
    }

}
