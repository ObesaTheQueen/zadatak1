package hr.combis;

import javax.persistence.Transient;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import hr.combis.exceptions.BusinessInfrastructureException;
import hr.combis.model.Document;
import hr.combis.repository.IPersonRepository;
import hr.combis.service.IPersonService;

//@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
class ApplicationTests {
	@Autowired
	IPersonRepository iPersonRepository;
	
	@Autowired
	IPersonService personService;
	
	@Autowired
	@Transient
	MessageSourceAccessor messages;

	
//	@Test
//	public void findPerson() {
//		Person person = iPersonRepository.findByFirstNameAndLastNameAndZipCodeAndCityAndPhone(
//				"aa", "aa", 10000, "aa", "aa");
//		assert(person != null);
//	}
//	
//	@Test
//	public void findPersonNoPhone() {
//		Person person = iPersonRepository.findByFirstNameAndLastNameAndZipCodeAndCityAndPhone(
//				"bb", "bb", 10000, "aa", null);
//		assert(person != null);
//	}
//	
	@Test
	public void loadPersonData() throws BusinessInfrastructureException {
		Document persons = personService.loadDataFromFile();
		System.out.println();
		assert(!CollectionUtils.isEmpty(persons.getPersons()));
	}
	


}
