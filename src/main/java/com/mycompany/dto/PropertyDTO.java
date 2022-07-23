package com.mycompany.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PropertyDTO {
	private Long id;
	private String title;
	private String description;
	private String ownerName;
	private String ownerEmail;
	private Double price;
	private String address;
}
