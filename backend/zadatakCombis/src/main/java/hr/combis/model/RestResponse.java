package hr.combis.model;

import java.util.List;

import lombok.Data;
import hr.combis.model.Error;

@Data
public class RestResponse {
	
	private List<Person> data;
	private Error error;

}
