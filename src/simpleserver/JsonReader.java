package simpleserver;

import com.google.gson.*;

import java.io.FileReader;
import java.io.BufferedReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

// Generic class for users object
class ReadUser implements iData {
    // Use Java generics to avoid linear searching for users by id, makes it O(1) instead of O(n)
    private  static Map<Integer, ReadUser> useridDict = new HashMap<>();
    private static ArrayList<ReadUser> allUsers = new ArrayList<ReadUser>();

    private  int userid;

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
class ReadPosts implements iData{

    private  static Map<Integer, ReadPosts> postDict = new HashMap<>();
    private static ArrayList<ReadPosts> allPosts = new ArrayList<>();

    private int postid;

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

    //class fields
    public ReadUser[] users;
    public ReadPosts[] posts;
    //the class instantiates a Gson object for it's use in the constructor
    private Gson gson;
    static JsonReader staticJsonReader;

    static {
        try {
            staticJsonReader = new JsonReader();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Constructor
   JsonReader() throws Exception {
       //instantiating a Gson object, the library that processes the JSON into Java objects of a prescribed class
       gson = new Gson();
       //calling the method that reads the JSON data file
       this.loadAllData();
   }//end JsonReader Constructor

    // static method
    private void loadAllData() throws Exception{

       Gson gson = new Gson();

       //instantiating an object that allows us to read files
       //the file path is hard coded for now, but it can be parameterized in the constructor
       BufferedReader br = new BufferedReader(new FileReader("./src/data/data.json"));

       //instantiating a JsonParser object from the GSON library that will translate the Json into a Java Object
       JsonParser jsonParser = new JsonParser();
       JsonObject asJsonObject = jsonParser.parse(br).getAsJsonObject();

       //Transferring the JsonParser GSON object, into collections of Java objects of the two classes that we have constructed
       users = gson.fromJson(asJsonObject.get("users"), ReadUser[].class);
       posts = gson.fromJson(asJsonObject.get("posts"), ReadPosts[].class);

       //calling a static method
       ReadUser.loadAll();
       ReadPosts.loadAllPosts();

    }//end loadAllData method

        //get methods
        public Object getUser(int userId ){
            return ReadUser.getUser(userId);
        }

        public Object getPost(int postId){
            return ReadPosts.getPost(postId);
        }



}//end JsonReader Class





