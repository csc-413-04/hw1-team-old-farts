package simpleserver;

// Initiate a Lazy Initialization Singleton class

public static class Users {
    private int userid;
    private String username;

    private Users(int userid, String username){
        this.userid = userid;
        this.username = username;
    }
}

public static class Posts {

    private int userid;
    private int postid;
    private String data;

    private Posts(int userid, int postid, String data) {
        this.userid = userid;
        this.postid = postid;
        this.data = data;
    }
}
public class Singleton {

    // Singleton instance
    private static Singleton instance;

    // Singleton Constructor
    private Singleton(){}

    //
    public static Singleton getInstance(){

        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }



}
