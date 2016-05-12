package net.christophersnow.etl

import org.apache.camel.spring.boot.CamelSpringBootApplicationController
import org.apache.camel.spring.boot.FatJarRouter
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.*
import org.springframework.beans.factory.annotation.*


@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
class Application extends FatJarRouter {

    @Value('${cloudant_username}')
    private String cl_user

    @Value('${cloudant_password}')
    private String cl_pass

    @Value('${cloudant_database}')
    private String cl_db
	
	public static void main(String... args) {
		ApplicationContext applicationContext = new SpringApplication(Application.class).run(args);
		CamelSpringBootApplicationController applicationController =
				applicationContext.getBean(CamelSpringBootApplicationController.class);
		applicationController.run();
	}
 
    @Override
    public void configure() throws Exception {
    
        def cloudant_camel_url = "https://${cl_user}.cloudant.com:443/${cl_db}?username=${cl_user}&password=${cl_pass}"

        from("couchdb:${cloudant_camel_url}").to("stream:out");
    }
 
}
