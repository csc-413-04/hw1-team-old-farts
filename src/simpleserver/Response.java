package simpleserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

class Data {
    private String status;
    private int entries;
    private List<dataObject> data;


    // Also if we want build a nested JSON Object we replace one of the parameters and
    // call Response res inside the Data constructor
    // Just making usage of Serialization

    public Data(String status, int entries,List<dataObject> data) {
        this.status = status;
        this.entries = entries;
        this.data = data;
    }

    // component is used for holding a list of Data for the data array Object
    public static class dataObject {
        int postid;
        int userid;
        String data;

        public dataObject(int postid, int userid, String data) {
            this.postid = postid;
            this.userid = userid;
            this.data = data;
        }

        public int getPostid() {
            return postid;
        }

        public void setPostid(int postid) {
            this.postid = postid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}



// This was just for testing purposes for building the JSON
public class Response {
    private String status;
    private int entries;


    public Response() {
        this("OK", 2);
    }

    public Response(String status, int entries) {
        this.status = status;
        this.entries = entries;
    }
}

class Test {

    public static void main(String[] args) {

        List<Data.dataObject> jsonObject = new ArrayList<>();
        Response response = new Response();
        jsonObject.add(new Data.dataObject(0, 1, "I lost my lightSaber, anyone seen it?"));
        jsonObject.add(new Data.dataObject(1, 0, "Found lightSaber, looking for owner"));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Data datah = new Data("Ok", 2 , jsonObject);

        String json = gson.toJson(datah);

        System.out.println(json);

    }
}

