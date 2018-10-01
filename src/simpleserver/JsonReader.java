package simpleserver;

import com.google.gson.*;

import java.io.FileReader;
import java.io.BufferedReader;
import java.net.URL;
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

    //class fields
    private ReadUser[] users;
    private ReadPosts[] posts;
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
       //
       users = gson.fromJson(asJsonObject.get("users"), ReadUser[].class);
       posts = gson.fromJson(asJsonObject.get("posts"), ReadPosts[].class);

       //calling a static method
       ReadUser.loadAll();
       ReadPosts.loadAllPosts();

        }//end loadAllData method

        //get methods

        public String getUser(int userId ){
            return gson.toJson(ReadUser.getUser(userId));
        }

        public String  getPost(int postId){
            return gson.toJson(ReadPosts.getPost(postId));
        }

        public static void main(String[] args) throws Exception {

        //testing that the updates still retrieve the data
        System.out.println(JsonReader.staticJsonReader.getUser(11));
        System.out.println(JsonReader.staticJsonReader.getPost(15));

        /*
        Test cases for the JSON Reader and ProcessFactory
        */

        //constructing a URL object for each of the endpoints
        URL user = new URL("http://localhost:1299/user");
        URL userWithId = new URL("http://localhost:1299/user?userid=7");
        URL postsWithId = new URL("http://localhost:1299/posts?postid=12");
        URL postsWithIdAndMaxLength = new URL("http://localhost:1299/posts?postid=11&maxlength=6");
        URL invalidPathRequest = new URL("http://localhost:1299/index.html");

        //Querying the Processor Factory
        System.out.println("Query for user: " + user.getPath() +  "\n \t"  + new ProcessorFactory().process(user) + "\n");
        System.out.println("Query for user with ID: " + userWithId.getPath() + "\n \t" + "?" + userWithId.getQuery() +
                new ProcessorFactory().process(userWithId)+ "\n");
        System.out.println("Query for posts with ID: "  + postsWithId.getPath() + "?" + postsWithId.getQuery() + "\n \t" +
                new ProcessorFactory().process(postsWithId) + "\n");
        System.out.println("Query for posts with ID and Max length: " + postsWithIdAndMaxLength.getPath() + "?" +
                postsWithIdAndMaxLength.getQuery() + "\n \t" + new ProcessorFactory().process(postsWithIdAndMaxLength) + "\n");
        System.out.print(("Query for invalid endpoint: " + invalidPathRequest.getPath() + "\n \t" +
                new ProcessorFactory().process(invalidPathRequest)) + "\n");

    }//end main

}//end JsonReader Class





