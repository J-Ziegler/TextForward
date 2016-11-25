import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by J-Ziegler on 11/24/16.
 */

public class Client {

	public static void main(String[] args) {

		String mm_host = "127.0.0.1";
		int port = 46163;

		try {
			Socket sock = new Socket(mm_host, port);

            // It is important that the OutputStream is created first for Java reasons.
            ObjectOutputStream mm_objOutStrm = new ObjectOutputStream(sock.getOutputStream());
            ObjectInputStream mm_objInStrm = new ObjectInputStream(sock.getInputStream());

            mm_objOutStrm.writeObject(new Status("Initial"));

            Status s = (Status) mm_objInStrm.readObject();

            if ("Accept".equals(s.getMessage())) {

            } else if ("Reject".equals(s.getMessage())) {
                System.err.println("Server rejected the connection.");
            } else {
                throw new InvalidObjectException("Received neither Accept nor Reject from the server.");
            }
            System.out.println(s.getMessage());

            sock.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

	}

}
