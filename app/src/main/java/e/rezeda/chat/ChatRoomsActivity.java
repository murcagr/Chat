package e.rezeda.chat;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.net.Socket;
import java.util.List;

import e.rezeda.chat.databinding.ActivityChatRoomsBinding;

public class ChatRoomsActivity extends AppCompatActivity {


    private ChatRoomsViewModel viewModelChat;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_rooms);
        setBinding(savedInstanceState);
    }


    private void setBinding(Bundle savedInstanceState){

        ActivityChatRoomsBinding bindingChat = DataBindingUtil.setContentView(this, R.layout.activity_chat_rooms);
        viewModelChat = ViewModelProviders.of(this).get(ChatRoomsViewModel.class);
        //RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecycleView);
        //recyclerView.setAdapter(viewModelChat.getAdapter());
        if (savedInstanceState == null) {
            viewModelChat.init();
        }
        bindingChat.setChatRoomsViewModel(viewModelChat);
        bindingChat.setLifecycleOwner(this);
        setupListUpdate();

    }


    private void setupListUpdate() {
        viewModelChat.loading.set(View.VISIBLE);
        viewModelChat.askForUpdateChatRooms();

        viewModelChat.getChatRooms().observe(this, new Observer<List<ChatRoom>>() {
            @Override
            public void onChanged(List<ChatRoom> chatRooms) {
                viewModelChat.loading.set(View.GONE);
                if (chatRooms.size() == 0) {
                    viewModelChat.showEmpty.set(View.VISIBLE);
                } else {
                    viewModelChat.showEmpty.set(View.GONE);
                    viewModelChat.setChatRoomsInAdapter(chatRooms);
                }
            }
        });
        setupListClick();
    }

    private void setupListClick() {
        viewModelChat.getSelected().observe(this, new Observer<ChatRoom>() {
            @Override
            public void onChanged(ChatRoom chatRoom) {
                if (chatRoom != null) {
                    Toast.makeText(ChatRoomsActivity.this, "You selected a ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
