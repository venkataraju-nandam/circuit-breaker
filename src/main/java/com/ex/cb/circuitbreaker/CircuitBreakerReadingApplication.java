package com.ex.cb.circuitbreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/*
 *  http://localhost:8091/to-read
 *  This returns the response from other microservice application running at port 8090, 
 *  and can test at  url:  http://localhost:8090/recommended
 *  And as soon as we stop the book microservice CircuitBreakerBookstoreApplication.java, 
 *  this routes to the default fallbackMethod = "reliable", and returns response from there.
 *   Once CircuitBreakerBookstoreApplication up and running again, it returns the response from other application.
 */

@EnableCircuitBreaker
@RestController
@SpringBootApplication
public class CircuitBreakerReadingApplication {

	  @Autowired
	  private BookService bookService;

	  @Bean
	  public RestTemplate rest(RestTemplateBuilder builder) {
	  return builder.build();
	  }

	  @RequestMapping("/to-read")
	  public String toRead() {
	  return bookService.readingList();
	  }

	  public static void main(String[] args) {
	  SpringApplication.run(CircuitBreakerReadingApplication.class, args);
	  }
	
}


