package e.rezeda.chat;


import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import e.rezeda.chat.Models.CurrentUser;
import e.rezeda.chat.databinding.ActivityChatRoomsBinding;

public class ChatRoomsActivity extends AppCompatActivity {

    private static final String TAG = "ChatRoomsActivity";
    private ChatRoomsViewModel viewModelChat;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_rooms);
        setBinding(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModelChat.askForUpdateChatRooms();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModelChat.askForUpdateChatRooms();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_chat_rooms, menu);
        return true;
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_chat:
                showDialog(1);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ChatRoomsActivity.this);
        // Get the layout inflater
        LayoutInflater inflater = ChatRoomsActivity.this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_new_chat, null))
                // Add action buttons
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String message = String.format("{\"type\": \"send\", \"from_who\": \"%s\", \"to_who\": \"jvjvuvvu\", \"message\": \"secretmsg\"}",
                                CurrentUser.getInstance().getUser().getUsername());
                        SocketConnection.getInstance().sendMessage(message);
                        viewModelChat.askForUpdateChatRooms();
                    }
                });
        return builder.create();
    }

    public void setBinding(Bundle savedInstanceState){

        ActivityChatRoomsBinding bindingChat = DataBindingUtil.setContentView(this, R.layout.activity_chat_rooms);
        viewModelChat = ViewModelProviders.of(this).get(ChatRoomsViewModel.class);

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
