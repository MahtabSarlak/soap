package ir.tourismit.tit.pushnotification.service;

import ir.tourismit.tit.pushnotification.model.SendResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class HTTPConnection {

    @Value("${payamsms.url}")
    private String address;
    private static final Logger logger = LogManager.getLogger();

    public List<SendResult> sendPostReques(String request) {
        URL url = null;
        String respond = "";
        try {
            url = new URL(address);
            HttpURLConnection connection = null;
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
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
            respond = rd.readLine();
            while ((line = rd.readLine()) != null)
                respond = line;
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return parseResponse(respond);
    }

    private List<SendResult> parseResponse(String response){
       List<SendResult> sendResults = new ArrayList<>();
        response=response.substring(response.indexOf("<item xsi:type=\"tns:SendResult\">"),response.indexOf("</return>"));
        String items[]= response.split("<item xsi:type=\"tns:SendResult\">");
        for (String item:items) {
            if(!item.equals("")) {
                String parts[] = item.split("><");
                String mobile = parts[1].substring(parts[1].indexOf(">")+1, parts[1].indexOf("<"));
                String id = parts[0].substring(parts[0].indexOf(">")+1, parts[0].indexOf("<", parts[0].indexOf("<")+1));
                SendResult sendResult = new SendResult(id,mobile);
                sendResults.add(sendResult);
            }
        }
        return sendResults;
    }
}
