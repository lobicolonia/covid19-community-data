package de.lobi.covid19communitydata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.lobi.covid19communitydata.dto.CommunityDataDto;
import de.lobi.covid19communitydata.dto.CummulatedCommunityData;
import de.lobi.covid19communitydata.dto.CountyDataDto;


@Repository
public interface CommunityDataRepository extends JpaRepository<CommunityDataDto, Integer> {
	
	@Query("select new de.lobi.covid19communitydata.dto.CummulatedCommunityData"
			+ "(cdd.ars,cdd.name,SUM(cda.area),SUM(cda.malePopulation),SUM(cda.femalePopulation),SUM(cda.populationPerSquareKilometer),MIN(cda.lastUpdate)) "
			+ " from CommunityDataDto cda "
			+ " inner join CountyDataDto cdd on cdd.ars = SUBSTRING(cda.ars,1,5)"
			+ " group by cdd.ars") 
	public List<CummulatedCommunityData> getCummulatedCommunityData();

}
