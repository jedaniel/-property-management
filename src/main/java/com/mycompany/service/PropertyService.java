package com.mycompany.service;

import java.util.List;

import com.mycompany.dto.PropertyDTO;

public interface PropertyService {
	PropertyDTO saveProperty(PropertyDTO propertyDTO);
	List<PropertyDTO> listAllProperty();
	PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId);
	PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId);
	PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId);
	void deleteProperty(Long propertyId);
}
