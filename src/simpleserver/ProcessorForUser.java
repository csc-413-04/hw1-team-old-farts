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
class ProcessorForUser extends ProcessorFactory {
    static String response;

    //method that processes the query for a userId
    protected static String processUser(URL url) throws Exception {

        Gson gson = new Gson();

        //query for users
        //update response with query results
        if(url.getQuery() != null && checkquery(url.getQuery(),"userid=")){
            int id = Integer.parseInt(url.getQuery().substring((url.getQuery().indexOf("=")+1)));

            response = gson.toJson(Database.getUser(id));

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