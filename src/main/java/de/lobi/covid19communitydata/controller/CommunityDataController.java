package de.lobi.covid19communitydata.controller;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import de.lobi.covid19communitydata.dto.CommunityDataDto;
import de.lobi.covid19communitydata.repository.CommunityDataRepository;
import de.lobi.covid19communitydata.service.ParseService;

//import de.lobi.covidrest.dto.CommunityDataDto;

@Controller
public class CommunityDataController {
	
	@Autowired
	ParseService parseService;
	
	@Autowired
	CommunityDataRepository communityDataRepository;

	@GetMapping("/communityData")
	public ResponseEntity<Collection<CommunityDataDto>> getCommunityData() {
		
		return ResponseEntity.ok(communityDataRepository.findAll());
		}
	
	@PostConstruct
	public void init() throws Exception {
		parseService.parse();
	}
}
