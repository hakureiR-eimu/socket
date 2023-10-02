// Client.java: The client sends the input to the server and receives
// result back from the server
import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.file.*;

public class Client
{
    // Main method
    public static void main(String[] args)
    {

        String address ="127.0.0.1";
        int port = 8000;
        String localPath ="/rec";
        String tarPath="/index.html";
        Scanner scanner = new Scanner(System.in);
        System.out.println("client socket is running!");
        while(true){
            System.out.print("expect command: ");
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
                        "  --reqInfo              Set the target filePath(default: /index.html)\n"+
                        "  --sendReq              send request");
            }
            else if(commandParts[1].equals("--address")){
                if(!commandParts[2].isEmpty()) {
                    address = commandParts[2];
                    System.out.println("address has confirmed");
                }
                else {
                    System.out.println("error:command not found");
                }
                //System.out.println("hello");
            }
            else if(commandParts[1].equals("--port")){
                if(!commandParts[2].isEmpty()) {
                    port = Integer.getInteger(commandParts[2]);
                    System.out.println("port has confirmed");
                }
                else {
                    System.out.println("error:command not found");
                }
                //System.out.println("hello");
            }
            else if(commandParts[1].equals("--local")){
                if(!commandParts[2].isEmpty()) {
                    localPath = commandParts[2];
                    System.out.println("localPath has confirmed");
                }
                else {
                    System.out.println("error:command not found");
                }
                //System.out.println("hello");
            }
            else if(commandParts[1].equals("--reqInfo")){
                if(!commandParts[2].isEmpty()) {
                    tarPath = commandParts[2];
                    System.out.println("targetPath has confirmed");
                }
                else {
                    System.out.println("error:command not found");
                }
                //System.out.println("hello");
            }
            else if(commandParts[1].equals("--sendReq")) {
                try {
//                    import java.io.*;
//                    import java.net.Socket;
//                    import java.nio.file.*;
//
//                    public class SaveHtmlToRecFolder {
//                        public static void main(String[] args) {
//                            String serverAddress = "localhost"; // 服务器地址
//                            int serverPort = 8080; // 服务器端口号
//                            String request = "GET /index.html HTTP/1.1\r\n" +
//                                    "Host: " + serverAddress + "\r\n" +
//                                    "\r\n"; // HTTP请求
//                            String recFolder = "rec"; // 保存文件的目录
//
//                            try {
//                                Socket socket = new Socket(serverAddress, serverPort);
//                                System.out.println("已连接到服务器 " + serverAddress + ":" + serverPort);
//
//                                // 发送HTTP请求
//                                OutputStream out = socket.getOutputStream();
//                                out.write(request.getBytes("UTF-8"));
//                                out.flush();
//
//                                // 读取服务器响应
//                                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                                StringBuilder response = new StringBuilder();
//                                String line;
//                                while ((line = in.readLine()) != null) {
//                                    response.append(line).append("\n");
//                                }
//
//                                // 关闭连接
//                                socket.close();
//
//                                // 保存响应到本地文件
//                                String htmlResponse = response.toString();
//                                if (htmlResponse.contains("HTTP/1.1 200 OK")) {
//                                    String filePath = Paths.get(recFolder, "index.html").toString();
//                                    try {
//                                        Files.write(Paths.get(filePath), htmlResponse.getBytes());
//                                        System.out.println("文件已保存至 " + filePath);
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//                                } else {
//                                    System.err.println("请求失败或文件不存在");
//                                }
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }

                    // Create a socket to connect to the server
                    //System.out.print("Input the target file(default:/index.html): ");
                    //tarPath = scanner.nextLine();
//                    address = "127.0.0.1";
//                    port = 8000;
//                    localPath = "/rec";
//                    tarPath ="/index.html";
                    System.out.println("成功发送请求");

                    String request = "GET " + tarPath + " HTTP/1.1\r\n" +
                            "Host: " + address + "\r\n" +
                            "\r\n"; // HTTP请求

                    Socket connectToServer = new Socket(address, port);
                    System.out.println("已连接到服务器 "+address+":port");
                    // Create a buffered input stream to receive data
                    // from the server

//                    BufferedReader isFromServer = new BufferedReader(new InputStreamReader(connectToServer.getInputStream()));

                    // Create a buffered output stream to send data to the server
//                    PrintWriter osToServer = new PrintWriter(connectToServer.getOutputStream(), true);

                    // Continuously send radius and receive area
                    // from the server

//                    while (true)
//                    {
//                        // Read the radius from the keyboard
//                        //System.out.println("http request < IP | port | directory | target > { 127.0.0.1 | 7050 | ./rec } | ./source/index.html ");
//                        System.out.print("Please enter content: ");
//                        //Scanner scanner = new Scanner(System.in); // 创建一个Scanner对象，用于读取标准输入
//                        double radius = scanner.nextDouble();
//
//                        // Send the radius to the server
//                        osToServer.println(radius);
//
//                        // Get area from the server
//                        StringTokenizer st = new StringTokenizer(
//                                isFromServer.readLine());
//
//                        // Convert string to double
//                        double area = Double.valueOf(st.nextToken ());
//
//                        // Print area on the console
//                        System.out.println("Area received from the server is "
//                                +area);
//                    }
//                }
//                catch (IOException ex)
//                {
//                    System.err.println(ex);
//                }
                    // 发送HTTP请求
                    OutputStream out = connectToServer.getOutputStream();
                    out.write(request.getBytes("UTF-8"));
                    out.flush();

                    // 读取服务器响应
                    BufferedReader in = new BufferedReader(new InputStreamReader(connectToServer.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    boolean headersCompleted = false;

                    while ((line = in.readLine()) != null) {
                        if (line.isEmpty() && !headersCompleted) {
                            headersCompleted = true;
                            continue;
                        }
                        if (headersCompleted) {
                            response.append(line).append("\n");
                        }
                    }
                    // 关闭连接
                    connectToServer.close();


                    // 保存响应到本地文件

                    String htmlResponse = response.toString();
                    //System.out.println(htmlResponse);
                    if (!htmlResponse.isEmpty()) {

                        String filePath = localPath+tarPath;
                        //System.out.println(filePath);
                        try {
                            //Files.createDirectories(Paths.get(localPath));
                            //System.out.println(filePath);
                            //System.out.println(String.valueOf(Paths.get(filePath)));
                            File file = new File("."+String.valueOf(Paths.get(filePath)));
                            Files.write(Path.of("."+filePath), htmlResponse.getBytes("utf-8"));
//                            FileWriter writer = new FileWriter("."+String.valueOf(Paths.get(filePath)));
//                            writer.write(htmlResponse);
                            //Files.write(Path.of(filePath), htmlResponse.getBytes());
                            System.out.println("文件已保存至 " + filePath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.err.println("请求失败或文件不存在");
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("command not found");
            }
        }

    }
}