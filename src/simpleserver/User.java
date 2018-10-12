package simpleserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User implements iData {
    private static Map<Integer, User> userValues = new HashMap<>();
    private static ArrayList<User> allUsers = new ArrayList<>();

    private int userid;

    public static User getUser(int userid) {
        return userValues.get(userid);
    }

    public void register() {
        userValues.put(userid, this);
    }

    public static void loadAllUsers() {
        for (User allUser : allUsers) {
            allUser.register();
        }
    }



}
