package ir.tourismit.tit.pushnotification.model;

import lombok.Data;
/**
 * Corresponding class to the Send service respond .
 * @author m.sarlak
 */
@Data
public class SendResult {
    private final String id;
    private final String mobile;
}
