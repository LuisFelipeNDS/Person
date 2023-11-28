package com.person.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.person.exceeptions.ResourceNotFoundExceeption;
import com.person.model.Person;
import com.person.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository personRepository;

	public List<Person> findAll() {

		logger.info("Findind all people");

		return personRepository.findAll();
	}

	public Person findById(Long id) {

		logger.info("Findind one person");

		return personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceeption("No records found for this ID"));
	}

	public Person create(Person person) {

		logger.info("Creating one person");
		return personRepository.save(person);
	}

	public Person update(Person person) {

		logger.info("Updating one person");

		var newPerson = personRepository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundExceeption("No records found for this ID"));

		newPerson.setFirstName(person.getFirstName());
		newPerson.setLastName(person.getLastName());
		newPerson.setAdress(person.getAdress());
		newPerson.setGender(person.getGender());

		return personRepository.save(newPerson);
	}

	public void delete(Long id) {

		logger.info("Deleting one person");

		var personDelete = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceeption("No records found for this ID"));

		personRepository.delete(personDelete);

	}

}
