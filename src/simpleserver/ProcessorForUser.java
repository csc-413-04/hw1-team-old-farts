/**************************************************************************************************
 *Class that processes queries for users
 * This class supports queries for all users or a user with the provided ID
 *
 **************************************************************************************************/

package simpleserver;

//removing access modifier to restrict access to class and package
class ProcessorForUser extends ProcessorFactory {

    //method that processes the query for a userId
    protected String process(int userId){
        //JsonReader.staticJsonReader is a static object in the JsonReader class
        return JsonReader.staticJsonReader.getUser(userId);
    }//end createResponse Method
    //process for any request for all items
    protected String process(){
        return JsonReader.staticJsonReader.toString();
    }

}//end class