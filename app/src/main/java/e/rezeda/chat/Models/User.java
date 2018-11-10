package e.rezeda.chat.Models;

import android.databinding.BaseObservable;
import android.media.Image;

public class User extends BaseObservable {

    String username;
    Image avatarImg;
    String ip;
    String password;


//    public User(String username, String ip){
//        this.username = username;
//        this.ip = ip;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        //NOTIFY
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Image getAvatarImg() {
        return avatarImg;
    }

    public void setAvatarImg(Image avatarImg) {
        this.avatarImg = avatarImg;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        //notifyPropertyChanged();
    }



}
