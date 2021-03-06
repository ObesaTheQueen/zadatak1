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
import javax.validation.constraints.Size;

import org.springframework.util.CollectionUtils;

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
	@Size(max=50)
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

}
