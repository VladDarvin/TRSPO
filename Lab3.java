import java.io.*;
import java.util.Scanner;
import java.net.Socket;
import java.net.ServerSocket;

public class Main {
    private static Socket cs;
    private static ServerSocket server;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String role = sc.next();
        if (role.equals("client"))
            try {
                try {
                    cs = new Socket("localhost", 9090);
                    reader = new BufferedReader(new InputStreamReader(System.in));
                    in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(cs.getOutputStream()));

                    System.out.println("ENTER YOUR MESSAGE, PLEASE");
                    String word = reader.readLine();
                    out.write(word + "\n");
                    out.flush();
                    String serverWord = in.readLine();
                    System.out.println(serverWord);
                } finally {
                    System.out.println("Клиент был закрыт...");
                    cs.close();
                    in.close();
                    out.close();
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        else
            try {
                try {
                    System.out.println("Сервер запущен!");
                    server = new ServerSocket(9090);
                    cs = server.accept();
                    try {
                        in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
                        out = new BufferedWriter(new OutputStreamWriter(cs.getOutputStream()));
                        System.out.println(in.readLine());
                        out.flush();

                    } finally {
                        cs.close();
                        in.close();
                        out.close();
                    }
                } finally {
                    System.out.println("Server is shutting down ...");
                    server.close();
                }
            } catch (IOException e) {
                System.err.println(e);
            }
    }
}
