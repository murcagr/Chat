package e.rezeda.chat.Models;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.media.Image;

import e.rezeda.chat.SocketConnection;

public class User extends BaseObservable {

    private String username;
    private Image avatarImg;
    private String ip;
    private String password;
    private Short isValidF = -1;

    public ObservableField<Integer> emailError = new ObservableField<>();
    public ObservableField<Integer> passwordError = new ObservableField<>();

    public User(){
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(String username){
        this.username = username;
    }


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



    public boolean isValid() {
        boolean valid = isUsernamePasswordValid();
       if(valid){
            startRegisterUser();
        }
        return valid;
    }


    public boolean isValidA() {
        boolean valid = isUsernamePasswordValid();
        if(valid){
            startAuthUser();
        }
        return valid;
    }

    public boolean isUsernamePasswordValid() {
        boolean valid = isUsernameValid();
        valid = isPasswordValid() && valid;
        return valid;
    }


    public void startRegisterUser() {
        String output = String.format("{\"type\": \"register\", \"username\" : \"%s\", \"passsword\" : \"%s\"}", username, password);
        SocketConnection.getInstance().sendMessage(output);
    }

    public void startAuthUser() {
        String output = String.format("{\"type\": \"auth\", \"username\" : \"%s\", \"passsword\" : \"%s\"}", username, password);
        SocketConnection.getInstance().sendMessage(output);
    }



    public boolean isUsernameValid() {
        if (username != null && username.length() >= 5) {
            return true;
        }
        return false;
    }

    public boolean isPasswordValid() {
        if (username != null && username.length() >= 5) {
            return true;
        }
        return false;

    }

}
