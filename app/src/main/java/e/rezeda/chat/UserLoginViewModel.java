package e.rezeda.chat;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import e.rezeda.chat.Models.User;


public class UserLoginViewModel extends ViewModel {

    private User user;

    private MutableLiveData<User> buttonClick = new MutableLiveData<>();

    public void init(){
        user = new User();
    }

    public User getUser(){
        return  user;
    }


    public void onButtonClick(View view) {
            if(user.isValid()){
                buttonClick.setValue(user);
                Context context = view.getContext();
                Intent intent = new Intent(context, ChatRoomsActivity.class);
                context.startActivity(intent);
            }
    }


    public MutableLiveData<User> getButtonClick() {
        return buttonClick;
    }

}
