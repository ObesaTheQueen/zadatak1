package hr.combis.model;

import lombok.Data;

@Data	
public class Error {
	
	private String errorId;
	private String errorMsg;
	
	public Error(String pErrorId, String pErrorMsg) {
		this.errorId = pErrorId;
		this.errorMsg =pErrorMsg ;
	}

	
}
