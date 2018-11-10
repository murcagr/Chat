package e.rezeda.chat;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import e.rezeda.chat.Models.User;


public class UserLoginViewModel extends ViewModel {

    private User user;
    private MutableLiveData<Integer> integ = new MutableLiveData<Integer>();

    private MutableLiveData<User> buttonClick = new MutableLiveData<>();

    public UserLoginViewModel(){
        integ.setValue(0);
    }

//    public UserLoginViewModel(String username, String password){
//        integ.setValue(0);
//
//        User usr = new User(username, password);
//        user.setValue(usr);
//
//    }


    public void init(){
        user = new User();
    }

    public User getUser(){
        return  user;
    }


    public void onButtonClick(View view) {
            integ.setValue(5);
            buttonClick.setValue(user);
            Context context = view.getContext();
            Intent intent = new Intent(context, ChatRoomsActivity.class);
            context.startActivity(intent);

    }


    public MutableLiveData<User> getButtonClick() {
        return buttonClick;
    }

    public MutableLiveData<Integer> getInteg() {
        return integ;
    }

}
