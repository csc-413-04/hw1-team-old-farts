/**************************************************************************************************
 * This abstract class is the Factory for the various responses that are required for each endpoint
 * The possible end points are: /user, /user?userid, /posts, /posts?postid
 *
 **************************************************************************************************/

package simpleserver;

import java.net.URL;

public class ProcessorFactory implements ServerProcessor {

    protected static String process(URL url) {

    //    public String process(URL url) throws Exception {
        //resetting the response for each new query
        String response = "";

        //switch executes the behavior based on the query that is requested
        switch (url.getPath()){

            case "/user":
                response = ProcessorForUser.process(url);
                break;

            case "/posts":
                response = ProcessorForPost.process(url);
                break;

            default:
                //default case, path not supported
                response =  "{\"status\":\"ERROR\"}";
                break;
        }
        return response;
    }
}//end class