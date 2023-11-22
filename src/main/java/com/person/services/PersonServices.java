package com.person.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.person.model.Person;

@Service
public class PersonServices {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	public List<Person> findAll() {

		logger.info("Findind all people");	
		List<Person> persons = new ArrayList<>();

		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}

		return persons;
	}

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

	private Person mockPerson(int i) {
		
		Person person = new Person();

		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name " + i);
		person.setLastName("lastName " + i);
		person.setAdress("Some adress in Brasil " + i);
		person.setGender("Male");

		return person;
	}

}
