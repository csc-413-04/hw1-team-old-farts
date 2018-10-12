package simpleserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Post implements iData {
    private static Map<Integer, Post> postValues = new HashMap<>();
    private static ArrayList<Post> allPosts = new ArrayList<>();

    private int postid;
    private String data;

    static Post getPost(int postid) {
        return postValues.get(postid);
    }

    public void registerPosts() {
        postValues.put(postid, this);
    }

    public static void loadAllPosts() {
        for (Post allPost : allPosts) {
            allPost.registerPosts();
        }
    }

}
