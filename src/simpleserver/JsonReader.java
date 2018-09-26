package simpleserver;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


// Java generics for posts key
 class Posts {

    private int userid;
    private int postid;
    private String data;

    //Contructor for users
    public Posts(int userid, int postid, String data) {
        this.userid = userid;
        this.postid = postid;
        this.data = data;
    }
}

// Java generics for user key
 class Users {
    private int userid;
    private String username;

    //Constructor for Users
    public Users(int userid, String username){
        this.userid = userid;
        this.username = username;
    }
}


public class JsonReader{

    public static void main(String[] args) {
        try {
            Gson gson = new Gson();

            BufferedReader br = new BufferedReader(
                    new FileReader("./src/data/data.json"));

            Users userdata = gson.fromJson(br, Users.class);
            Posts postdata = gson.fromJson(br, Posts.class);
        }

        catch(IOException e)
            {
                e.printStackTrace();
            }
    }

}


