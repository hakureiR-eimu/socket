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
        String address ="127.0.0.1";
        int port = 8000;
        String localPath ="/rec";
        String tarPath="/source/index.html";
        Scanner scanner = new Scanner(System.in);
        while(true){
            String command = scanner.nextLine();
            String[] commandParts = command.split(" ");
            if(command=="") {
                continue;
            }
            else if(!commandParts[0].equals("socket")) {
                System.out.println("Command '" + commandParts[0] + "' not found");
            }
            else if(commandParts[1].equals("-h")||commandParts[1].equals(("--help"))){
                System.out.println("socket-client implement\n" +
                        "\n" +
                        "Usage: socket [OPTIONS] [INPUT]\n" +
                        "\n" +
                        "Options:\n" +
                        "  --help                 Print help information\n" +
                        "  --address              Set the target IP address (default: 127.0.0.1).\n" +
                        "  --port                 Set the target port (default: 8000)\n"+
                        "  --local                Set the local receive path(default: /rec)\n"+
                        "  --reqInfo              Set the target filePath(default: /source/index.html)\n"+
                        "  --sendReq              send request");
            }
            else if(commandParts[1].equals("--address")){
                if(!commandParts[2].isEmpty())
                address=commandParts[2];
                //System.out.println("hello");
            }
            else if(commandParts[1].equals("--port")){
                if(!commandParts[2].isEmpty())
                port=Integer.getInteger(commandParts[2]);
                //System.out.println("hello");
            }
            else if(commandParts[1].equals("--local")){
                if(!commandParts[2].isEmpty())
                localPath = commandParts[2];
                //System.out.println("hello");
            }
            else if(commandParts[1].equals("--reqInfo")){
                if(!commandParts[2].isEmpty())
                tarPath = commandParts[2];
                //System.out.println("hello");
            }
            else if(commandParts[1].equals("--sendReq")) {
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
                        //System.out.println("http request < IP | port | directory | target > { 127.0.0.1 | 7050 | ./rec } | ./source/index.html ");
                        System.out.print("Please enter a radius: ");
                        //Scanner scanner = new Scanner(System.in); // 创建一个Scanner对象，用于读取标准输入
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
            else {
                System.out.println("command not found");
            }
        }

    }
}