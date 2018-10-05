package simpleserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;


//class Data {
//    private String status;
//    private int entries;
//    private List<dataObject> data;
//
//
//    // Also if we want build a nested JSON Object we replace one of the parameters and
//    // call Response res inside the Data constructor
//    // Just making usage of Serialization
//
//    public Data(String status, int entries,List<dataObject> data) {
//        this.status = status;
//        this.entries = entries;
//        this.data = data;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public int getEntries() {
//        return entries;
//    }
//
//    public void setEntries(int entries) {
//        this.entries = entries;
//    }
//
//    // component is used for holding a list of Data for the data array Object
//    public static class dataObject {
//        int postid;
//        int userid;
//        String data;
//
//        public dataObject(int postid, int userid, String data) {
//            this.postid = postid;
//            this.userid = userid;
//            this.data = data;
//        }
//
//        public int getPostid() {
//            return postid;
//        }
//
//        public void setPostid(int postid) {
//            this.postid = postid;
//        }
//
//        public int getUserid() {
//            return userid;
//        }
//
//        public void setUserid(int userid) {
//            this.userid = userid;
//        }
//
//        public String getData() {
//            return data;
//        }
//
//        public void setData(String data) {
//            this.data = data;
//        }
//    }
//}

// This was just for testing purposes for building the JSON
public class Response {
    private String status;
    private int entries;
    private iData[] data;



    public Response(String status, iData[] _data) {
        this.status = status;
        this.data = _data;
        this.entries = _data.length;
    }
}

class Test {

    public static void main(String[] args) throws Exception {

        JsonReader reader = new JsonReader();
        Response response = new Response("OK",reader.posts);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(response);
        System.out.println(json);

//        List<Data.dataObject> jsonObject = new ArrayList<>();
//        Response response = new Response(); // beginner test case
//        jsonObject.add(new Data.dataObject(0, 1, "I lost my lightSaber, anyone seen it?"));
//        jsonObject.add(new Data.dataObject(1, 0, "Found lightSaber, looking for owner"));
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        Data datah = new Data("Ok", 2 , jsonObject);
//
//        String json = gson.toJson(datah);
//
//        System.out.println(json);

    }
}

