package e.rezeda.chat;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableInt;
import android.view.View;

import java.util.List;

import e.rezeda.chat.Adapters.ChatMessagesAdapter;
import e.rezeda.chat.Adapters.ChatRoomsAdapter;
import e.rezeda.chat.Models.CurrentUser;
import e.rezeda.chat.Models.Message;
import e.rezeda.chat.Models.User;

public class ChatMessageViewModel extends ViewModel {
    private ChatMessages chatMessages;
    public ChatMessage message;
    private ChatMessagesAdapter adapter;
    public MutableLiveData<ChatMessage> selected;
    private MutableLiveData<String> buttonClick = new MutableLiveData<>();
    public ObservableArrayMap<String, String> images;
    public ObservableInt loading;
    public ObservableInt showEmpty;

    public void init(){

        chatMessages = new ChatMessages();
        message = new ChatMessage();
        selected = new MutableLiveData<>();
        adapter = new ChatMessagesAdapter(R.layout.chat_message_short, this);
        images = new ObservableArrayMap<>();
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
    }

    public void init(String username){

        chatMessages = new ChatMessages(username);
        message = new ChatMessage();
        selected = new MutableLiveData<>();
        adapter = new ChatMessagesAdapter(R.layout.chat_message_short, this);
        images = new ObservableArrayMap<>();
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
    }

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }

    public MutableLiveData<String> getButtonClick() {
        return buttonClick;
    }

    public void askForUpdateChatMessages() {
        chatMessages.askForUpdateChatMessages();
    }

    public MutableLiveData<List<ChatMessage>> getChatMessages() {
        return chatMessages.getChatMessages();
    }

    public ChatMessagesAdapter getAdapter() {
        return adapter;
    }

    public void setChatMessageInAdapter(List<ChatMessage> chatMessages) {
        this.adapter.setChatMessages(chatMessages);
        this.adapter.notifyDataSetChanged();
    }

    public MutableLiveData<ChatMessage> getSelected() {
        return selected;
    }


    public void onItemClick(Integer index, View view) {
        ChatMessage chatMessage = getChatMessageAt(index);
        selected.setValue(chatMessage);
    }

    public void onSendClick() {
        String message = String.format("{\"type\": \"send\", \"to_who\": \"%s\", \"from_who\": \"%s\", \"message\" : \"%s\"}",
                chatMessages.to_who, CurrentUser.getInstance().getUser().getUsername(), this.message.getText());
        SocketConnection.getInstance().sendMessage(message);
        chatMessages.addChatMessage(new ChatMessage(CurrentUser.getInstance().getUser().getUsername(),this.message.getText()));
    }




    public ChatMessage getChatMessageAt(Integer index) {
        if (chatMessages.getChatMessages().getValue() != null &&
                index != null &&
                chatMessages.getChatMessages().getValue().size() > index) {
            return chatMessages.getChatMessages().getValue().get(index);
        }
        return null;
    }
}
