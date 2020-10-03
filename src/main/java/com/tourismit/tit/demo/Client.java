package com.tourismit.tit.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {
    public static void main(String[] args) {
        String addr = "http://new.payamsms.com/services/?wsdl";
        String request = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:SMSAPIwsdl\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <urn:Send soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <organization xsi:type=\"xsd:string\">gardeshgari2</organization>\n" +
                "         <username xsi:type=\"xsd:string\">TIT_DigitalBanking</username>\n" +
                "         <password xsi:type=\"xsd:string\">T!TD!git@l</password>\n" +
                "         <srcNumber xsi:type=\"xsd:string\">98200023950</srcNumber>\n" +
                "         <body xsi:type=\"xsd:string\">test</body>\n" +
                "         <destNo xsi:type=\"urn:Destinations\" SOAP-ENC:arrayType=\"xsd:string[2]\">\n" +
                "\t\t<item>989128027923</item>\n" +
                "        \t<item>989359814321</item>\n" +
                "\t\t</destNo>\n" +
                "         <flash xsi:type=\"xsd:string\">1</flash>\n" +
                "      </urn:Send>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        URL url = null;
        try {
            url = new URL(addr);
            HttpURLConnection connection = null;
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
            connection.setDoOutput(true);
            PrintWriter pw = null;
            pw = new PrintWriter(connection.getOutputStream());
            pw.write(request);
            pw.flush();
            connection.connect();
            BufferedReader rd = null;
            rd = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            String respond = "";
            respond = rd.readLine();
            while ((line = rd.readLine()) != null)
                respond = line;
            System.out.println(respond);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
