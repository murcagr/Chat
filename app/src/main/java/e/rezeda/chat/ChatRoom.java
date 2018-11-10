package e.rezeda.chat;

import android.media.Image;

import java.util.ArrayList;

import e.rezeda.chat.ChatRooms;

public class ChatRoom {

    private String name;
    private String text;
    private Image image;

    public ChatRoom(){
        this.name = "lalhdalal";
        this.text = "asdasd";
        this.image = null;
    }

    public ChatRoom(String name, String text, Image image){
        this.name = "lalhdalal";
        this.text = "asdasd";
        this.image = null;
    }
    public ChatRoom(String name, String text){
        this.name = "lalhdalal";
        this.text = "asdasd";
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

    public void setMessage(String text) {
        this.text = text;
    }

    public String getMessage() {
        return text;
    }


}
