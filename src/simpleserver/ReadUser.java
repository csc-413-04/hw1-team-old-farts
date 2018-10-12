package simpleserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ReadUser implements iData {
    // Use Java generics to avoid linear searching for users by id, makes it O(1) instead of O(n)
    private  static Map<Integer, ReadUser> useridDict = new HashMap<>();
    private static ArrayList<ReadUser> allUsers = new ArrayList<>();

    private  int userid;

    //this method seems to have been missed when it was copied form the JSONReader class
    public ReadUser (){
        allUsers.add(this);
    }

    public static ReadUser getUser(int userid){
        return useridDict.get(userid);
    }

    public void register(){
        useridDict.put(userid, this);
    }

    public static void loadAll(){
        for(int i = 0 ; i < allUsers.size(); i++){
            allUsers.get(i).register();
        }
    }
}
