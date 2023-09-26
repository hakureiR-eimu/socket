// MultiThreadsServer.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.io.*;
import java.net.*;
import java.util.*;

public class MultiThreadsServer
{
    // Main method
    public static void main(String[] args)
    {
        try
        {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8000);

            // Number a thread
            int i = 0;

            while (true)
            {
                // Listen for a new connection request
                Socket connectToClient = serverSocket.accept();

                // Print the new connect number on the console
                System.out.println("Starting thread "+i);

                // Create a new thread for the connection
                ThreadHandler thread = new ThreadHandler(connectToClient, i);

                // Start the new thread
                thread.start();

                // Increment i to number the next connection
                i++;
            }
        }
        catch(IOException ex)
        {
            System.err.println(ex);
        }
    }
}

// Define the thread class for handling a new connection
class ThreadHandler extends Thread
{
    private Socket connectToClient; // A connected socket
    private int counter; // Number the thread

    // Construct a thread
    public ThreadHandler(Socket socket, int i)
    {
        connectToClient = socket;
        counter = i;
    }

    // Implement the run() method for the thread
    public void run()
    {
        try
        {
            // Create data input and print streams
            BufferedReader isFromClient = new BufferedReader(
                    new InputStreamReader(connectToClient.getInputStream()));
            PrintWriter osToClient =
                    new PrintWriter(connectToClient.getOutputStream(), true);

            // Continuously serve the client
            while (true)
            {
                // Receive data from the client in string
                StringTokenizer st = new StringTokenizer
                        (isFromClient.readLine());

                // Get radius
                double radius = Double.valueOf(st.nextToken());  //new Double(st.nextToken()).doubleValue();
                System.out.println("radius received from client: "+radius);

                // Compute area
                double area = radius*radius*Math.PI;

                // Send area back to the client
                osToClient.println(area);
                System.out.println("Area found: " + area);
            }
        }
        catch(IOException ex)
        {
            System.err.println(ex);
        }
    }
}