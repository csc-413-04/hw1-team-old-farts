package simpleserver;

import com.google.gson.*;

import java.io.FileReader;
import java.io.BufferedReader;


/*
class database
User[] users
HashMap<String,User>
boolean isloaded = false;
public statis Database database - new database

private database(){
}

public statis DataBase getDatabase(){
if database isloaded database connect
return database;
}

public Users getAllUsers
 */

public class Database {

    //class fields
    public ReadUser[] users;
    public ReadPosts[] posts;
    //the class instantiates a Gson object for it's use in the constructor
    private Gson gson;
    static Database staticDatabase;

    static {
        try {
            staticDatabase = new Database();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Constructor
   Database() throws Exception {
       //instantiating a Gson object, the library that processes the JSON into Java objects of a prescribed class
       gson = new Gson();
       //calling the method that reads the JSON data file
       this.loadAllData();
   }//end Database Constructor

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
        public static Object getUser(int userId ){
            return ReadUser.getUser(userId);
        }

        public static Object getPost(int postId){
            return ReadPosts.getPost(postId);
        }

}//end Database Class





