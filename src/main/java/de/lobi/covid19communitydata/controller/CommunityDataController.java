package de.lobi.covid19communitydata.controller;

import java.util.Collection;
//import java.util.stream.Collectors;
import java.util.Comparator;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.lobi.covid19communitydata.Helper.GeoHelper;
import de.lobi.covid19communitydata.dto.CommunityDataDto;
import de.lobi.covid19communitydata.dto.CummulatedCommunityData;
import de.lobi.covid19communitydata.repository.CommunityDataRepository;
import de.lobi.covid19communitydata.service.ParseService;

//import de.lobi.covidrest.dto.CommunityDataDto;

@Controller
public class CommunityDataController {
	
	Logger log = LoggerFactory.getLogger(CommunityDataController.class);
	
	@Autowired
	ParseService parseService;
	
	@Autowired
	CommunityDataRepository communityDataRepository;

	@GetMapping("/communityData")
	public ResponseEntity<Collection<CommunityDataDto>> getCommunityData() {
		
		return ResponseEntity.ok(communityDataRepository.findAll()
//				.stream().filter(a -> a.getArs().length() == 5).collect(Collectors.toList())
				);
	}
	
	@GetMapping("/cumulatedCommunityData")
	public ResponseEntity<Collection<CummulatedCommunityData>> getCummulatedCommunityData() {
		
		return ResponseEntity.ok(communityDataRepository.getCummulatedCommunityData()
//				.stream().filter(a -> a.getArs().length() == 5).collect(Collectors.toList())
				);
	}
	
	@GetMapping("/cumulatedCommunityDataByGeoCoordinates")
	public ResponseEntity<String> getCummulatedByGeoCoordinates(@RequestParam(name = "latitude") String latitude, @RequestParam(name = "longitude") String longitude ) {
		
		String ags = "";
		double distance = 9999999999999.0;
		
		Collection<CommunityDataDto> communityDataDtos = communityDataRepository.findAll();
		for (CommunityDataDto communityDataDto2 : communityDataDtos) {
			
			log.info("ags: " +  communityDataDto2.getArs());
			log.info("lon: " + communityDataDto2.getLatitude());
			log.info("lat " + communityDataDto2.getLongitude());
			
			if(!communityDataDto2.getLatitude().isEmpty() && !communityDataDto2.getLongitude().isEmpty()) {
				double tmp = GeoHelper.distanceInKm(Double.parseDouble(communityDataDto2.getLatitude().replace(",", ".")),
					Double.parseDouble(communityDataDto2.getLongitude().replace(",", ".")), 
							Double.parseDouble(longitude.replace(",", ".")),
									Double.parseDouble(latitude.replace(",", ".")));
				if(tmp < distance) {
					ags = communityDataDto2.getArs();
					distance = tmp;
				}
			}
			
			
		}
				
		return ResponseEntity.ok(ags);
	}
	
	
	@PostConstruct
	public void init() throws Exception {
		parseService.parse();
	}
}
