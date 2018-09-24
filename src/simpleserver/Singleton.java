package simpleserver;

// Initiate a Lazy Initialization Singleton class
public class Singleton {

    // Singleton instance
    private static Singleton instance;

    // Singleton Constructor
    private Singleton(){}

    //
    public static Singleton getInstance(){

        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }


}
