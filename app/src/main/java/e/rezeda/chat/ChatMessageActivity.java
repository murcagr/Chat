package e.rezeda.chat;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import e.rezeda.chat.ViewModels.ChatViewModel;

public class ChatMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChatViewModel chatVM = ViewModelProviders.of(this).get(ChatViewModel.class);


    }

}
