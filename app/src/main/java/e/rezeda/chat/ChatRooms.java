package e.rezeda.chat;

import android.arch.lifecycle.MutableLiveData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
       // if (messageEvent.getType() == MessageEvent.EVENT_CHAT_LISTS) {
//
       // }
        //sock.sendMessage();
        ChatRoom chatRoom1 = new ChatRoom();
        List<ChatRoom> kokoko = new ArrayList<>();


        kokoko.add(chatRoom1);
        kokoko.add(chatRoom1);
        kokoko.add(chatRoom1);
        kokoko.add(chatRoom1);
        kokoko.add(chatRoom1);
        kokoko.add(chatRoom1);
        chatRooms.setValue(kokoko);
//
//
//
//          client.newCall(request).enqueue(new Callback() {
////            @Override
////            public void onFailure(Call call, IOException e) {
////                e.printStackTrace();
////            }
////
////            @Override
////            public void onResponse(Call call, final Response response) throws IOException {
////                if (!response.isSuccessful()) {
////                    throw new IOException("Unexpected code " + response);
////                }
////            }


    }





}
