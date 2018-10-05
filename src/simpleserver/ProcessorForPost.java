/**************************************************************************************************
 *Class that processes queries for posts
 * This class supports queries for all posts or a post with the provided ID
 *
 **************************************************************************************************/

package simpleserver;

import com.oracle.javafx.jmx.json.JSONReader;

//removing access modifier to restrict access to class and package
class ProcessorForPost extends ProcessorFactory {

    //method that processes the query for a postId
    protected String process(int postId ){
        //JsonReader.staticJsonReader is a static object in the JsonReader class
        return null;
        //return JsonReader.staticJsonReader.getPost(postId);
    }//end createResponse Method
    //process for any request for all items
    protected String process(){
        return JsonReader.staticJsonReader.toString();
    }

}//end class
