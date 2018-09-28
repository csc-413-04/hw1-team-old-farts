package simpleserver;

import com.google.gson.*;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

// Generic class for users object
class ReadUser {
    // Use Java generics to avoid linear searching for users by id, makes it O(1) instead of O(n)
    private  static Map<Integer, ReadUser> useridDict = new HashMap<>();
    private static ArrayList<ReadUser> allUsers = new ArrayList<ReadUser>();

    public void setUsername(String username) {
        this.username = username;
        System.out.println(username);
    }

    private  String username;

    public void setUserid(int userid) {
        this.userid = userid;
        System.out.println(userid);
    }

    private  int userid;

    public ReadUser(){
        allUsers.add(this);
    }

    public ReadUser(String username, int userid){
        this.username = username;
        this.userid = userid;
        allUsers.add(this);
        useridDict.put(userid, this);
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

// Generic class for posts object
class ReadPosts {

    private  static Map<Integer, ReadPosts> postDict = new HashMap<>();
    private static ArrayList<ReadPosts> allPosts = new ArrayList<>();

    public void setPostid(int postid){
        this.postid = postid;
        System.out.println(postid);
    }

    private int postid;

    public void setUseridP(int userid){
        this.userid = userid;
        System.out.print(userid);
    }

    private int userid;

    public void setData(String data){
        this.data = data;
        System.out.println(data);
    }

    private String data;

    public ReadPosts (){
        allPosts.add(this);
    }

    public static ReadPosts getPost(int postid){
        return postDict.get(postid);
    }

    public void registerp(){
        postDict.put(postid, this);
    }

    public static void loadAllPosts(){
        for(int i = 0 ; i <allPosts.size(); i++)
            allPosts.get(i).registerp();
    }
}

public class JsonReader {

    public static void main(String[] args) throws Exception {


        Gson gson = new Gson();

        BufferedReader br = new BufferedReader(
                new FileReader("./src/data/data.json"));

        JsonParser jsonParser = new JsonParser();
        JsonObject obj = jsonParser.parse(br).getAsJsonObject();

        ReadUser[] users = gson.fromJson(obj.get("users"), ReadUser[].class);
        ReadPosts[] posts = gson.fromJson(obj.get("posts"), ReadPosts[].class);

        ReadUser.loadAll();
        ReadPosts.loadAllPosts();

        //Add input here to the get methods to print out the data
        String jsonString = gson.toJson(ReadUser.getUser(1));
        String jsonString2 = gson.toJson(ReadPosts.getPost(1));

        System.out.println(jsonString);
        System.out.println(jsonString2);

    }

}





