package de.lobi.covid19communitydata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.lobi.covid19communitydata.dto.CommunityDataDto;


@Repository
public interface CommunityDataRepository extends JpaRepository<CommunityDataDto, Integer> {

}
