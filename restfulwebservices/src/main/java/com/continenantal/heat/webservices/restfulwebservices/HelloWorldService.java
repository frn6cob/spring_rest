package com.continenantal.heat.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.continenantal.heat.webservices.restfulwebservices.beans.HelloWorldBean;

@RestController
public class HelloWorldService {

	@GetMapping(path="/hello-world")
	public String getHelloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean getHelloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping(path = "/hello-world-bean/{pathVar}")
	public HelloWorldBean HelloWorldPathVariable(@PathVariable String pathVar) {
		return new HelloWorldBean(String.format("hello world , %s", pathVar));		
	}
	
}
