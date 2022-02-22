package tn.esprit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

@EnableAspectJAutoProxy
@EnableScheduling
@SpringBootApplication
public class ExamenBlancApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenBlancApplication.class, args);
		
  

	}

}
