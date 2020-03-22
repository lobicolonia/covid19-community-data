package de.lobi.covid19communitydata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.lobi.covid19communitydata.dto.CountyDataDto;

@Repository
public interface CountyDataRepository extends JpaRepository<CountyDataDto, Integer>{

}
