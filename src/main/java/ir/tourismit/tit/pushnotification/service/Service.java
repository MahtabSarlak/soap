package ir.tourismit.tit.pushnotification.service;

import ir.tourismit.tit.pushnotification.model.SendRequest;
import ir.tourismit.tit.pushnotification.model.SendResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * sms service
 *
 * @author m.sarlak
 */
@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private HTTPConnection httpConnection;
    @Autowired
    private GenerateSoapRequest generateSoapRequest;

    public List<SendResult> send(SendRequest sendRequest){
       String request = generateSoapRequest.generateRequest(sendRequest);
        return httpConnection.sendPostReques(request);
    }
}
