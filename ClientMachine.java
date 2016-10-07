import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
public class ClientMachine {
    private static final int TIMEOUT = 10000;

    private static Socket socket;

    private static OutputStream os;

    private static OutputStreamWriter osw;

    private static BufferedWriter bw;

    public static void main(String args[]) {

        try {

            int port = 8080;
            socket = new Socket("localhost", port);


            //Here we send the message over to the server.
            os = socket.getOutputStream();
            System.out.println("Made Socket");
            
            osw = new OutputStreamWriter(os);
            System.out.println("Made OSW");
            
            bw = new BufferedWriter(osw);

            String message = "Hello from the client!";

            bw.write(message);

            bw.flush();
            os.flush();
            osw.flush();
            System.out.println("The client successfully sent the message to the client...." + message);
        } catch(Exception exp) {
            System.out.println("Exception thrown!!");
        }

        finally {
            
            try {
            socket.close();
        } catch(IOException ioe) {
            System.out.println("Socket did not close properly");
        } catch(NullPointerException npe) {
            System.out.println("null pointer error!");
        }
        }
    }
}


