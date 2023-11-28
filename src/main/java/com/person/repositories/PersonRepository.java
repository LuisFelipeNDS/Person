package com.person.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.person.model.Person;

//Classe (Interface) que peermite o acesso ao BD
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
