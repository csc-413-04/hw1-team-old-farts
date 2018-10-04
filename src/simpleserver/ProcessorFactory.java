/**************************************************************************************************
 * This abstract class is the Factory for the various responses that are required for each endpoint
 * The possible end points are: /user, /user?userid, /posts, /posts?postid
 *
 **************************************************************************************************/

package simpleserver;

import java.net.URL;
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
                  if(url.getQuery() != null){
                      int id = Integer.parseInt(url.getQuery().substring((url.getQuery().indexOf("=")+1)));
                      response = response + ( new ProcessorForUser().process(id));
                }
                else if(url.getQuery() == null){
                    //Logic for if there's no query to get all users
                  }
                else {
                    response = response + new ProcessorForUser().process();
                    }
                  break;

            case "/posts":
                  //query for posts by id
                  if(url.getQuery() != null){
                      int id = Integer.parseInt(url.getQuery().substring((url.getQuery().indexOf("=")+1)));
                      response = response + new ProcessorForPost().process(id);
                  } else {
                      // update response with all posts
                      response = response + new ProcessorForPost().process();
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

}//end class