package hr.combis.service;

import hr.combis.exceptions.BusinessInfrastructureException;
import hr.combis.model.Document;

public interface IPersonService {

	public Document loadPersonDataFile(boolean save, String saveFileHash) throws BusinessInfrastructureException;
	public Document loadDataFromFile() throws BusinessInfrastructureException;
}
