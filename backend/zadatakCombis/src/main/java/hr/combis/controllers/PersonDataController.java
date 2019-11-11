package hr.combis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.combis.exceptions.BusinessInfrastructureException;
import hr.combis.model.Person;
import hr.combis.model.RestResponse;
import hr.combis.service.IPersonService;

@RestController
@RequestMapping("/persons")
public class PersonDataController {
	@Autowired
	IPersonService personService;
	
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public ResponseEntity<RestResponse> loadData() throws BusinessInfrastructureException {
		List<Person> persons = personService.loadDataFromFile();
		RestResponse response = new RestResponse();
		response.setData(persons);
		return new ResponseEntity<RestResponse>(response, HttpStatus.OK);
	}
}