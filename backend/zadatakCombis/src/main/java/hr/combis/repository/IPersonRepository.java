package hr.combis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.combis.model.Person;

public interface IPersonRepository extends JpaRepository<Person, Long> {
	Person findByFirstNameAndLastNameAndZipCodeAndCityAndPhone(String firstName, String lastName, int zipCode, 
			String city, String phone);
}
