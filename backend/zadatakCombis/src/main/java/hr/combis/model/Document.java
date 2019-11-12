package hr.combis.model;

import java.util.List;

import lombok.Data;
@Data
public class Document {

	List<Person> persons;
	String hash;
}
