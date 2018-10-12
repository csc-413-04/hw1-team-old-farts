/**************************************************************************************************
 *Class that processes queries for posts
 * This class supports queries for all posts or a post with the provided ID
 *
 **************************************************************************************************/

package simpleserver;

import com.google.gson.Gson;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

//removing access modifier to restrict access to class and package
class ProcessorForPost implements ServerProcessor {

    protected static String process(URL url) {

        String response;

        Gson gson = new Gson();
        // query for posts by id

        if (url.getQuery() != null && checkQuery(url.getQuery(), "postid=")
                && checkQuery(url.getQuery(), "maxlength=" )) {
            String maxlengthstring = getQueryMap(url.getQuery()).get("maxlength");
            String postidstring = getQueryMap(url.getQuery()).get("postid");

            int postid = Integer.parseInt(postidstring);
            int maxlength = Integer.parseInt(maxlengthstring);

            response = gson.toJson(Database.getPosts(postid));

        } else if (url.getQuery() != null && checkQuery(url.getQuery(),"postid=")){

            int id = Integer.parseInt(url.getQuery().substring((url.getQuery().indexOf("=")+1)));

            response = gson.toJson(Database.getPosts(id));

        }
        else {
            // update response with all posts
            response =  "{\"status\":\"IS IT HERE 2\"}";
        }
        return response;
    }


    //Method to check if the query is in the correct form
    static Boolean checkQuery(String query, String contains){

        if(query.toLowerCase().contains(contains)){
            query.replace(contains,"");
            if(query.matches(".*\\d+.*")){
                return true;
            }
            return false;
        }
        return false;
    }

    public static Map<String, String> getQueryMap(String query)
    {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<>();
        for (String param : params)
        {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }
}//end class
