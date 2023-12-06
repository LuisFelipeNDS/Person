package com.person.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.person.data.vo.v1.PersonVO;
import com.person.exceeptions.ResourceNotFoundExceeption;
import com.person.mapper.DozerMapper;
import com.person.model.Person;
import com.person.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository personRepository;

	public List<PersonVO> findAll() {

		logger.info("Findind all people");

		return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
	}

	public PersonVO findById(Long id) {

		logger.info("Findind one person");

		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceeption("No records found for this ID"));
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}

	public PersonVO create(PersonVO person) {

		logger.info("Creating one person");
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class)	;
		
		return vo;
	}

	public PersonVO update(PersonVO person) {

		logger.info("Updating one person");

		var newPerson = personRepository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundExceeption("No records found for this ID"));

		newPerson.setFirstName(person.getFirstName());
		newPerson.setLastName(person.getLastName());
		newPerson.setAdress(person.getAdress());
		newPerson.setGender(person.getGender());

		var vo = DozerMapper.parseObject(personRepository.save(newPerson), PersonVO.class)	;
		
		return vo;
	}

	public void delete(Long id) {

		logger.info("Deleting one person");

		var personDelete = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceeption("No records found for this ID"));

		personRepository.delete(personDelete);

	}

}
