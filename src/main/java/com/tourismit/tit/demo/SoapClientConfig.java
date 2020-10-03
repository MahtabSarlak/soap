package com.tourismit.tit.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


@Configuration
public class SoapClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("com.tourismit.tit.demo");
        return jaxb2Marshaller;
    }

    @Bean
    public ArticleClient articleClient(Jaxb2Marshaller jaxb2Marshaller) {
        ArticleClient articleClient = new ArticleClient();
        articleClient.setDefaultUri("http://new.payamsms.com/services/wsdl");
        articleClient.setMarshaller(jaxb2Marshaller);
        articleClient.setUnmarshaller(jaxb2Marshaller);
        return articleClient;
    }
}
