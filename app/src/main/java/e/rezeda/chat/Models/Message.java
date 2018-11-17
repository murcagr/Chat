package e.rezeda.chat.Models;

public class Message {
    private Integer id;
    private String from;
    private String to;
    private String message;

    private boolean isSelf;

    public Message(){

    }

    public Message(String from, String to, String message){
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public String getFromName() {
        return from;
    }

    public void setFromName(String fromName) {
        this.from = fromName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSelf() {
        return isSelf;
    }

    public void setSelf(boolean isSelf) {
        this.isSelf = isSelf;
    }


}
