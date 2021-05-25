package com.continenantal.heat.webservices.restfulwebservices;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.continenantal.heat.webservices.restfulwebservices.beans.User;
import com.continenantal.heat.webservices.restfulwebservices.daoservice.UserDAOService;
import com.continenantal.heat.webservices.restfulwebservices.exception.UserNotFoundException;

@RestController
public class UserResource {
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
    UserDAOService userDaoService;
	
	@GetMapping("/users")
	public List<User> users(){
		return userDaoService.users();
	}

	@GetMapping("/welcome")
	public String welcomeUserMessage(){
		return messageSource.getMessage("welcome.message", null, LocaleContextHolder.getLocale());
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> getUserById(@PathVariable int id) throws UserNotFoundException {
		User user = userDaoService.getUser(id);
		EntityModel<User> resource = EntityModel.of(user);
		
		if(user == null) {
			//throw exception
			throw new UserNotFoundException("User couldnt be found");
		}
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).users());
		resource.add(link.withRel("all-users"));
		
		return resource;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createNewUser(@Valid @RequestBody User user) {
		
		userDaoService.saveUser(user);
		
		//we need to send 'CREATED' status
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(user.getId()).toUri();
		//also the URI to the created object
		
		return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable int id) throws UserNotFoundException {
		User user =  this.userDaoService.deleteUserById(id);
		if(user == null) {
			throw new UserNotFoundException("Attempted deletion of non existing user");
		}
		
	}
}
