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


@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
class Application extends FatJarRouter {
	
	
	public static void main(String... args) {
		ApplicationContext applicationContext = new SpringApplication(Application.class).run(args);
		CamelSpringBootApplicationController applicationController =
				applicationContext.getBean(CamelSpringBootApplicationController.class);
		applicationController.run();
	}
 
    @Override
    public void configure() throws Exception {
        from("netty4-http:http://0.0.0.0:18080").
            setBody().simple("ref:helloWorld");
    }
 
    @Bean
    String helloWorld() {
        return "helloWorld\n";
    }
 
}
