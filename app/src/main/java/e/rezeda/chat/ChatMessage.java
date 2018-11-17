package e.rezeda.chat;

import android.databinding.BaseObservable;

import java.util.Objects;

public class ChatMessage extends BaseObservable {


    private String name;
    private String text;


    ChatMessage(String name, String text){
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

    @Override
    public boolean equals(Object o){
        // self check
        if(this == o){ return true; } else
            // null check
            if(o == null){ return false;} else
                // type check and cast
                if(getClass() != o.getClass()){ return false; } else {
                    final ChatMessage a = (ChatMessage) o;
                    // field comparison
                    return true;
                }
    }

}
