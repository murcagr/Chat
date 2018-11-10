package e.rezeda.chat;

import android.media.Image;

import java.util.ArrayList;

import e.rezeda.chat.ChatRooms;

public class ChatRoom {

    private String name;
    private String message;
    private Image image;

    public ChatRoom(){
        this.name = "lalhdalal";
        this.message = "asdasd";
        this.image = null;
    }



    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
