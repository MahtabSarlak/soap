package com.tourismit.tit.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	void temp(){
		System.out.println("check");
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SoapClientConfig.class);
		ArticleClient articleClient = annotationConfigApplicationContext.getBean(ArticleClient.class);
//		System.out.println(articleClient.getArticle(1));
	}

}
