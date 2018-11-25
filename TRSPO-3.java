import java.io.*;
import java.util.Scanner;
import java.net.Socket;
import java.net.ServerSocket;

public class Main {
    private static Socket cSocket;
    private static ServerSocket serverSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String role = scan.next();
        if (role.equals("client"))
            try {
                try {
                    cSocket = new Socket("localhost", 9091);
                    reader = new BufferedReader(new InputStreamReader(System.in));
                    in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(cSocket.getOutputStream()));
                    System.out.println("Enter your message, please :");
                    String message = reader.readLine();
                    out.write(message + "\n");
                    out.flush();
                    String serverRespond = in.readLine();
                    System.out.println(serverRespond);
                } finally {
                    System.out.println("Client app is shutting down ...");
                    cSocket.close();
                    in.close();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        else
            try {
                try {
                    serverSocket = new ServerSocket(9091);
                    cSocket = serverSocket.accept();
                    System.out.println("Server has started working !");
                    try {
                        in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
                        out = new BufferedWriter(new OutputStreamWriter(cSocket.getOutputStream()));
                        System.out.println(in.readLine());
                        out.flush();
                    } finally {
                        cSocket.close();
                        in.close();
                        out.close();
                    }
                } finally {
                    System.out.println("Server is shutting down ...");
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
