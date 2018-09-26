package simpleserver;

import com.google.gson.Gson

// Initiate a Lazy Initialization Singleton class



private class Posts {

    private int userid;
    private int postid;
    private String data;

    public Posts(int userid, int postid, String data) {
        this.userid = userid;
        this.postid = postid;
        this.data = data;
    }
}

private class Users {
    private int userid;
    private String username;

    public Users(int userid, String username){
        this.userid = userid;
        this.username = username;
    }
}


    Gson gson = new Gson();

 //   JsonArray body = gson.fromJson(ResultString, JsonArray.class);
 //   System.out.println(body.get(0).getAsJsonObject().get("elementnamehere").getAsString())

public class Singleton {

    // Singleton instance
    private static Singleton instance;
    // create object array variables for user and post objects

    // Singleton Constructor
    private Singleton(){}


    public static Singleton getInstance(){

        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }

    private readJson(){
        // TODO
    }

}
