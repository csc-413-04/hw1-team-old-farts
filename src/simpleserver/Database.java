package simpleserver;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Database {
    public User[] users;
    public Post[] posts;
    boolean isLoaded = false;
    static Database database = new Database();

    public Database() {

    }

    static User[] getUsers(int userId) {
        return new User[] { User.getUser(userId)};
    }

    static Post[] getPosts(int postId) {
        return new Post[] { Post.getPost(postId)};
    }

    static Database getDatabase() {
        if (!database.isLoaded) database.connect();
        return database;
    }

    private void connect() {
        isLoaded = true;
        Gson gson = new Gson();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("./src/data/data.json"));
            JsonParser jsonParser = new JsonParser();
            JsonObject asJsonObject = jsonParser.parse(br).getAsJsonObject();

            // Parse through the JSON OBJECT
            users = gson.fromJson(asJsonObject.get("users"), User[].class);
            posts = gson.fromJson(asJsonObject.get("posts"), Post[].class);

            User.loadAllUsers();
            Post.loadAllPosts();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
