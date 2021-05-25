package com.continenantal.heat.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.continenantal.heat.webservices.restfulwebservices.beans.Name;
import com.continenantal.heat.webservices.restfulwebservices.beans.Person1;
import com.continenantal.heat.webservices.restfulwebservices.beans.Person2;

@RestController
public class PersonConroller {
	
	//using req param

	@GetMapping(value= "/person", params="v=1")
	public Person1 getPerson1UsingReqParam() {
		return new Person1("nithin");
	}
	
	@GetMapping(value= "/person", params="v=2")
	public Person2 getPerson2UsingReqParam() {
		return new Person2(new Name("nithin","francis"));
	}
	
	//using header

	@GetMapping(value= "/person", headers="X-API-VERSION=1")
	public Person1 getPerson1UsingHeaderParam() {
		return new Person1("nithin");
	}
	
	@GetMapping(value= "/person", headers="X-API-VERSION=2")
	public Person2 getPerson2UsingHeaderParam() {
		return new Person2(new Name("nithin","francis"));
	}
	
	//using content nego

	@GetMapping(value= "/person", produces="application/vnd.company.app-v1+json")
	public Person1 getPerson1UsingCN() {
		return new Person1("nithin");
	}
	
	@GetMapping(value= "/person", produces="application/vnd.company.app-v2+json")
	public Person2 getPerson2UsingCN() {
		return new Person2(new Name("nithin","francis"));
	}
	
	
}
