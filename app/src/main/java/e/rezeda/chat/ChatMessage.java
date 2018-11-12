package e.rezeda.chat;

import android.databinding.BaseObservable;

public class ChatMessage extends BaseObservable {

    private String text;
    private String name;


    ChatMessage(String text, String name){
        this.name = name;
        this.text = text;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setName(String owner) {
        this.name = owner;
    }

    public String getName() {
        return name;
    }

}
