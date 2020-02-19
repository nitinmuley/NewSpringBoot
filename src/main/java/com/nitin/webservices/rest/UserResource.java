package com.nitin.webservices.rest;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class UserResource {
   
	@Autowired
	private UserDaoService service;
	
	
	@RequestMapping(method=RequestMethod.GET, path="/users")
	public List<User> retrieveAllUsers(){
		return service.finAll(); 	
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/users/{id}" , produces = { "application/json", "application/xml" })
	public Resource<User> retriveUser(@PathVariable int id) {
		
		User user=service.findOne(id);
		if(user==null)
			throw new UserNotfoundException("id -"+id);
		
		//adding code for HATEOAS
		
		Resource<User> resource= new Resource<User>(user);
		//adding link for retriveAllUsers()
		ControllerLinkBuilder linkto=linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkto.withRel("all-users"));
		
		return resource;
	}
	
	@RequestMapping(method=RequestMethod.POST,path="/users")
	public ResponseEntity<Object> createNewUser(@Valid @RequestBody User user) { 	 	
		User savedUser=service.save(user);
		//below lines used to add newly created request url and status code
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(method=RequestMethod.DELETE, path="/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
		User user=service.deleteUserById(id);
		if(user==null)
			throw new UserNotfoundException("id -"+id);

	}
	
	
	
}
 
