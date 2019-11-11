package hr.combis.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import hr.combis.enums.ERROR_CODE;
import hr.combis.exceptions.BusinessInfrastructureException;
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
	
	public List<Person> loadDataFromFile() throws BusinessInfrastructureException{
	 	BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        ArrayList<Person> persons = new ArrayList<Person>();
        try {

            br = new BufferedReader(new InputStreamReader(new FileInputStream(personPath), "Cp1250"));
            while ((line = br.readLine()) != null) {

                String[] personRow = line.split(cvsSplitBy);
                //napuniti beanove)
                Person person = new Person();
                if(!StringUtils.isEmpty(personRow[0]))
                	person.setFirstName(personRow[0]);
                else
                	person.addErrorMsg(message.getMessage(ERROR_CODE.ERR_MANDATORY.getErrorCode(), new Object[] {"ime"}));
                
                if(!StringUtils.isEmpty(personRow[1]))
                	person.setLastName(personRow[1]);
                else
                	person.addErrorMsg(message.getMessage(ERROR_CODE.ERR_MANDATORY.getErrorCode(), new Object[] {"prezime"}));
                
                if(!StringUtils.isEmpty(personRow[2]))
                	person.setZipCodeString(personRow[2],message.getMessage(ERROR_CODE.ERR_WRONG_ZIP_CODE.getErrorCode()));
                else
                	person.addErrorMsg(message.getMessage(ERROR_CODE.ERR_MANDATORY.getErrorCode(), new Object[] {"poštanski broj"}));
                
                if(!StringUtils.isEmpty(personRow[3]))
                	person.setCity(personRow[3]);
                else
                	person.addErrorMsg(message.getMessage(ERROR_CODE.ERR_MANDATORY.getErrorCode(), new Object[] {"grad"}));
                
                if(!StringUtils.isEmpty(personRow[4]))
                	person.setPhone(personRow[4]);
                
                if(CollectionUtils.isEmpty(person.getErrorMsgs())) {
	                Person oldPerson = iPersonRepository.findByFirstNameAndLastNameAndZipCodeAndCityAndPhone(
	                		person.getFirstName(), person.getLastName(), person.getZipCode(), person.getCity(), person.getPhone());
	                if(oldPerson != null)
	                	person.setExists(true);
	                else {
	                	iPersonRepository.save(person);
	                }
                }
                persons.add(person);

            }
            
        } catch (FileNotFoundException e) {
        	logger.error("Dogodila se greška kod dohvata datoteke", e);
            throw new BusinessInfrastructureException("Dogodila se greška kod dohvata datoteke.");
        } catch (IOException e) {
        	logger.error("Dogodila se greška kod dohvata datoteke", e);
        	throw new BusinessInfrastructureException("Dogodila se greška kod dohvata datoteke.");
        } catch (Exception ex){
        	logger.error("Dogodila se greška u sustavu", ex);
        	throw new BusinessInfrastructureException("Dogodila se greška u sustavu.");
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }    
		
		return persons;
	}

	
	
}