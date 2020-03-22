package de.lobi.covid19communitydata.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CummulatedCommunityData {

	private String ars;
	private String name;
	private double area;
	private long malePopulation;
	private long femalePopulation;
	private long populationPerSquareKilometer;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate lastUpdate;
	
	public CummulatedCommunityData() {
		
	};
	
	
	public CummulatedCommunityData(String ars, String name, double area, long malePopulation, long femalePopulation,
			long populationPerSquareKilometer, LocalDate lastUpdate) {
//		super();
		this.ars = ars;
		this.name = name;
		this.area = area;
		this.malePopulation = malePopulation;
		this.femalePopulation = femalePopulation;
		this.populationPerSquareKilometer = populationPerSquareKilometer;
		this.lastUpdate = lastUpdate;
	}
	@JsonProperty("ags")
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
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	
	@JsonProperty("population_male")
	public long getMalePopulation() {
		return malePopulation;
	}
	public void setMalePopulation(long malePopulation) {
		this.malePopulation = malePopulation;
	}
	
	@JsonProperty("population_female")
	public long getFemalePopulation() {
		return femalePopulation;
	}
	public void setFemalePopulation(long femalePopulation) {
		this.femalePopulation = femalePopulation;
	}
	
	@JsonProperty("population_density_km")
	public long getPopulationPerSquareKilometer() {
		return populationPerSquareKilometer;
	}
	public void setPopulationPerSquareKilometer(long populationPerSquareKilometer) {
		this.populationPerSquareKilometer = populationPerSquareKilometer;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public LocalDate getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(LocalDate lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	

}


