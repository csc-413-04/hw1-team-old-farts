package simpleserver;

import java.net.URL;

public class ProcessFactoryB {
    static String response;

    protected static String process(URL url) throws Exception {
        //resetting the response for each new query
        response = "";

        //switch executes the behavior based on the query that is requested
        switch (url.getPath()){
            case "/user":
                response = ProcessorForUser.processUser(url);
            break;

            case "/posts":
                response = ProcessorForPost.processPost(url);
            break;

            default:
                //default case, path not supported
                response =  "{\"status\":\"ERROR\"}";
            break;
        }
        return response;
    }
}
