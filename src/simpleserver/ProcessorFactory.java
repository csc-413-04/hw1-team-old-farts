/**************************************************************************************************
 * This abstract class is the Factory for the various responses that are required for each endpoint
 * The possible end points are: /user, /user?userid, /posts, /posts?postid
 *
 **************************************************************************************************/

package simpleserver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;

public class ProcessorFactory {
    static String response;

    protected static String process(URL url){
        //resetting the response for each new query
        response = "";

        //switch executes the behavior based on the query that is requested
        switch (url.getPath()){

            case "/user":
                  //query for users
                  //update response with query results
                  if(url.getQuery() != null && checkquery(url.getQuery(),"userid=")){
                      int id = Integer.parseInt(url.getQuery().substring((url.getQuery().indexOf("=")+1)));
                      response = response + ( new ProcessorForUser().process(id));
                    }
                    else if(url.getQuery() == null){
                    //Logic for if there's no query to get all users
                    }
                    else {
                      response =  "{\"status\":\"ERROR\"}";
                    }
                  break;

            case "/posts":
                  //query for posts by id
                  if(url.getQuery() != null && checkquery(url.getQuery(),"postid=") && checkquery(url.getQuery(),"maxlength=")) {

                      String maxlengthstring = getQueryMap(url.getQuery()).get("maxlength");
                      String postidstring = getQueryMap(url.getQuery()).get("postid");

                      int postid = Integer.parseInt(postidstring);
                      int maxlength = Integer.parseInt(maxlengthstring);

                      response = response + new ProcessorForPost().process(postid);
                      response = response.substring(maxlength, Math.min(response.length(), maxlength));

                  } else if (url.getQuery() != null && checkquery(url.getQuery(),"postid=")){

                      int id = Integer.parseInt(url.getQuery().substring((url.getQuery().indexOf("=")+1)));
                      response = response + new ProcessorForPost().process(id);

                  }
                  else {
                      // update response with all posts
                      response =  "{\"status\":\"ERROR\"}";
                  }
                  break;

            default:
                //default case, path not supported
                response =  "{\"status\":\"ERROR\"}";
                break;

        }//end switch
        //returning the response String after it's been updating according to the request
        return response;
    }//end process method

    //Method to check if the query is in the correct form
    static private Boolean checkquery(String query, String contains){

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
        Map<String, String> map = new HashMap<String, String>();
        for (String param : params)
        {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }
}//end class