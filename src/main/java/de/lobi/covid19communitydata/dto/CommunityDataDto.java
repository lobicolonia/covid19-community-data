package de.lobi.covid19communitydata.dto;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class CommunityDataDto {
	
	@Id
	private String ars;
	private String name;
	private double area;
	private int malePopulation;
	private int femalePopulation;
	private int populationPerSquareKilometer;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate lastUpdate;
	
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
	public int getMalePopulation() {
		return malePopulation;
	}
	public void setMalePopulation(int malePopulation) {
		this.malePopulation = malePopulation;
	}
	
	@JsonProperty("population_female")
	public int getFemalePopulation() {
		return femalePopulation;
	}
	public void setFemalePopulation(int femalePopulation) {
		this.femalePopulation = femalePopulation;
	}
	
	@JsonProperty("population_density_km")
	public int getPopulationPerSquareKilometer() {
		return populationPerSquareKilometer;
	}
	public void setPopulationPerSquareKilometer(int populationPerSquareKilometer) {
		this.populationPerSquareKilometer = populationPerSquareKilometer;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public LocalDate getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(LocalDate lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	@Override
	public String toString() {
		return "CommunityDataDto [ars=" + ars + ", name=" + name + ", area=" + area + ", malePopulation="
				+ malePopulation + ", femalePopulation=" + femalePopulation + ", populationPerSquareKilometer="
				+ populationPerSquareKilometer + ", lastUpdate=" + lastUpdate + "]";
	}
	
	
	
}
