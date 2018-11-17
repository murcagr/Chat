package e.rezeda.chat;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BaseObservable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ChatMessages extends BaseObservable {

    String status;
    String to_who;
    List<ChatMessage> chatMessageList = new ArrayList<>();
    MutableLiveData<List<ChatMessage>> chatMessages = new MutableLiveData<>();



    ChatMessages(){
        EventBus.getDefault().register(this);
    }

    ChatMessages(String to_who){
        this.to_who = to_who;
        //EventBus.getDefault().register(this);
    }

    public String getTo_who() {
        return to_who;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addChatMessage(ChatMessage bd) {
        chatMessageList.add(bd);
        chatMessages.setValue(chatMessageList);
    }



    public MutableLiveData<List<ChatMessage>> getChatMessages() {
        return chatMessages;
    }

    public List<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }
    //TODO Make load from server

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void fetchList(com.example.MessageEvent messageEvent) {

        if (messageEvent.getType().equals("getMessagesForChatResult")) {
            List<JSONChatListMessages> data = messageEvent.getData();
            for(int i = 0; i < messageEvent.getData().size(); i++){
                chatMessageList.add(new ChatMessage(data.get(i).getFromWho(),data.get(i).getText()));
            }
        }

        //SocketConnection.getInstance().sendMessage("{\"type\": \"getMessagesForChatList\", \"username\": \"admin\"}");
        chatMessages.setValue(chatMessageList);
    }

    public void askForUpdateChatMessages(){
        SocketConnection.getInstance().sendMessage("{\"type\": \"getMessagesForChat\", \"to_who\": \"admin\"}");
    }

}
