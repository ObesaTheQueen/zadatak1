package hr.combis.model;

import lombok.Data;

@Data
public class RestResponse {
	
	private Document data;
	private Error error;

}
