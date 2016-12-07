import java.io.Serializable;

/**
 * Status objects are used during initial contact between the Client/Device
 * and the Server. By utilization of connection codes, accounts are not necessary.
 */

public class Status implements Serializable {

    private enum ClientType {
        Device(0),
        Client(1),
        Server(2);

        private int intValue;

        ClientType(int n) {
            this.intValue = n;
        }

        public int getValue() {
            return this.intValue;
        }

        public static ClientType fromInt(int i) {
            for (ClientType cT : ClientType.values()) {
                if (cT.getValue() == i) {
                    return cT;
                }
            }
            return null;
        }
    }

    private enum MessageType {
        Reject(-1),
        Contact(0),
        MatchMade(1);

        private int intValue;

        MessageType(int n) {
            this.intValue = n;
        }

        public int getValue() {
            return this.intValue;
        }

        public static MessageType fromInt(int i) {
            for (MessageType cT : MessageType.values()) {
                if (cT.getValue() == i) {
                    return cT;
                }
            }
            return null;
        }
    }

    /*
    Message varies based on MessageType:
    - Contact: Connection Code
    - Reject: Rejection Reason
    - MatchMade: Optional Information about the thing.
     */
    private String message;
    private ClientType clientType;
    private MessageType messageType;

    /**
     * Status Messages are used by the Client and Server to exchange
     * information about the initial connection, or general check-in.
     *
     * @param messageType
     * @param clientType
     */

    public Status(int messageType, int clientType) {
        this.messageType = MessageType.fromInt(messageType);
        this.clientType = ClientType.fromInt(clientType);
    }

    public Status(int messageType, int clientType, String message) {
        this.messageType = MessageType.fromInt(messageType);
        this.clientType = ClientType.fromInt(clientType);
        this.message = message;
    }

    /**
     * <p>-1: Rejection Message, either server doesn't like you or the other client dropped.</p>
     * <p>0: Contact. The message contains a connection/verification code.</p>
     * <p>1: Match Made. The server is ready</p>
     */
    public int getMessageType() {
        System.out.println("Trying to do a thing");
        return messageType.getValue();
    }
}
