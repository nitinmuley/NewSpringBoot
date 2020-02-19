package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	//we can use GetMapping postmapping etc instead of below
	//like GetMapping(path="/hello-world")
	@RequestMapping(method=RequestMethod.GET, path="/hello-world")
	public String helloWorld() {
		
		return "hello world";
		
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("hello world bean");
		
	}
	
	@RequestMapping(method=RequestMethod.GET , path="/hello-world/path-varibale/{name}")
	public  HelloWorldBean helloWorldBeanPath(@PathVariable String name) {
		return new HelloWorldBean(String.format("my parameter is ,%s", name));
	}
	
}
