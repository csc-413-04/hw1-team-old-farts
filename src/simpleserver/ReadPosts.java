package simpleserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
