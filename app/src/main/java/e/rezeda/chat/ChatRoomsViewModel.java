package e.rezeda.chat;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableInt;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import e.rezeda.chat.Adapters.ChatRoomsAdapter;

public class ChatRoomsViewModel extends ViewModel {
    private ChatRooms chatRooms;
    private ChatRoomsAdapter adapter;
    public MutableLiveData<ChatRoom> selected;
    public ObservableArrayMap<String, String> images;
    public ObservableInt loading;
    public ObservableInt showEmpty;

    public void init(){

        chatRooms = new ChatRooms();
        selected = new MutableLiveData<>();
        adapter = new ChatRoomsAdapter(R.layout.chat_room_short, this);
        images = new ObservableArrayMap<>();
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);

    }


    public void fetchList() {
        chatRooms.fetchList();
    }

    public MutableLiveData<List<ChatRoom>> getChatRooms() {
        return chatRooms.getChatRooms();
    }

    public ChatRoomsAdapter getAdapter() {
        return adapter;
    }

    public void setChatRoomsInAdapter(List<ChatRoom> chatRooms) {
        this.adapter.setChatRooms(chatRooms);
        this.adapter.notifyDataSetChanged();
    }

    public MutableLiveData<ChatRoom> getSelected() {
        return selected;
    }


    public void onItemClick(Integer index) {
        ChatRoom chatRoom = getChatRoomAt(index);
        selected.setValue(chatRoom);
    }

    public ChatRoom getChatRoomAt(Integer index) {
        if (chatRooms.getChatRooms().getValue() != null &&
                index != null &&
                chatRooms.getChatRooms().getValue().size() > index) {
            return chatRooms.getChatRooms().getValue().get(index);
        }
        return null;
    }
}
