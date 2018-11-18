package e.rezeda.chat;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import e.rezeda.chat.Models.CurrentUser;
import e.rezeda.chat.Models.User;


public class UserAuthViewModel extends ViewModel {

    private User user;

    private MutableLiveData<User> buttonClick = new MutableLiveData<>();

    public void init(){
        EventBus.getDefault().register(this);
        user = new User();
    }

    public User getUser(){
        return  user;
    }
    private View view;


    public void onButtonClick(View view) {
            if(user.isValidA()){
                buttonClick.setValue(user);
                this.view = view;
            }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void checkOnServerAuth(com.example.MessageEvent messageEvent) {
        if (messageEvent.getType().equals("authSuccess")) {
            CurrentUser.getInstance().setUser(user);
            Context context = view.getContext();
            Intent intent = new Intent(context, ChatRoomsActivity.class);
            context.startActivity(intent);
            EventBus.getDefault().unregister(this);
        }
        else {

        }
    }


    public MutableLiveData<User> getButtonClick() {
        return buttonClick;
    }





}
