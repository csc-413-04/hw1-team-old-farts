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


//// Java generic for posts key
// class Post {
//
//    private int userid;
//    private int postid;
//    private String data;
//
//    //Contructor for users
//    public Posts(int userid, int postid, String data) {
//        this.userid = userid;
//        this.postid = postid;
//        this.data = data;
//    }
//    public int getUserid(){
//        return userid;
//    }
//}

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
}

class userlist {
     List<Usertemplate> userlist;


}

//class postlist {
//     List<Post> postlist;
//}



public class JsonReader {

    public static void main(String[] args) throws Exception {


        Gson gson = new Gson();

        BufferedReader br = new BufferedReader(
                new FileReader("./src/data/data.json"));

        final Type typeOf = new TypeToken<Map<String, List<Usertemplate>>>() {
        }.getType();
        final Map<String, List<Usertemplate>> map = new Gson().fromJson(br, typeOf);
// get value
        final List<Usertemplate> list = map.get("users");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getUserid());

        }

    }
}


