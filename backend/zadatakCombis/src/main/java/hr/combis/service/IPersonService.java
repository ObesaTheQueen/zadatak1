package hr.combis.service;

import java.util.List;

import hr.combis.exceptions.BusinessInfrastructureException;
import hr.combis.model.Person;

public interface IPersonService {

	public List<Person> loadDataFromFile() throws BusinessInfrastructureException;
}
