import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

// ToDo: Make everything not static. Probably a good idea.

public class Client {

    private static Scanner scan = new Scanner(System.in);
    private static Socket sock;
    private static ObjectInputStream serverInStrm;
    private static ObjectOutputStream serverOutStrm;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        setUp();
        establishConnection(sock, serverInStrm, serverOutStrm);



        sock.close();

    }

    /**
     * This method exists to establish the initial settings, connection info, etc.
     */
    private static void setUp() throws IOException {
        String host;
        int port = 46163;

        System.out.println("Enter the IP you would like to connect to:");
        host = scan.nextLine();

        System.out.println(String.format("Enter the port for the host: (default is %d)", port));
        String portString = scan.nextLine();
        if (!portString.isEmpty()) {
            port = Integer.parseInt(portString);
        }

        sock = new Socket(host, port);
        serverOutStrm = new ObjectOutputStream(sock.getOutputStream());
        serverInStrm = new ObjectInputStream(sock.getInputStream());
    }

    private static void establishConnection(Socket sock, ObjectInputStream inStrm, ObjectOutputStream outStrm) throws IOException, ClassNotFoundException {
        try {
            outStrm.writeObject(new Status(0, 1));
            sock.setSoTimeout(1000);
            Status serverResponse = (Status) inStrm.readObject();

            if (serverResponse.getMessageType() == -1) {
                handleRejection(serverResponse);
            }

        } catch (SocketTimeoutException e) {

        }
    }

    private static void handleRejection(Status rejection) {

    }

}
