package simpleserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

class SimpleServer {

  public static void main(String[] args) throws IOException {
    ServerSocket ding;
    Socket dong = null;
    String resource = null;
    //creating a variable that is available to the entire class so that it can be used for making queries
    //that are sent to the factory
    URL urlResponse = null;
    try {

      Database.getDatabase();
      ding = new ServerSocket(1299);
      System.out.println("Opened socket " + 1299);
      while (true) {

        // keeps listening for new clients, one at a time
        try {
          dong = ding.accept(); // waits for client here
        } catch (IOException e) {
          System.out.println("Error opening socket");
          System.exit(1);
        }

        InputStream stream = dong.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String parseMe = null;
        try {

          // read the first line to get the request method, URI and HTTP version
          String line = in.readLine();
          parseMe = line;
          //constructing a URL object that will be passed to the ProcessorFactory for processing
          //string split index 1


          System.out.println("----------REQUEST START---------");
          System.out.println(line);
          // read only headers
          line = in.readLine();
          //parseMe = line;
          while (line != null && line.trim().length() > 0) {
            int index = line.indexOf(": ");
            if (index > 0) {
              System.out.println(line);
            } else {
              break;
            }
            line = in.readLine();
          }
          System.out.println("----------REQUEST END---------\n\n");
        } catch (IOException e) {
          System.out.println("Error reading");
          System.exit(1);
        }

        BufferedOutputStream out = new BufferedOutputStream(dong.getOutputStream());
        PrintWriter writer = new PrintWriter(out, true);  // char output to the client

        // every response will always have the status-line, date, and server name
        writer.println("HTTP/1.1 200 OK");
        writer.println("Server: TEST");
        writer.println("Connection: close");
        writer.println("Content-type: text/html");
        writer.println("");

        // Body of our response
        // order of logic

        /*Take response from factory, should be a string or Json object. Write fromJson, similar
        to the end of Json Read
        */
        //Print response here using the writer print ln

        urlResponse = new URL("http://localhost:1299"+parseMe.substring(parseMe.indexOf("/"), parseMe.indexOf(" HTTP")));

        writer.println(ProcessorFactory.process(urlResponse));

        dong.close();
      }
    } catch (IOException e) {
      System.out.println("Error opening socket");
      System.exit(1);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
