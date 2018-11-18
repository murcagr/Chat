package e.rezeda.chat;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import e.rezeda.chat.Models.CurrentUser;
import e.rezeda.chat.Models.User;


public class UserLoginViewModel extends ViewModel {

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
            if(user.isValid()){
                buttonClick.setValue(user);
                this.view = view;
            }
    }

    public void onButtonAuthClick(View view) {
            this.view = view;
            Context context = view.getContext();
            Intent intent = new Intent(context, UserAuthActivity.class);
            context.startActivity(intent);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void checkOnServerReg(com.example.MessageEvent messageEvent) {
        if (messageEvent.getType().equals("registerSuccess")) {
            CurrentUser.getInstance().setUser(user);
            Context context = view.getContext();
            Intent intent = new Intent(context, ChatRoomsActivity.class);
            context.startActivity(intent);
            EventBus.getDefault().unregister(this);
        }
        else {

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
