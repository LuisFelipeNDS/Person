package com.person.services;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.person.model.Person;

@Service
public class PersonServices {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public Person findById(String id) {
		
		logger.info("Findind one person");
		Person person = new Person();
		
		person.setId(counter.incrementAndGet());
		person.setFirstName("Luis");
		person.setLastName("Neves");
		person.setAdress("Orindiuva");
		person.setGender("Male");
		
		return person;
	}
	

}
