package e.rezeda.chat.Models;

public class CurrentUser {


    User user;

    private static CurrentUser instance;


    public static synchronized CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
