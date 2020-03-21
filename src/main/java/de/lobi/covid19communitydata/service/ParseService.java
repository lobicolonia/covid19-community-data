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
import de.lobi.covid19communitydata.repository.CommunityDataRepository;





@Service
public class ParseService  {
	
	Logger log = LoggerFactory.getLogger(ParseService.class);

	@Autowired
	CommunityDataRepository communityDataRepository;
	
	public void parse() throws Exception {
		
		List<CommunityDataDto> communityDataDtos = new ArrayList<>();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.registerModule(new JavaTimeModule());
		
		XSSFWorkbook xssfWorkbook 
			= new XSSFWorkbook(new FileInputStream(new File("/communitydata.xlsx")));
		
		XSSFSheet xhSheet = xssfWorkbook.getSheetAt(1);
				
		CommunityDataDto communityDataDto = null;
		
		
		for (int i = 0; i < xhSheet.getPhysicalNumberOfRows(); i++) {
			
			XSSFRow row = xhSheet.getRow(i);
			if(row.getCell(0).getStringCellValue().contains("40")) {
//				CommunityDataDto 
				communityDataDto = new CommunityDataDto();
				communityDataDto.setArs(
						row.getCell(2).getStringCellValue().trim() +
						row.getCell(3).getStringCellValue().trim() + 
						row.getCell(4).getStringCellValue().trim() 
						
						);
				communityDataDto.setName(row.getCell(7).getStringCellValue().trim());
				communityDataDtos.add(communityDataDto);
					
					
				}
				if(row.getCell(0).getStringCellValue().contains("60") ) {
					
					communityDataDto.setArea(communityDataDto.getArea() + row.getCell(8).getNumericCellValue());
					communityDataDto.setMalePopulation(communityDataDto.getMalePopulation() + (int)row.getCell(11).getNumericCellValue());
					communityDataDto.setFemalePopulation(communityDataDto.getFemalePopulation() +  (int)row.getCell(12).getNumericCellValue());
					communityDataDto.setPopulationPerSquareKilometer(communityDataDto.getPopulationPerSquareKilometer() +  (int)row.getCell(13).getNumericCellValue());
					
					if(communityDataDto.getLastUpdate() == null) {
						communityDataDto.setLastUpdate((row.getCell(9).getDateCellValue().toInstant()
							      .atZone(ZoneId.systemDefault())
							      .toLocalDate()));
				}
					
			
			}	
		}
		
		communityDataRepository.saveAll(communityDataDtos);
	}

}
