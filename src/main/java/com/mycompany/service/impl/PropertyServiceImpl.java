package com.mycompany.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.converter.PropertyConverter;
import com.mycompany.dto.PropertyDTO;
import com.mycompany.entity.PropertyEntity;
import com.mycompany.repository.PropertyRepository;
import com.mycompany.service.PropertyService;

//Esta clase debe ser un objeto singleton (se instancia un solo objeto),
//Esto se puede conseguir con @Service, @Repository, @Component, @Configuration
@Service
public class PropertyServiceImpl implements PropertyService {
	
	@Autowired
	private PropertyRepository propertyRepository;
	
	@Autowired
	private PropertyConverter propertyConverter;

	@Override
	public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
		
		PropertyEntity pe= propertyConverter.convertDTOtoEntity(propertyDTO);
		propertyRepository.save(pe);
		
		propertyDTO = propertyConverter.convertEntityToDTO(pe);
		return propertyDTO;
	}

	
	@Override
	public List<PropertyDTO> listAllProperty() {
		
		List<PropertyEntity> listPropertyEntity = (List<PropertyEntity>)propertyRepository.findAll();
		List<PropertyDTO> listPropertyDTO = new ArrayList<>();
		
		for(PropertyEntity propertyEntity:listPropertyEntity) {
			PropertyDTO propertyDTO=propertyConverter.convertEntityToDTO(propertyEntity);
			listPropertyDTO.add(propertyDTO);
		}
		return listPropertyDTO;
		
	}


	@Override
	public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
		Optional<PropertyEntity> optEn= propertyRepository.findById(propertyId);
		PropertyDTO dto = null;
		if(optEn.isPresent()) {
			PropertyEntity pe = optEn.get();
			
			pe.setTitle(propertyDTO.getTitle());
			pe.setAddress(propertyDTO.getAddress());
			pe.setOwnerEmail(propertyDTO.getOwnerEmail());
			pe.setOwnerName(propertyDTO.getOwnerName());
			pe.setPrice(propertyDTO.getPrice());
			pe.setDescription(propertyDTO.getDescription());
			
			propertyRepository.save(pe);
			dto = propertyConverter.convertEntityToDTO(pe);
		}
		
		return dto;
	}


	@Override
	public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
		Optional<PropertyEntity> optEn= propertyRepository.findById(propertyId);
		PropertyDTO dto = null;
		if(optEn.isPresent()) {
			PropertyEntity pe = optEn.get();			
			pe.setDescription(propertyDTO.getDescription());
			dto = propertyConverter.convertEntityToDTO(pe);
			propertyRepository.save(pe);				
		}
		
		return dto;
	}


	@Override
	public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
		Optional<PropertyEntity> optEn= propertyRepository.findById(propertyId);
		PropertyDTO dto = null;
		if(optEn.isPresent()) {
			PropertyEntity pe = optEn.get();			
			pe.setPrice(propertyDTO.getPrice());
			dto = propertyConverter.convertEntityToDTO(pe);
			propertyRepository.save(pe);				
		}
		
		return dto;
	}


	@Override
	public void deleteProperty(Long propertyId) {
		propertyRepository.deleteById(propertyId);
		
	}
}
