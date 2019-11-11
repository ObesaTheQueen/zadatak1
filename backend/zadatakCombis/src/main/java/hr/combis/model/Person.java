package hr.combis.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.util.CollectionUtils;

import hr.combis.enums.ERROR_CODE;
import lombok.Data;

@Data
@Entity
@Table(name = "PERSON")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "PERSON_ID", nullable = false)
	private Long personId;
	
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;
	@Column(name = "ZIP_CODE", nullable = false)
	private Integer zipCode;
	
	@Transient
	private String zipCodeString;
	
	@Column(name = "CITY", nullable = false)
	private String city;
	@Column(name = "PHONE", nullable = true)
	private String phone;
	
	@Column(name = "LOCK_TSTMP", insertable=false)
	private Timestamp lockTstmp;
	
	@Transient
	private List<String> errorMsgs;
	
	@Transient
	private boolean exists = false;

	public void setZipCodeString(String zipCodeString, String errorMsg) {
		Integer zipCode;
		try {
			zipCode = Integer.valueOf(zipCodeString);
			this.setZipCode(zipCode);
		}catch(NumberFormatException ex) {
			addErrorMsg(errorMsg);

		}
	}
	
	public void addErrorMsg(String msg) {
		if(CollectionUtils.isEmpty(errorMsgs)){
			errorMsgs = new ArrayList<String>();
			errorMsgs.add(msg);
		}else
			errorMsgs.add(msg);
	}
}