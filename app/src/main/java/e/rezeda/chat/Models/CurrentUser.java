package e.rezeda.chat.Models;

public class CurrentUser {

    private static CurrentUser instance;

    String password;

    public static synchronized CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }





}
