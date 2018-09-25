/**************************************************************************************************
 * This abstract class is the Factory for the various responses that are required for each endpoint
 * The possible end points are: /user, /user?userid, /posts, /posts?postid
 *
 **************************************************************************************************/

//Importing GSON library in the abstract class so that all subclasses have access to the library
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class ProcessorFactory {
    String response;

    protected Object queryJSON(Object data, String urlPath){

        switch (urlPath){

            case "user": //create user query
                    if(urlPath.contains("?")){
                        //query for user ID
                        //update response with query results
                    }
                    else{
                        //query for users
                        //update response with query results
                    }
                break;

            case "posts": //create post query
                    if(urlPath.contains("?")){
                        //query for post ID
                        //update response with query results
                    }
                    else{
                        //query for posts
                        //update response with query results
                    }
                break;

            default:
                //default case, path not supported
                response =  "{\"status\":\"ERROR\"}";
                break;

        }//end switch

        return response;
    }


}