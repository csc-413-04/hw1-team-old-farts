package simpleserver;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;


class SimpleServer {

  public static void main(String[] args) throws IOException {
    ServerSocket ding;
    Socket dong = null;
    String parseMe = null;
    String resource = null;
    //creating a variable that is available to the entire class so that it can be used for making queries
    //that are sent to the factory
    URL urlToUseForQuery = null;
    try {
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

        try {

          // read the first line to get the request method, URI and HTTP version
          String line = in.readLine();
          parseMe = line;
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

        //printing the query to the factory
//        String [] requestParts = parseMe.split(" ");
//        String endpoint = requestParts[1];

        urlToUseForQuery = new URL("http://localhost:1299"+parseMe.substring(parseMe.indexOf("/"), parseMe.indexOf(" HTTP")));

        writer.println(ProcessFactoryB.process(urlToUseForQuery));

//        ServerProcessor serverProcessor = ProcessFactoryB.getProcessor(endpoint);
//        writer.println(serverProcessor.process(endpoint));
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
