import java.io.Serializable;
import java.security.InvalidParameterException;

/**
 * Status objects are used during initial contact between the Client/Device
 * and the Server.
 */

public class Status implements Serializable {

    // TODO: Improve this Javadoc, attach them to the individual enumerated statuses.
    /**
     * StatusMessage
     *
     * <ul>
     * <li>Initial: Initial client/device contact. Server returns Accept or Reject based on load.</li>
     * <li>Device: Identifies the sender as the mobile device that sends/receives data.</li>
     * <li>DependentClient: Client that requires the server as an intermediary.</li>
     * <li>IndependentClient: Client that claims it can act as the server.</li>
     * <li>Accept: Server will accept the client.</li>
     * <li>Reject: Server rejects the client connection, for whatever reason.</li>
     * <li>Referral: Matched an IndependentClient, information sent.</li>
     * <li>Error: Signifies that something went wrong. Created in preparation of future need.</li>
     * </ul>
     *
     */
    private enum StatusMessage { Initial, Device, DependentClient, IndependentClient, Accept, Reject, Referral, Error}

    private StatusMessage msg;
    private String referredIP;
    private int referredPort;

    public Status(String msg) {
        this.msg = StatusMessage.Initial;
        if ("Initial".equals(msg)) {
            this.msg = StatusMessage.Initial;
        } else if ("Device".equals(msg)) {
            this.msg = StatusMessage.Device;
        } else if ("DependentClient".equals(msg)) {
            this.msg = StatusMessage.DependentClient;
        } else if ("IndependentClient".equals(msg)) {
            this.msg = StatusMessage.IndependentClient;
        } else if ("Accept".equals(msg)) {
            this.msg = StatusMessage.Accept;
        } else if ("Reject".equals(msg)) {
            this.msg = StatusMessage.Reject;
        } else if ("Referral".equals(msg)) {
            throw new InvalidParameterException("Referrals require an IP and Port to be passed");
        } else {
            throw new InvalidParameterException("Cannot create Status with message: " + msg);
        }
    }

    /**
     * @param ip IP of the IndependentClient
     * @param port Port for the same
     *
     * <p>Create a Referral Status. The message is set to Referral automatically.</p>
     */
    public Status(String ip, int port) {
        this.msg = StatusMessage.Referral;
        this.referredIP = ip;
        this.referredPort = port;
    }

    public StatusMessage getMessage() {
        return this.msg;
    }

    public String getReferredIP() {
        return this.referredIP;
    }

    public int getReferredPort() {
        return this.referredPort;
    }
}
