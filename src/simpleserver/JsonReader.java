package simpleserver;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.reflect.TypeToken;


// Java generic for user key
class Usertemplate {
    private int userid;
    private String username;

    //Constructor for Users
    public Usertemplate(int userid, String username){
        this.userid = userid;
        this.username = username;
    }
    public int getUserid(){
        return userid;
    }
    public String username(){
        return username;
    }
}

// Java generic for posts key
class Posttemplate {

    private int userid;
    private int postid;
    private String data;

    //Contructor for users
    public Posttemplate(int userid, int postid, String data) {
        this.userid = userid;
        this.postid = postid;
        this.data = data;
    }
    public int getUserid(){
        return userid;
    }
    public int getPostid(){
        return postid;
    }
    public String getData(){
        return data;
    }
}

public class JsonReader {

    public static void main(String[] args) throws Exception {


        Gson gson = new Gson();

        BufferedReader br = new BufferedReader(
                new FileReader("./src/data/data.json"));
        BufferedReader br2 = new BufferedReader(
                new FileReader("./src/data/data.json"));

        final Type usertypeOf = new TypeToken<Map<String, List<Usertemplate>>>() {
        }.getType();
        final Type posttypeOf = new TypeToken<Map<String, List<Posttemplate>>>() {
        }.getType();

        // Creating maps for posts and users
        final Map<String, List<Usertemplate>> usermap = new Gson().fromJson(br, usertypeOf);
        final Map<String, List<Posttemplate>> postmap = new Gson().fromJson(br2, posttypeOf);

        // Building Java object list from maps
        final List<Usertemplate> userlist = usermap.get("users");

        //Null pointerException, can't see it
        final List<Posttemplate> postlist = postmap.get("posts");

//        // Test object by printing userids
//        for (int i = 0; i < userlist.size(); i++) {
//            System.out.println(userlist.get(i).getUserid());
//        }
//
//        for (int i = 0; i < postlist.size(); i++) {
//            System.out.println(postlist.get(i).getData());
        }

    }

}



