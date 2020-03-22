package de.lobi.covid19communitydata.service;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import de.lobi.covid19communitydata.dto.CommunityDataDto;
import de.lobi.covid19communitydata.dto.CountyDataDto;
import de.lobi.covid19communitydata.repository.CommunityDataRepository;
import de.lobi.covid19communitydata.repository.CountyDataRepository;





@Service
public class ParseService  {
	
	Logger log = LoggerFactory.getLogger(ParseService.class);

	@Autowired
	CommunityDataRepository communityDataRepository;
	
	@Autowired
	CountyDataRepository countyDataRepository;
	
	public void parse() throws Exception {
		
		List<CommunityDataDto> communityDataDtos = new ArrayList<>();
		List<CountyDataDto> countyDataDtos = new ArrayList<>();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.registerModule(new JavaTimeModule());
		
		XSSFWorkbook xssfWorkbook 
			= new XSSFWorkbook(new FileInputStream(new File("/communitydata.xlsx")));
		
		XSSFSheet xhSheet = xssfWorkbook.getSheetAt(1);
				
		
		for (int i = 0; i < xhSheet.getPhysicalNumberOfRows(); i++) {
			
			XSSFRow row = xhSheet.getRow(i);
			if(row.getCell(0).getStringCellValue().contains("40")) {
				
				CountyDataDto countyDataDto = new CountyDataDto();
				countyDataDto.setArs(row.getCell(2).getStringCellValue().trim() +
						row.getCell(3).getStringCellValue().trim() + 
						row.getCell(4).getStringCellValue().trim());
				countyDataDto.setName(row.getCell(7).getStringCellValue().trim());
				countyDataDtos.add(countyDataDto);
			}
			
			if(row.getCell(0).getStringCellValue().contains("60")) {
				CommunityDataDto communityDataDto = new CommunityDataDto();
				communityDataDto.setArs(
						row.getCell(2).getStringCellValue().trim() +
						row.getCell(3).getStringCellValue().trim() + 
						row.getCell(4).getStringCellValue().trim() +
						row.getCell(5).getStringCellValue().trim() +
						row.getCell(6).getStringCellValue().trim() 
						
						);
				communityDataDto.setName(row.getCell(7).getStringCellValue().trim());
				communityDataDto.setArea(row.getCell(8).getNumericCellValue());
				communityDataDto.setMalePopulation((int)row.getCell(11).getNumericCellValue());
				communityDataDto.setFemalePopulation((int)row.getCell(12).getNumericCellValue());
				communityDataDto.setPopulationPerSquareKilometer((int)row.getCell(13).getNumericCellValue());
				communityDataDto.setLastUpdate((row.getCell(9).getDateCellValue().toInstant()
						      .atZone(ZoneId.systemDefault())
						      .toLocalDate()));
				communityDataDto.setLongitude(row.getCell(16).getStringCellValue());
				communityDataDto.setLatitude(row.getCell(15).getStringCellValue());
				
				
				communityDataDtos.add(communityDataDto);
			
			}	
		}
		
		countyDataRepository.saveAll(countyDataDtos);
		communityDataRepository.saveAll(communityDataDtos);
	}

}
