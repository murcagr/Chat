package e.rezeda.chat;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import e.rezeda.chat.databinding.ActivityChatMessagesBinding;
import e.rezeda.chat.databinding.ActivityChatRoomsBinding;

public class ChatMessageActivity extends AppCompatActivity {

    private ChatMessageViewModel viewModelChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_messages);
        setBinding(savedInstanceState);


    }

    private void setBinding(Bundle savedInstanceState){

        ActivityChatMessagesBinding bindingChat = DataBindingUtil.setContentView(this, R.layout.activity_chat_messages);
        viewModelChat = ViewModelProviders.of(this).get(ChatMessageViewModel.class);
        if (savedInstanceState == null) {
            viewModelChat.init();
        }
        bindingChat.setChatMessageViewModel(viewModelChat);
        bindingChat.setLifecycleOwner(this);
        setupListUpdate();
        setupListClick();
        setupSendClick();
    }

    private void setupListUpdate() {
        viewModelChat.loading.set(View.VISIBLE);
        viewModelChat.askForUpdateChatMessages();

        viewModelChat.getChatMessages().observe(this, new Observer<List<ChatMessage>>() {
            @Override
            public void onChanged(List<ChatMessage> chatMessages) {
                viewModelChat.loading.set(View.GONE);
                if (chatMessages.size() == 0) {
                    viewModelChat.showEmpty.set(View.VISIBLE);
                } else {
                    viewModelChat.showEmpty.set(View.GONE);
                    viewModelChat.setChatMessageInAdapter(chatMessages);
                }
            }
        });
        //setupListClick();
    }
    private void setupListClick() {
        viewModelChat.getSelected().observe(this, new Observer<ChatMessage>() {
            @Override
            public void onChanged(ChatMessage chatMessage) {
                if (chatMessage != null) {
                    Toast.makeText(ChatMessageActivity.this, "You selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupSendClick() {
        viewModelChat.getChatMessages().observe(this, new Observer<List<ChatMessage>>() {
            @Override
            public void onChanged(List<ChatMessage> chatMessages) {
                viewModelChat.loading.set(View.GONE);
                if (chatMessages.size() == 0) {
                    viewModelChat.showEmpty.set(View.VISIBLE);
                } else {
                    viewModelChat.showEmpty.set(View.GONE);
                    viewModelChat.setChatMessageInAdapter(chatMessages);
                }
            }
        });
    }



}
