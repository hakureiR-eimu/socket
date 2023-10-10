// MultiThreadsServer.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class MultiThreadsServer
{
    // Main method
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int port=8000;
        String path="/source";
        try
        {
            // Create a server socket
            System.out.println("serve is running now.You need to init first");
            System.out.print("port(default: 8000): ");
            port = scan.nextInt();
            scan.nextLine();
            System.out.println("server bind to port:"+port+" successfully");
            System.out.print("source(default: source): ");
            path = scan.nextLine();
            System.out.println("server bind to directory:"+path+" successfully");
            System.out.println("server socket is running");
            /**
             * 生成服务器端的socket对象
             */
            ServerSocket serverSocket = new ServerSocket(port);
            // Number a thread
            int i = 0;

            while (true)
            {
                // Listen for a new connection request
                Socket connectToClient = serverSocket.accept();
                /**
                 * 当客户端请求时创造一个socket对象
                 */
                // Print the new connect number on the console
                //System.out.println("Starting thread "+i);
                System.out.println("\n接受新连接:");
                System.out.println("客户端地址: " + connectToClient.getInetAddress());
                System.out.println("客户端端口: " + connectToClient.getPort());
                /**
                 * 把对象传入子线程
                 */
                ThreadHandler thread = new ThreadHandler(connectToClient, i,path);
                /**
                 * 启动子线程
                 */
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
    private String directory;
    // Construct a thread
    public ThreadHandler(Socket socket, int i,String path)
    {
        connectToClient = socket;
        counter = i;
        directory = path;
    }
    private static String getContentType(Path filePath) {
        String fileName = filePath.getFileName().toString();
        if (fileName.endsWith(".html")) {
            return "text/html";
        } else if (fileName.endsWith(".css")) {
            return "text/css";
        } else if (fileName.endsWith(".js")) {
            return "application/javascript";
        } else {
            return "application/octet-stream"; // 默认情况下使用二进制流
        }
    }
    // Implement the run() method for the thread
    public void run()
    {
        try
        {
            // Create data input and print streams
//            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            OutputStream out = clientSocket.getOutputStream();
            /**
             * 初始化输入流和输出流
             */
            BufferedReader in = new BufferedReader(new InputStreamReader(connectToClient.getInputStream()));
            OutputStream out = connectToClient.getOutputStream();
            String requestLine = in.readLine();
            System.out.println("客户端请求: " + requestLine);
            if (requestLine == null) {
                throw new NullPointerException("requestLine is null");
            }
            String[] requestParts = requestLine.split(" ");
            if (requestParts.length >= 2) {
                String requestedFile = requestParts[1];
                Path filePath = Paths.get(directory, requestedFile.substring(1));

                if (Files.exists(filePath) && !Files.isDirectory(filePath)) {
                    // 读取文件内容并发送响应
                    byte[] fileContent = Files.readAllBytes(filePath);
                    String contentType = getContentType(filePath);
                    /**
                     * 构造回复报文
                     */
                    String response = "HTTP/1.1 200 OK\r\n"
                            + "Content-Type: " + contentType + "\r\n"
                            + "Content-Length: " + fileContent.length + "\r\n"
                            + "\r\n";
                    //System.out.println("\n"+response+"\n");
                    out.write(response.getBytes());
                    out.write(fileContent);
                    out.flush();
                    System.out.println("回复成功");
                } else {
                    /**
                     * 构造404报文
                     */
                    // 文件不存在，发送404响应
                    String notFoundResponse = "HTTP/1.1 404 Not Found\r\n\r\n";
                    out.write(notFoundResponse.getBytes("UTF-8"));
                    out.flush();
                    System.err.println("对应文件不存在");
                }
            }

            // 关闭输入流和输出流
            in.close();
            out.close();
        }
        catch(IOException ex) {
            System.err.println(ex);
        } catch (NullPointerException e) {
            // 捕获 NullPointerException 并输出 "404"
            System.err.println("对应文件不存在");
        }
    }
}
