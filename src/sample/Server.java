package sample;
import java.net.*;
import java.io.*;

public class Server {
        public static void main(String[] args) throws IOException , InterruptedException {
            int count = 0;
            ServerSocket serverSocket = new ServerSocket(3306);
            System.out.println("Server starting...");
            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client accepted  " + (count++));

                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(
                                clientSocket.getOutputStream()));
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()));

                String request = reader.readLine();
                Thread.sleep(3000);
                String response = "#" + count + " request lan "  + request.length();

                writer.write(response);
                writer.newLine();
                writer.flush();

                writer.close();
                reader.close();
                clientSocket.close();
            }
        }
}
