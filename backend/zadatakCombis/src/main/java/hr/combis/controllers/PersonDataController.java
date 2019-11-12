package hr.combis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.combis.exceptions.BusinessInfrastructureException;
import hr.combis.model.Document;
import hr.combis.model.RestResponse;
import hr.combis.service.IPersonService;

@RestController
@RequestMapping("/persons")
public class PersonDataController {
	@Autowired
	IPersonService personService;
	
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public ResponseEntity<RestResponse> loadData() throws BusinessInfrastructureException {
		Document document = personService.loadDataFromFile();
		RestResponse response = new RestResponse();
		response.setData(document);
		return new ResponseEntity<RestResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<RestResponse> saveData(@RequestBody Document document) throws BusinessInfrastructureException {
		Document doc = personService.loadDataFromFile(true, document.getHash());
		RestResponse response = new RestResponse();
		response.setData(doc);
		return new ResponseEntity<RestResponse>(response, HttpStatus.OK);
	}
}
