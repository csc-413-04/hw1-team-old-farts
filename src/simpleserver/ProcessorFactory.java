/**************************************************************************************************
 * This abstract class is the Factory for the various responses that are required for each endpoint
 * The possible end points are: /user, /user?userid, /posts, /posts?postid
 *
 **************************************************************************************************/

package simpleserver;

import java.net.URL;

public class ProcessorFactory {
    static String response;

    protected static String process(URL url){
        //resetting the response for each new query
        response = "empty response";

        //switch executes the behavior based on the query that is requested
        switch (url.getPath()){

            case "/user":
                  //query for users
                  //update response with query results
                  if(url.getQuery() != null){
                      response = response + ( new ProcessorForUser().process(url.getQuery().indexOf("=")+1));
                  }
                  //response = response + ( new ProcessorForUser().process(url.getQuery().indexOf("=")+1));
                  break;

            case "/posts":
                  //query for posts by id
                  if(url.getQuery() != null){
                      response = response + ((new ProcessorForPost().process(url.getQuery().indexOf("="+1))));
                  }
                  //update response with all posts
                  //response = response + ((new ProcessorForPost().process(url.getQuery().indexOf("="+1))));
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