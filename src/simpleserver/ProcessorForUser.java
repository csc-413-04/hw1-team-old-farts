/**************************************************************************************************
 *Class that processes queries for users
 * This class supports queries for all users or a user with the provided ID
 *
 **************************************************************************************************/

package simpleserver;

import com.google.gson.Gson;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

//removing access modifier to restrict access to class and package
class ProcessorForUser implements ServerProcessor {

    protected static String process(URL url) {

        Gson gson = new Gson();

        //query for users
        //update response with query results
        //method that processes the query for a userId
        String response;
        if(url.getQuery() != null && ProcessorForPost.checkQuery(url.getQuery(),"userid=")){
            int id = Integer.parseInt(url.getQuery().substring((url.getQuery().indexOf("=")+1)));

            response = gson.toJson(Database.getUsers(id));

        }
        else if(url.getQuery() == null){
            //Logic for if there's no query to get all users
            response =  "{\"status\":\"USERS\"}";
        }
        else {
            response =  "{\"status\":\"IS IT HERE\"}";
        }
        return response;
    }//end createResponse Method

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