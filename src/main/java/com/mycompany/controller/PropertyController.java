package com.mycompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.dto.PropertyDTO;
import com.mycompany.service.PropertyService;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {
	
	@Value("${pms.dummy}")
	private String dummy;
	
	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Autowired
	private PropertyService propertyService;

	// http://localhost:8080/api/v1/properties
	@PostMapping("/properties")
	public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO) {
		propertyDTO = propertyService.saveProperty(propertyDTO);
		ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping(value = "/properties")
	public ResponseEntity<List<PropertyDTO>> getAllProperties() {
		System.out.println(dummy);
		System.out.println(dbUrl);
		
		List<PropertyDTO> listProperties = propertyService.listAllProperty();
		ResponseEntity<List<PropertyDTO>> requestEntity = new ResponseEntity<>(listProperties, HttpStatus.OK);
		return requestEntity;
	}

	@PutMapping(value = "/properties/{id}")
	public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDto,
			@PathVariable(value = "id") Long propertyId) {

		PropertyDTO pdto = propertyService.updateProperty(propertyDto, propertyId);
		ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(pdto, HttpStatus.OK);

		return responseEntity;

	}

	@PatchMapping(value = "/properties/update-description/{id}")
	public ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO propertyDto,
			@PathVariable(value = "id") Long propertyId) {
		PropertyDTO pdto = propertyService.updatePropertyDescription(propertyDto, propertyId);
		ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(pdto, HttpStatus.OK);

		return responseEntity;

	}

	@PatchMapping(value = "/properties/update-price/{id}")
	public ResponseEntity<PropertyDTO> updatePropertyPrice(@RequestBody PropertyDTO propertyDto,
			@PathVariable(value = "id") Long propertyId) {
		PropertyDTO pdto = propertyService.updatePropertyPrice(propertyDto, propertyId);
		ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(pdto, HttpStatus.OK);

		return responseEntity;
	}
	
	@DeleteMapping(value="/properties/{id}")
	public ResponseEntity<Void> deleteProperty(@PathVariable(value="id") Long propertyID) {
		propertyService.deleteProperty(propertyID);
		ResponseEntity<Void> responseEntity=new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		return responseEntity;
	}
	
	
}
