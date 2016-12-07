import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by J-Ziegler on 11/24/16.
 */

public class MiddleMan {

	public static void main(String[] args) {
        System.out.println("Server Started");
        ServerSocket servSock = null;
        try {
            servSock = new ServerSocket(46163);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Main Loop
		while(true) {
            try {
				Socket client = servSock.accept();
                System.out.println("Client has connected");
                ObjectOutputStream outStrm = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream inStrm = new ObjectInputStream(client.getInputStream());

				while (!client.isInputShutdown()) {
                    Status clientStatus = (Status) inStrm.readObject();
                    outStrm.writeObject(new Status(1, 2));
                    outStrm.flush();
                }
						
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // End Main Loop
	}
}
