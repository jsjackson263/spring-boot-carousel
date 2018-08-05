package info.jsjackson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class PhotoApplication extends SpringBootServletInitializer {

	
	public static void main(String[] args) {
		SpringApplication.run(PhotoApplication.class, args);
	}
	
	
	/*
	 * convert a Spring Boot application for App Engine Standard - required, according to 
	 * https://github.com/GoogleCloudPlatform/getting-started-java/tree/master/appengine-standard-java8/springboot-appengine-standard
	 */
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PhotoApplication.class);
	}*/
	
	
}
