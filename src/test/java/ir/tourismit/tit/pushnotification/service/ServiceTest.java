package ir.tourismit.tit.pushnotification.service;

import ir.tourismit.tit.pushnotification.model.SendRequest;
import ir.tourismit.tit.pushnotification.model.SendResult;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
class ServiceTest {
    @Autowired
    private Service service;
    private static String [] destNo;
    private static SendRequest sendRequest;


    @BeforeAll
    static void before(){
        destNo = new String[]{"989128027923", "989359814321"};
        sendRequest = new SendRequest(destNo,"سلام!","98200023950");
    }

    @Test
    void send(){
        List<SendResult> sendResults = service.send(sendRequest);
        for (int i =0; i< sendResults.size();i++) {
            assertEquals(destNo[i], sendResults.get(i).getMobile());
        }
    }
}