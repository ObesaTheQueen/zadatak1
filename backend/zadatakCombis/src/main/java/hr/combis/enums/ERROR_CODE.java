package hr.combis.enums;

public enum ERROR_CODE {

	ERR_WRONG_ZIP_CODE("err.zip.format"),
	ERR_MANDATORY("err.mandatory.field"),
	ERR_ROW_EXISTS("err.row.exists");
	
	String errorCode;
	
	ERROR_CODE(String error){
		this.errorCode = error;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorMsg) {
		this.errorCode = errorMsg;
	}
	
}
