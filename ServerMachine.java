import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class ServerMachine {

   private static Socket socket;
   private static ServerSocket serverSocket;

    public static void main(String[] args) {
        System.out.println("Waiting for host to connect..........");
        System.out.println("...");
        try {
              int port = 8080;

            serverSocket = new ServerSocket(port);
            System.out.println("The Server is listening for the message...");
            InputStream is;
            InputStreamReader isr;
            BufferedReader br;


            while(!serverSocket.isClosed()) {
                socket = serverSocket.accept();
                is = socket.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                String recieved = br.readLine();
                System.out.println("The message from the client reads...");
                System.out.println(recieved);
            }

        } catch (IOException ioe) {
            System.out.println("Could not get our message");
        }

        finally {
                try {
                    socket.close();
                    serverSocket.close();
                } catch(IOException ioe) {
                    System.out.println("Socket in ServerMachine did not close correctly");
                }
        }
    }
}
