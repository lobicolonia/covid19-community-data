package de.lobi.covid19communitydata.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CountyDataDto {

	@Id
	private String ars;
	private String name;
	
	public String getArs() {
		return ars;
	}
	public void setArs(String ars) {
		this.ars = ars;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
