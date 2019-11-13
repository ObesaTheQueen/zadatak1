package hr.combis.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import hr.combis.common.ObjectUtil;
import hr.combis.enums.ERROR_CODE;
import hr.combis.exceptions.BusinessInfrastructureException;
import hr.combis.model.Document;
import hr.combis.model.Person;
import hr.combis.repository.IPersonRepository;

@Service
public class PersonServiceImpl implements IPersonService {

	@Value("${app.file.person}")
	private String personPath;

	@Autowired
	MessageSourceAccessor message;
	
	@Autowired
	IPersonRepository iPersonRepository;
	Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
	
	
	public Document loadDataFromFile() throws BusinessInfrastructureException{
		return loadPersonDataFile(false, null);
	}
	/**
	 *dohvat podataka iz datoteke
	 */
	public synchronized Document loadPersonDataFile(boolean save, String saveFileHash) throws BusinessInfrastructureException{
        String line = "";
        String cvsSplitBy = ";";
        ArrayList<Person> persons = new ArrayList<Person>();
        Document doc = null;
        String hashString="";
        try (        	
        		BufferedReader br = 
        		new BufferedReader(new InputStreamReader(new FileInputStream(personPath), "Cp1250"));){

            while ((line = br.readLine()) != null) {
            	hashString+=line;
                String[] personRow = line.split(cvsSplitBy);
                
                Person person = new Person();
                
                if(personRow.length > 0 && !StringUtils.isEmpty(personRow[0]) ) {
                	person.setFirstName(personRow[0]);
                	if(!ObjectUtil.isLenghtOk(personRow[0], 50))
                		addErrorMsg(person, message.getMessage(ERROR_CODE.ERR_TOO_LONG.getErrorCode(), new Object[] {"ime"}));
                }else 
                	addErrorMsg(person, message.getMessage(ERROR_CODE.ERR_MANDATORY.getErrorCode(), new Object[] {"ime"}));
                
                
                if(personRow.length > 1 && !StringUtils.isEmpty(personRow[1])) {
                	person.setLastName(personRow[1]);
                	if(!ObjectUtil.isLenghtOk(personRow[1], 50))
                		addErrorMsg(person, message.getMessage(ERROR_CODE.ERR_TOO_LONG.getErrorCode(), new Object[] {"prezime"}));
                }else
                	addErrorMsg(person, message.getMessage(ERROR_CODE.ERR_MANDATORY.getErrorCode(), new Object[] {"prezime"}));
                
                
                if(personRow.length > 2 && !StringUtils.isEmpty(personRow[2])) {
                	person.setZipCodeString(personRow[2]);
                	if(!checkZip(personRow[2]))
                		addErrorMsg(person, message.getMessage(ERROR_CODE.ERR_WRONG_ZIP_CODE.getErrorCode()));
                	else
                		person.setZipCode(Integer.valueOf(personRow[2]));
                	
                	if(!ObjectUtil.isLenghtOk(personRow[2], 10))
                		addErrorMsg(person, message.getMessage(ERROR_CODE.ERR_TOO_LONG.getErrorCode(), new Object[] {"poštanski broj"}));
                }else
                	addErrorMsg(person, message.getMessage(ERROR_CODE.ERR_MANDATORY.getErrorCode(), new Object[] {"poštanski broj"}));
                
                
                if(personRow.length > 3 && !StringUtils.isEmpty(personRow[3])) {
                	person.setCity(personRow[3]);
                	if(!ObjectUtil.isLenghtOk(personRow[3], 50))
                		addErrorMsg(person, message.getMessage(ERROR_CODE.ERR_TOO_LONG.getErrorCode(), new Object[] {"grad"}));
                }else
                	addErrorMsg(person, message.getMessage(ERROR_CODE.ERR_MANDATORY.getErrorCode(), new Object[] {"grad"}));
                
                
                if(personRow.length > 4 && !StringUtils.isEmpty(personRow[4])) {
                	person.setPhone(personRow[4]);
                	if(!ObjectUtil.isLenghtOk(personRow[4], 30))
                		addErrorMsg(person, message.getMessage(ERROR_CODE.ERR_TOO_LONG.getErrorCode(), new Object[] {"tel. broj"}));
                }
                
                if(CollectionUtils.isEmpty(person.getErrorMsgs())){
	                Person oldPerson = iPersonRepository.findByFirstNameAndLastNameAndZipCodeAndCityAndPhone(
		                		person.getFirstName(), person.getLastName(), person.getZipCode(), person.getCity(), person.getPhone());
	                if(oldPerson != null) {
	                	addErrorMsg(person, message.getMessage(ERROR_CODE.ERR_ROW_EXISTS.getErrorCode()));
	                }
                }
                
                persons.add(person);

            }
            doc = new Document();
            doc.setPersons(persons);
            String currDocHash = DigestUtils.md5Hex( hashString );
            doc.setHash( currDocHash);
           
            if(save) {
            	if(currDocHash.equals(saveFileHash)) {
		            for(Person person:doc.getPersons()) {
		    			if(CollectionUtils.isEmpty(person.getErrorMsgs()) ) {
		    				Person oldPerson = iPersonRepository.findByFirstNameAndLastNameAndZipCodeAndCityAndPhone(
				                		person.getFirstName(), person.getLastName(), person.getZipCode(), person.getCity(), person.getPhone());
		    				
		    				
			                if(oldPerson == null) {
			                	iPersonRepository.save(person);
			                }
		    			}
		    				
		    		}
            	}else
            		throw new BusinessInfrastructureException("Podaci su u međuvremenu promijenjeni. Učitajte podatke ponovo.");
            }
            
        } catch (FileNotFoundException e) {
        	logger.error("Dogodila se greška kod dohvata datoteke", e);
            throw new BusinessInfrastructureException("Datoteka ne postoji u sustavu.");
        } catch (IOException e) {
        	logger.error("Dogodila se greška kod dohvata datoteke", e);
        	throw new BusinessInfrastructureException("Dogodila se greška kod dohvata datoteke.");
        } finally {
           
        }    
		
		return doc;
	}
	
	public void addErrorMsg(Person person, String msg) {
		if(CollectionUtils.isEmpty(person.getErrorMsgs())){
			person.setErrorMsgs(new ArrayList<String>());
			person.getErrorMsgs().add(msg);
		}else
			person.getErrorMsgs().add(msg);
	}
	
	public boolean checkZip(String zipCodeString) {
		try {
			Integer zipCode = Integer.valueOf(zipCodeString);
			return true;
		}catch(NumberFormatException ex) {
			return false;

		}
	}
	
	
}
