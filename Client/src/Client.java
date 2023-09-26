// Client.java: The client sends the input to the server and receives
// result back from the server
import java.io.*;
import java.net.*;
import java.util.*;

public class Client
{
    // Main method
    public static void main(String[] args)
    {
        try
        {
            // Create a socket to connect to the server
            Socket connectToServer = new Socket("localhost", 8000);

            // Create a buffered input stream to receive data
            // from the server
            BufferedReader isFromServer = new BufferedReader(
                    new InputStreamReader(connectToServer.getInputStream()));

            // Create a buffered output stream to send data to the server
            PrintWriter osToServer =
                    new PrintWriter(connectToServer.getOutputStream(), true);

            // Continuously send radius and receive area
            // from the server
            while (true)
            {
                // Read the radius from the keyboard
                System.out.print("Please enter a radius: ");
                Scanner scanner = new Scanner(System.in); // 创建一个Scanner对象，用于读取标准输入
                double radius = scanner.nextDouble();

                // Send the radius to the server
                osToServer.println(radius);

                // Get area from the server
                StringTokenizer st = new StringTokenizer(
                        isFromServer.readLine());

                // Convert string to double
                double area = Double.valueOf(st.nextToken ());

                // Print area on the console
                System.out.println("Area received from the server is "
                        +area);
            }
        }
        catch (IOException ex)
        {
            System.err.println(ex);
        }
    }
}