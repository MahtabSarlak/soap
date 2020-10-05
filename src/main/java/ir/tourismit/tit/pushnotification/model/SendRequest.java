package ir.tourismit.tit.pushnotification.model;

import lombok.Data;
/**
 * Corresponding class to the Send service request .
 * @author m.sarlak
 */
@Data
public class SendRequest {
    private final String[] destNo;
    private final String bodyMsg;
    private final String srcNumber;
}
