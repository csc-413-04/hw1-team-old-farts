package simpleserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// This was just for testing purposes for building the JSON
public class Response {
    public String status;
    public int entries;
    iData[] data;

//    void setStatus(String status) {
//        this.status = status;
//    }

    public Response(String status, iData[] _data) {
        this.status = status;
        this.data = _data;
        this.entries = _data.length;
    }

    void setStatus(String status) {
        this.status = status;
    }

    void setData(iData[] data) {
        this.entries = data.length;
        this.data = data;
    }

//    static class Test {
//
//        public static void main(String[] args) throws Exception {
//
//            JsonReader reader = new JsonReader();
//            Response response = new Response("OK",reader.posts);
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            String json = gson.toJson(response);
//            System.out.println(json);
//
//        }
//    }

}


