import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    private static BufferedReader in;
    private static BufferedWriter out;
    private static Socket cSocket;
    private static ServerSocket serverSocket;

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String role = scan.next();
        if (role.equals("client"))
            try {
                try {
                    cSocket = new Socket("localhost", 9091);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(cSocket.getOutputStream()));
                    System.out.println("Please, enter your message here:");
                    String message = reader.readLine();
                    JSONObject json = new JSONObject();
                    json.put("name", role);
                    json.put("message", message);
                    out.write(json.toJSONString());
                    out.flush();
                } finally {
                    System.out.println("Client is shut down ... ");
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
                    System.out.println("Server has started working !");
                    cSocket = serverSocket.accept();
                    try {
                        in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
                        out = new BufferedWriter(new OutputStreamWriter(cSocket.getOutputStream()));
                        String word = in.readLine();
                        JSONObject json = StringToJson(word);
                        word = jsonToString(json);
                        System.out.println(word);
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

    public static JSONObject StringToJson(String text) throws ParseException {
        return (JSONObject) new JSONParser().parse(text);
    }

    public static String jsonToString(JSONObject json) {
        return json.get("name") + ": " + json.get("message") + "\n";
    }
}
