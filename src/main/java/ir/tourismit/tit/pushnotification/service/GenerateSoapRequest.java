package ir.tourismit.tit.pushnotification.service;

import ir.tourismit.tit.pushnotification.model.SendRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GenerateSoapRequest {
    @Value("${payamsms.organization}")
    private String organization;
    @Value("${payamsms.userName}")
    private String userName;
    @Value("${payamsms.password}")
    private String password;

    public String generateRequest(SendRequest sendRequest) {
        StringBuilder request = new StringBuilder();
        request.append("<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:SMSAPIwsdl\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <urn:Send soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <organization xsi:type=\"xsd:string\">" + organization + "</organization>\n" +
                "         <username xsi:type=\"xsd:string\">" + userName + "</username>\n" +
                "         <password xsi:type=\"xsd:string\">" + password + "</password>\n" +
                "         <srcNumber xsi:type=\"xsd:string\">" + sendRequest.getSrcNumber() + "</srcNumber>\n" +
                "         <body xsi:type=\"xsd:string\">" + sendRequest.getBodyMsg() + "</body>\n" +
                "         <destNo xsi:type=\"urn:Destinations\" SOAP-ENC:arrayType=\"xsd:string[2]\">\n");
        for (String no : sendRequest.getDestNo()) {
            request.append("\t\t<item>" + no + "</item>\n");
        }
        request.append("\t\t</destNo>\n" +
                "         <flash xsi:type=\"xsd:string\">1</flash>\n" +
                "      </urn:Send>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>");
        return request.toString();
    }
}
